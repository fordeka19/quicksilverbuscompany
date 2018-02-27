package quicksilver.com.quicksilverbuscompany.presentation;

import quicksilver.com.quicksilverbuscompany.interactor.RouteDetailInteractor;
import quicksilver.com.quicksilverbuscompany.model.Route;

public class RouteDetailPresenterImpl implements RouteDetailContract.RouteDetailPresenter{
    private RouteDetailContract.RouteDetailView mRouteDetailView;
    private RouteDetailInteractor mRouteDetailInteractor;

    public RouteDetailPresenterImpl(RouteDetailContract.RouteDetailView view, RouteDetailInteractor routeDetailInteractor) {
        mRouteDetailView = view;
        mRouteDetailInteractor = routeDetailInteractor;
    }

    @Override
    public void getRoute(String routeId) {
        Route route = mRouteDetailInteractor.getRoute(routeId);
        if (route != null) {
            mRouteDetailView.displayRouteDetail(route);
        }
    }
}
