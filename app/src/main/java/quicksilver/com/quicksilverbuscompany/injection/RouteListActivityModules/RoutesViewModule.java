package quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules;


import dagger.Binds;
import dagger.Module;
import quicksilver.com.quicksilverbuscompany.presentation.RoutesContract;
import quicksilver.com.quicksilverbuscompany.ui.activities.RouteListActivity;

@Module
public abstract class RoutesViewModule {

    @Binds
    abstract RoutesContract.RoutesView provideRoutesView(RouteListActivity routesActivity);
}