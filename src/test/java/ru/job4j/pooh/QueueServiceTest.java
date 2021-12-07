package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QueueServiceTest {
    @Test
    public void whenPostThenGetQueue() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";
        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.text(), is("temperature=18"));
    }

    @Test
    public void whenEmptyQueue() {
        QueueService queueService = new QueueService();
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result.text(), is(""));
    }

    @Test
    public void whenAddTwoTimesAndGEtThreeTimesThenLastResponseIsEmpty() {
        QueueService queueService = new QueueService();
        queueService.process(
                new Req("POST", "queue", "weather", "temperature=10")
        );
        queueService.process(
                new Req("POST", "queue", "weather", "temperature=20")
        );
        Resp result1 = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        Resp result2 = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        Resp result3 = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        assertThat(result1.text(), is("temperature=10"));
        assertThat(result2.text(), is("temperature=20"));
        assertThat(result3.text(), is(""));
    }

    @Test
    public void whenAddToTwoQueuesAndGetBackThenOK() {
        QueueService queueService = new QueueService();
        queueService.process(
                new Req("POST", "queue", "weather", "temperature=10")
        );
        queueService.process(
                new Req("POST", "queue", "news", "text=Warning!")
        );
        Resp result1 = queueService.process(
                new Req("GET", "queue", "weather", null)
        );
        Resp result2 = queueService.process(
                new Req("GET", "queue", "news", null)
        );
        assertThat(result1.text(), is("temperature=10"));
        assertThat(result2.text(), is("text=Warning!"));
    }

}