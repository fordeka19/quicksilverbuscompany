package quicksilver.com.quicksilverbuscompany.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RouteTest {

    @Test
    public void test_setGetId() {
        Route route = new Route();
        route.setId("test");
        assertEquals("test", route.getId());
    }

    @Test
    public void test_setGetName() {
        Route route = new Route();
        route.setName("name");
        assertEquals("name", route.getName());
    }

    @Test
    public void test_setGetStops() {
        Stop stop1 = mock(Stop.class);
        Stop stop2  = mock(Stop.class);

        when(stop1.getName()).thenReturn("stop1");
        when(stop2.getName()).thenReturn("stop2");

        List<Stop> stops = Arrays.asList(stop1, stop2);

        Route route = new Route();
        route.setStops(stops);

        List<Stop> actualStops = route.getStops();
        assertEquals(stops, actualStops);
        assertEquals("stop1", actualStops.get(0).getName());
        assertEquals("stop2", actualStops.get(1).getName());
    }

    @Test
    public void test_setGetDescription() {
        Route route = new Route();
        route.setDescription("test");
        assertEquals("test", route.getDescription());
    }

    @Test
    public void test_setGetAccessibile() {
        Route route = new Route();
        route.setAccessible(true);

        assertEquals(true, route.isAccessible());
    }

    @Test
    public void test_SetGetImage() {
        Route route = new Route();
        route.setImage("https://test");

        assertEquals("https://test", route.getImage());
    }
}
