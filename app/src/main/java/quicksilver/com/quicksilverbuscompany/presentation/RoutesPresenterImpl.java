package quicksilver.com.quicksilverbuscompany.presentation;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import quicksilver.com.quicksilverbuscompany.interactor.RoutesInteractor;
import quicksilver.com.quicksilverbuscompany.model.Route;

import static quicksilver.com.quicksilverbuscompany.Constants.LOG_TAG;

public class RoutesPresenterImpl implements RoutesContract.RoutesPresenter {
    private RequestQueue mRequestQueue;
    private Gson mGson;
    private RoutesContract.RoutesView mRoutesView;
    private RoutesInteractor mRoutesInteractor;
    private Scheduler mIoScheduler;
    private Scheduler mMainScheduler;
    private Disposable mDisposable;

    public RoutesPresenterImpl(RequestQueue requestQueue, Gson gson, RoutesContract.RoutesView view, RoutesInteractor routesInteractor, Scheduler ioScheduler, Scheduler mainScheduler) {
        mRequestQueue = requestQueue;
        mGson = gson;
        mRoutesView = view;
        mRoutesInteractor = routesInteractor;
        mIoScheduler = ioScheduler;
        mMainScheduler = mainScheduler;
    }

    @Override
    public void getRoutes() {
        if (mRoutesView != null) {
            mRoutesView.showProgress();
        }

        // Use RxJava to handle threading
        mRoutesInteractor.getRoutes(mRequestQueue, mGson)
                .subscribeOn(mIoScheduler)
                .observeOn(mMainScheduler)
                .subscribe(getRoutesObserver());
    }

    @Override
    public void unbind() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    private Observer<List<Route>> getRoutesObserver() {
        Observer<List<Route>> observer = new Observer<List<Route>>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                mDisposable = disposable;
            }

            @Override
            public void onNext(List<Route> routes) {
                mRoutesView.hideProgress();
                mRoutesView.setupRoutesRecyclerView(routes);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(LOG_TAG, "Error retrieving routes: " + e);
                mRoutesView.hideProgress();
                mRoutesView.showErrorDialog();
            }

            @Override
            public void onComplete() {
                Log.i(LOG_TAG, "getRoutes onComplete");
            }
        };

        return observer;
    }
}

