package quicksilver.com.quicksilverbuscompany.injection.RouteListActivityModules;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import quicksilver.com.quicksilverbuscompany.interactor.RoutesInteractor;
import quicksilver.com.quicksilverbuscompany.presentation.RoutesContract;
import quicksilver.com.quicksilverbuscompany.presentation.RoutesPresenterImpl;

@Module
public class GetRoutesModule {

    @Provides
    RequestQueue provideVolleyRequestQueue(Context context) {
        return Volley.newRequestQueue(context);
    }

    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    RoutesContract.RoutesPresenter provideRoutesPresenter(RequestQueue requestQueue, Gson gson, RoutesContract.RoutesView routesView, RoutesInteractor routesInteractor, @Named("ioScheduler") Scheduler ioScheduler, @Named("mainScheduler") Scheduler mainScheduler) {
        return new RoutesPresenterImpl(requestQueue, gson, routesView, routesInteractor, ioScheduler, mainScheduler);
    }
}
