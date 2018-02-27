package quicksilver.com.quicksilverbuscompany.presentation;

import android.support.annotation.NonNull;

import quicksilver.com.quicksilverbuscompany.model.Route;

public interface RouteDetailContract {

    interface RouteDetailView {
        void displayRouteDetail(@NonNull Route route);
    }

    interface RouteDetailPresenter {
        void getRoute(String routeId);
    }
}