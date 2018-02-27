package quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules;

import dagger.Module;
import dagger.Provides;
import quicksilver.com.quicksilverbuscompany.interactor.RoutesInteractor;

@Module
public class RoutesInteractorModule {

    private RoutesInteractor mRoutesInteractor;

    public RoutesInteractorModule() {
        mRoutesInteractor = new RoutesInteractor();
    }

    @Provides
    public RoutesInteractor provideRoutesInteractor() {
        return mRoutesInteractor;
    }
}
