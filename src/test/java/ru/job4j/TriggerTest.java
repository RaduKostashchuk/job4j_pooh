package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TriggerTest {

    @Test
    public void whenGet() {
        Trigger trigger = new Trigger();
        assertThat(trigger.get(), is(0));
    }

}