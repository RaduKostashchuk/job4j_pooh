package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TopicServiceTest {
    @Test
    public void whenTopic() {
        TopicService topicService = new TopicService();
        String paramForPublisher = "temperature=18";
        String paramForSubscriber1 = "client407";
        String paramForSubscriber2 = "client6565";
        topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        topicService.process(
                new Req("POST", "topic", "weather", paramForPublisher)
        );
        Resp result1 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        Resp result2 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber2)
        );
        assertThat(result1.text(), is("temperature=18"));
        assertThat(result2.text(), is(""));
    }

    @Test
    public void whenGetFromEmptyTopicsThenGetEmptyResponse() {
        TopicService topicService = new TopicService();
        Resp result1 = topicService.process(
                new Req("GET", "topic", "weather", "client1")
        );
        Resp result2 = topicService.process(
                new Req("GET", "topic", "news", "client2")
        );
        assertThat(result1.text(), is(""));
        assertThat(result2.text(), is(""));
    }

    @Test
    public void whenGetFromTwoNonEmptyTopicsThenGetResponse() {
        TopicService topicService = new TopicService();
        topicService.process(
                new Req("GET", "topic", "weather", "client1")
        );
        topicService.process(
                new Req("GET", "topic", "news", "client1")
        );
        topicService.process(
                new Req("GET", "topic", "weather", "client2")
        );
        topicService.process(
                new Req("POST", "topic", "weather", "temperature=10")
        );
        topicService.process(
                new Req("POST", "topic", "news", "text=War in Syria")
        );
        Resp result1 = topicService.process(
                new Req("GET", "topic", "weather", "client1")
        );
        Resp result2 = topicService.process(
                new Req("GET", "topic", "news", "client1")
        );
        Resp result3 = topicService.process(
                new Req("GET", "topic", "weather", "client2")
        );
        Resp result4 = topicService.process(
                new Req("GET", "topic", "news", "client2")
        );
        assertThat(result1.text(), is("temperature=10"));
        assertThat(result2.text(), is("text=War in Syria"));
        assertThat(result3.text(), is("temperature=10"));
        assertThat(result4.text(), is(""));
    }

}