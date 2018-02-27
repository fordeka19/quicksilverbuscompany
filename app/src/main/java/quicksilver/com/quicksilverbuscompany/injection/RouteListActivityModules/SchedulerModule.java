package quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class SchedulerModule {
    private Scheduler mIoScheduler;
    private Scheduler mMainScheduler;

    public SchedulerModule() {
        mIoScheduler = Schedulers.io();
        mMainScheduler = AndroidSchedulers.mainThread();
    }

    @Provides
    @Named("ioScheduler")
    public Scheduler provideIoScheduler() {
        return mIoScheduler;
    }

    @Provides
    @Named("mainScheduler")
    public Scheduler provideMainScheduler() {
        return mMainScheduler;
    }
}
