package quicksilver.com.quicksilverbuscompany.interactor;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import quicksilver.com.quicksilverbuscompany.cache.RouteCache;
import quicksilver.com.quicksilverbuscompany.model.Route;

import static quicksilver.com.quicksilverbuscompany.Constants.JSON_ROUTES_TAG;
import static quicksilver.com.quicksilverbuscompany.Constants.LOG_TAG;
import static quicksilver.com.quicksilverbuscompany.Constants.ROUTES_URL;

public class RoutesInteractor {

    /*  Request the list of routes using Volley and return an observable.
     *  If the request is successful and returns a list of routes,
     *  we use the ObservableEmitter to communicate the list back to the presenter.
     *  If there was an error we also use the ObservableEmitter to communicate that there was an error to the presenter
     *  */
    public Observable<List<Route>> getRoutes(final RequestQueue requestQueue, final Gson gson) {
        return Observable.create(new ObservableOnSubscribe<List<Route>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<Route>> emitter) {
                JsonObjectRequest getRoutesRequest = new JsonObjectRequest(Request.Method.GET, ROUTES_URL, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray routesArray = response.getJSONArray(JSON_ROUTES_TAG);
                                    List<Route> routes = gson.fromJson(routesArray.toString(), new TypeToken<List<Route>>() {}.getType());
                                    RouteCache.getInstance().addRoutes(routes); // add routes to cache
                                    emitter.onNext(routes);
                                } catch (JSONException e) {
                                    emitter.onError(e);
                                    Log.e(LOG_TAG, "JSONException getting routes", e);
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        emitter.onError(error);
                        Log.e(LOG_TAG, "Error getting routes", error);
                    }
                });

                requestQueue.add(getRoutesRequest);
            }
        });
    }
}
