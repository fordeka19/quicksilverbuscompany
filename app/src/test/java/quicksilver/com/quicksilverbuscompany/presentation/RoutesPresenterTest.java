package quicksilver.com.quicksilverbuscompany.presentation;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import quicksilver.com.quicksilverbuscompany.interactor.RoutesInteractor;
import quicksilver.com.quicksilverbuscompany.model.Route;

import static org.mockito.ArgumentMatchers.anyListOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RoutesPresenterTest {
    @Mock private RoutesContract.RoutesView mView;
    @Mock private RoutesInteractor mRoutesInteractor;
    private RequestQueue mRequestQueue;
    private Gson mGson;
    private RoutesContract.RoutesPresenter mPresenter;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mRequestQueue = mock(RequestQueue.class);
        mGson = new GsonBuilder().create(); // could not mock final class
        mPresenter = new RoutesPresenterImpl(mRequestQueue,  mGson, mView, mRoutesInteractor, Schedulers.trampoline(), Schedulers.trampoline());
    }

    @Test
    public void test_getRoutes_validResults() {
        Route route1 = mock(Route.class);
        Route route2 = mock(Route.class);
        List<Route> routes = Arrays.asList(route1, route2);

        when(mRoutesInteractor.getRoutes(mRequestQueue, mGson)).thenReturn(Observable.just(routes));

        mPresenter.getRoutes();

        verify(mView).showProgress();
        verify(mView).hideProgress();
        verify(mView).setupRoutesRecyclerView(routes);
        verify(mView, never()).showErrorDialog();
    }

    @Test
    public void test_getRoutes_error() {
        Observable errorObservable = Observable.error(new VolleyError());
        when(mRoutesInteractor.getRoutes(mRequestQueue, mGson)).thenReturn(errorObservable);

        mPresenter.getRoutes();

        verify(mView).showProgress();
        verify(mView).hideProgress();
        verify(mView).showErrorDialog();
        verify(mView, never()).setupRoutesRecyclerView(anyListOf(Route.class));
    }

    @Test
    public void test_getRoutes_jsonException() {
        Observable errorObservable = Observable.error(new JSONException("JSON exception"));
        when(mRoutesInteractor.getRoutes(mRequestQueue, mGson)).thenReturn(errorObservable);

        mPresenter.getRoutes();

        verify(mView).showProgress();
        verify(mView).hideProgress();
        verify(mView).showErrorDialog();
        verify(mView, never()).setupRoutesRecyclerView(anyListOf(Route.class));
    }
}
