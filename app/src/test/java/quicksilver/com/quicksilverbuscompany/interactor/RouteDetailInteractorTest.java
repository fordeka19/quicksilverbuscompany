package quicksilver.com.quicksilverbuscompany.interactor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import quicksilver.com.quicksilverbuscompany.cache.RouteCache;
import quicksilver.com.quicksilverbuscompany.model.Route;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.when;

public class RouteDetailInteractorTest {
    private static final String ADDED_ROUTE_ID = "route_id_1";
    private static final String NOT_ADDED_ROUTE_ID = "route_id_2";

    private RouteDetailInteractor mRouteDetailInteractor;
    @Mock private Route mRoute;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(mRoute.getId()).thenReturn(ADDED_ROUTE_ID);
        RouteCache.getInstance().addRoute(mRoute);
        mRouteDetailInteractor = new RouteDetailInteractor();
    }

    @Test
    public void test_getRoute_validResult() {
        Route route = mRouteDetailInteractor.getRoute(ADDED_ROUTE_ID);

        assertNotNull(route);
        assertEquals(ADDED_ROUTE_ID, route.getId());
    }

    @Test
    public void test_getRoute_null() {
        Route route = mRouteDetailInteractor.getRoute(NOT_ADDED_ROUTE_ID);

        assertNull(route);
    }

    @After
    public void after() {
        RouteCache.getInstance().clear(); // clear test routes from cache
    }
}
