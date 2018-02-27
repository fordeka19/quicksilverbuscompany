package quicksilver.com.quicksilverbuscompany.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StopTest {

    @Test
    public void test_setGetName() {
        Stop stop = new Stop();
        stop.setName("Name");

        assertEquals("Name", stop.getName());
    }
}
