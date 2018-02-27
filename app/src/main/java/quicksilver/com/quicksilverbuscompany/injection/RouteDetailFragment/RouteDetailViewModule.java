package quicksilver.com.quicksilverbuscompany.injection.RouteDetailFragment;


import dagger.Binds;
import dagger.Module;
import quicksilver.com.quicksilverbuscompany.presentation.RouteDetailContract;
import quicksilver.com.quicksilverbuscompany.ui.fragments.RouteDetailFragment;

@Module
public abstract class RouteDetailViewModule {

    @Binds
    abstract RouteDetailContract.RouteDetailView provideRouteDetailView(RouteDetailFragment routeDetailFragment);
}