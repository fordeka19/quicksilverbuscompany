package quicksilver.com;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.volley.RequestQueue;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules.RoutesInteractorModule;
import quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules.SchedulerModule;
import quicksilver.com.quicksilverbuscompany.interactor.RoutesInteractor;
import quicksilver.com.quicksilverbuscompany.model.Route;
import quicksilver.com.quicksilverbuscompany.ui.activities.RouteListActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class RouteListActivityTest {
    private RoutesInteractor mRoutesInteractor;

    @Rule
    public ActivityTestRule<RouteListActivity> mActivityRule = new ActivityTestRule<>(RouteListActivity.class, false, false);

    @Before
    public void before() throws Exception {
        // inject mock module
        mRoutesInteractor = mock(RoutesInteractor.class);
        RoutesInteractorModule routesInteractorModule = mock(RoutesInteractorModule.class);
        when(routesInteractorModule.provideRoutesInteractor()).thenReturn(mRoutesInteractor);

        SchedulerModule schedulerModule = mock(SchedulerModule.class);
        when(schedulerModule.provideIoScheduler()).thenReturn(Schedulers.trampoline());
        when(schedulerModule.provideMainScheduler()).thenReturn(Schedulers.trampoline());

        Instrumentation instrumentation = getInstrumentation();
        TestApp app = (TestApp) instrumentation.getTargetContext().getApplicationContext();

        app.setRoutesInteractorModule(routesInteractorModule);
        app.setSchedulerModule(schedulerModule);
        app.onCreate();
    }

    @Test
    public void testActivity() {
        Route route1 = mock(Route.class);
        Route route2 = mock(Route.class);
        List<Route> routes = Arrays.asList(route1, route2);
        when(route1.getName()).thenReturn("Route Name 1");
        when(route1.getDescription()).thenReturn("Route 1 Description");
        when(route2.getName()).thenReturn("Route Name 2");

        when(mRoutesInteractor.getRoutes(mock(RequestQueue.class), new GsonBuilder().create())).thenReturn(Observable.just(routes));

        mActivityRule.launchActivity(new Intent());

        onView(withText("Route Name 1"));
        onView(withText("Route Name 2"));
    }
}
