package ru.job4j.pooh;

public class Req {
    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        return new Req(findReq(content),
                        findMode(content),
                        findSourceName(content),
                        findParam(content));
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }

    private static String findReq(String content) {
        return content.split("\s")[0];
    }

    private static String findMode(String content) {
        return content
                .split("\s")[1]
                .split("/")[1];
    }

    private static String findSourceName(String content) {
        return content
                .split("\s")[1]
                .split("/")[2];
    }

    private static String findParam(String content) {
        String result = "";
        String ls = System.lineSeparator();
        if ("POST".equals(findReq(content))) {
            String[] request = content.split(ls);
            result = request[request.length - 1];
        } else if ("GET".equals(findReq(content)) && findMode(content).equals("topic")) {
            result = content
                    .split("\s")[1]
                    .split("/")[3];
        }
        return result;
    }

}
