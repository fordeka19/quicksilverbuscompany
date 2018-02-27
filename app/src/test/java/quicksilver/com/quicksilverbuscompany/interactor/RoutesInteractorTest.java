package quicksilver.com.quicksilverbuscompany.interactor;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import quicksilver.com.quicksilverbuscompany.model.Route;

import static org.mockito.Mockito.mock;

public class RoutesInteractorTest {

    private RoutesInteractor mRoutesInteractor;
    private RequestQueue mRequestQueue;
    private Gson mGson;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mRoutesInteractor = new RoutesInteractor();
        mRequestQueue = mock(RequestQueue.class);
        mGson = new GsonBuilder().create(); // could not mock final  class Gson
    }


    @Test
    public void test_shouldLoadTwoRoutes()  {
        Route route1 = mock(Route.class);
        Route route2 = mock(Route.class);
        List<Route> routes = Arrays.asList(route1, route2);

        Observable<List<Route>> observable = Observable.create(s -> {
            mRoutesInteractor.getRoutes(mRequestQueue, mGson);
            s.onNext(routes);
        });

        TestObserver<List<Route>> listTestObserver = new TestObserver<>();

        observable.subscribe(listTestObserver);
        listTestObserver.assertSubscribed();
        listTestObserver.assertValue(routes);
    }

    @Test
    public void test_shouldResultInVolleyError() {
        VolleyError error = new VolleyError();
        Observable<List<Route>> observable = Observable.create(s -> {
            mRoutesInteractor.getRoutes(mRequestQueue, mGson);
            s.onError(error);
        });

        TestObserver<List<Route>> listTestObserver = new TestObserver<>();

        observable.subscribe(listTestObserver);
        listTestObserver.assertSubscribed();
        listTestObserver.assertError(error);
    }

    @Test
    public void test_shouldResultInJSONException() {
        JSONException exception = new JSONException("Exception");
        Observable<List<Route>> observable = Observable.create(s -> {
            mRoutesInteractor.getRoutes(mRequestQueue, mGson);
            s.onError(exception);
        });

        TestObserver<List<Route>> listTestObserver = new TestObserver<>();

        observable.subscribe(listTestObserver);
        listTestObserver.assertSubscribed();
        listTestObserver.assertError(exception);
    }
}
