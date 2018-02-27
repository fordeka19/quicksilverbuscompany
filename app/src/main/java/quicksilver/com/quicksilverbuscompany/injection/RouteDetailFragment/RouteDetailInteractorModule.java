package quicksilver.com.quicksilverbuscompany.injection.RouteDetailFragment;

import dagger.Module;
import dagger.Provides;
import quicksilver.com.quicksilverbuscompany.interactor.RouteDetailInteractor;

@Module
public class RouteDetailInteractorModule {

    private RouteDetailInteractor mRouteDetailInteractor;

    public RouteDetailInteractorModule() {
        mRouteDetailInteractor = new RouteDetailInteractor();
    }

    @Provides
    RouteDetailInteractor provideRoutesInteractorImpl() {
        return mRouteDetailInteractor;
    }
}
