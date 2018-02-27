package quicksilver.com.quicksilverbuscompany;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.Scheduler;
import quicksilver.com.quicksilverbuscompany.injection.DaggerAppComponent;
import quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules.RoutesInteractorModule;
import quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules.SchedulerModule;

public class App extends Application implements HasActivityInjector, HasSupportFragmentInjector {
    @Inject DispatchingAndroidInjector<Activity> mActivityInjector;
    @Inject DispatchingAndroidInjector<Fragment> mFragmentInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .routesInteractorModule(getRoutesInteractorModule())
                .schedulerModule(getSchedulerModule())
                .build().inject(this);
    }

    public RoutesInteractorModule getRoutesInteractorModule() {
        return new RoutesInteractorModule();
    }

    public SchedulerModule getSchedulerModule() {
        return new SchedulerModule();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentInjector;
    }
}
