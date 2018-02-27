package quicksilver.com.quicksilverbuscompany.injection;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import quicksilver.com.quicksilverbuscompany.App;
import quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules.RoutesInteractorModule;
import quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules.SchedulerModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, BuildersModule.class, RoutesInteractorModule.class, SchedulerModule.class })
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App application);
        @BindsInstance
        Builder routesInteractorModule(RoutesInteractorModule routesInteractorModule);
        @BindsInstance
        Builder schedulerModule(SchedulerModule schedulerModule);
        AppComponent build();
    }
    void inject(App app);
    void inject(RoutesInteractorModule routesInteractorModule);

}

