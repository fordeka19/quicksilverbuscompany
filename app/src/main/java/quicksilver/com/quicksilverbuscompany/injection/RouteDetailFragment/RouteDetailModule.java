package quicksilver.com.quicksilverbuscompany.injection.RouteDetailFragment;

import dagger.Module;
import dagger.Provides;
import quicksilver.com.quicksilverbuscompany.interactor.RouteDetailInteractor;
import quicksilver.com.quicksilverbuscompany.presentation.RouteDetailContract;
import quicksilver.com.quicksilverbuscompany.presentation.RouteDetailPresenterImpl;

@Module
public class RouteDetailModule {

    @Provides
    RouteDetailContract.RouteDetailPresenter provideRouteDetailPresenter(RouteDetailContract.RouteDetailView routeDetailView, RouteDetailInteractor routeDetailInteractor) {
        return new RouteDetailPresenterImpl(routeDetailView, routeDetailInteractor);
    }
}
