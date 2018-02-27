package quicksilver.com.quicksilverbuscompany.interactor;

import quicksilver.com.quicksilverbuscompany.cache.RouteCache;
import quicksilver.com.quicksilverbuscompany.model.Route;

public class RouteDetailInteractor  {

    /*  Get route from cache */
    public Route getRoute(String routeId) {
        return RouteCache.getInstance().getRoute(routeId);
    }
}
