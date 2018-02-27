package quicksilver.com.quicksilverbuscompany.injection;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import quicksilver.com.quicksilverbuscompany.injection.RouteDetailFragment.RouteDetailInteractorModule;
import quicksilver.com.quicksilverbuscompany.injection.RouteDetailFragment.RouteDetailModule;
import quicksilver.com.quicksilverbuscompany.injection.RouteDetailFragment.RouteDetailViewModule;
import quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules.GetRoutesModule;
import quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules.RoutesViewModule;
import quicksilver.com.quicksilverbuscompany.ui.activities.RouteListActivity;
import quicksilver.com.quicksilverbuscompany.ui.fragments.RouteDetailFragment;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = {RoutesViewModule.class, GetRoutesModule.class})
    abstract RouteListActivity bindRouteListActivity();

    @ContributesAndroidInjector(modules = {RouteDetailViewModule.class, RouteDetailModule.class, RouteDetailInteractorModule.class})
    abstract RouteDetailFragment bindRouteDetailFragment();
}
