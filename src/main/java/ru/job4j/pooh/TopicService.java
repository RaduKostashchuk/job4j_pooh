package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> queue =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Resp result = new Resp("", "204");
        if ("POST".equals(req.httpRequestType())) {
            queue.get(req.getSourceName())
                    .forEach(
                            4,
                            (k, v) -> v.add(req.getParam()));
        } else if ("GET".equals(req.httpRequestType())) {
            String topic = req.getSourceName();
            queue.putIfAbsent(topic, new ConcurrentHashMap<>());
            queue.get(topic).putIfAbsent(req.getParam(), new ConcurrentLinkedQueue<>());
            String text = queue.get(topic).get(req.getParam()).poll();
            result = text != null ? new Resp(text, "200") : result;
        }
        return result;
    }
}
