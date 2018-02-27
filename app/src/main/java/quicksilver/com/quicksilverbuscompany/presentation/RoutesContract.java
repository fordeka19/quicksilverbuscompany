package quicksilver.com.quicksilverbuscompany.presentation;

import java.util.List;

import quicksilver.com.quicksilverbuscompany.model.Route;

public interface RoutesContract {

    interface RoutesView {
        void setupRoutesRecyclerView(List<Route> routes);
        void showErrorDialog();
        void showProgress();
        void hideProgress();
    }

    interface RoutesPresenter {
        void getRoutes();
        void unbind();
    }
}