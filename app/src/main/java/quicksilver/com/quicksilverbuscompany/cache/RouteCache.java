package quicksilver.com.quicksilverbuscompany.cache;

import android.support.annotation.VisibleForTesting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quicksilver.com.quicksilverbuscompany.model.Route;

public class RouteCache {
    private static RouteCache sInstance;

    private Map<String, Route> mRoutesMap = new HashMap<>();

    public static RouteCache getInstance() {
        if (sInstance == null) {
            sInstance = new RouteCache();
        }
        return sInstance;
    }

    public void addRoutes(List<Route> routes) {
        for (Route route : routes) {
            mRoutesMap.put(route.getId(), route);
        }
    }

    public Route getRoute(String routeId) {
        return mRoutesMap.get(routeId);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public void addRoute(Route route) {
        mRoutesMap.put(route.getId(), route);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public void clear() {
        mRoutesMap.clear();
    }
}
