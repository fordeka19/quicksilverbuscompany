package quicksilver.com;

import quicksilver.com.quicksilverbuscompany.App;
import quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules.RoutesInteractorModule;
import quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules.SchedulerModule;

public class TestApp extends App {
    private RoutesInteractorModule mRoutesIteractorModule;
    private SchedulerModule mSchedulerModule;

    @Override
    public RoutesInteractorModule getRoutesInteractorModule() {
        if (mRoutesIteractorModule == null) {
            return super.getRoutesInteractorModule();
        }
        return mRoutesIteractorModule;
    }

    public void setRoutesInteractorModule(RoutesInteractorModule module) {
        mRoutesIteractorModule = module;
    }

    @Override
    public SchedulerModule getSchedulerModule() {
        if (mSchedulerModule == null) {
            return super.getSchedulerModule();
        }
        return mSchedulerModule;
    }

    public void setSchedulerModule(SchedulerModule module) {
        mSchedulerModule = module;
    }
}
