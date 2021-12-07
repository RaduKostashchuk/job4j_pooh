package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Resp result = new Resp("", "204");
        String topic = req.getSourceName();
        if ("POST".equals(req.httpRequestType())) {
            queue.putIfAbsent(topic, new ConcurrentLinkedQueue<>());
            queue.get(topic).add(req.getParam());
            result =  new Resp("", "200");
        } else if ("GET".equals(req.httpRequestType())) {
            if (queue.get(topic) != null
                    && queue.get(topic).peek() != null) {
                result = new Resp(queue.get(topic).poll(), "200");
            }
        }
        return result;
    }
}
