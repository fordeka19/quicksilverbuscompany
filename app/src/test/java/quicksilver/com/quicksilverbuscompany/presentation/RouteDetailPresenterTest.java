package quicksilver.com.quicksilverbuscompany.presentation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import quicksilver.com.quicksilverbuscompany.interactor.RouteDetailInteractor;
import quicksilver.com.quicksilverbuscompany.model.Route;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RouteDetailPresenterTest {
    @Mock private RouteDetailContract.RouteDetailView mView;
    @Mock private RouteDetailInteractor mRouteDetailInteractor;
    private RouteDetailContract.RouteDetailPresenter mPresenter;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new RouteDetailPresenterImpl(mView, mRouteDetailInteractor);
    }

    @Test
    public void test_getRoute_returnValidRoute() {
        Route route = mock(Route.class);
        String id = "route_id1";

        when(mRouteDetailInteractor.getRoute(id)).thenReturn(route);

        mPresenter.getRoute(id);

        verify(mView).displayRouteDetail(route);
    }

    @Test
    public void test_getRoute_null() {
        Route route = mock(Route.class);
        String id = "route_id1";

        when(mRouteDetailInteractor.getRoute(id)).thenReturn(null);

        mPresenter.getRoute(id);

        verify(mView, never()).displayRouteDetail(route);
    }
}
