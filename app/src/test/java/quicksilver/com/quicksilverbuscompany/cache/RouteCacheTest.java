package quicksilver.com.quicksilverbuscompany.cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import quicksilver.com.quicksilverbuscompany.model.Route;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.when;

public class RouteCacheTest {
    private static final String ROUTE_ID_1 = "route_id_1";
    private static final String ROUTE_ID_2 = "route_id_2";
    private static final String ROUTE_ID_3 = "route_id_3";

    private RouteCache mCache;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mCache = RouteCache.getInstance();
    }

    @Test
    public void test_addRoutesAndGetRoutes() {
        Route route1 = Mockito.mock(Route.class);
        Route route2 = Mockito.mock(Route.class);

        when(route1.getId()).thenReturn(ROUTE_ID_1);
        when(route2.getId()).thenReturn(ROUTE_ID_2);

        List<Route> routes = Arrays.asList(route1, route2);
        mCache.addRoutes(routes);

        Route retrievedRoute1 = mCache.getRoute(ROUTE_ID_1);
        Route retrievedRoute2 = mCache.getRoute(ROUTE_ID_2);
        assertNotNull(retrievedRoute1);
        assertNotNull(retrievedRoute2);
        assertEquals(ROUTE_ID_1, retrievedRoute1.getId());
        assertEquals(ROUTE_ID_2, retrievedRoute2.getId());
    }

    @Test
    public void test_getRoute_null() {
        Route route = mCache.getRoute(ROUTE_ID_3);

        assertNull(route);
    }

    @After
    public void after() {
        RouteCache.getInstance().clear(); // clear test routes from cache
    }
}
