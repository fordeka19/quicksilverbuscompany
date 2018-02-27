package quicksilver.com.quicksilverbuscompany.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import quicksilver.com.quicksilverbuscompany.R;
import quicksilver.com.quicksilverbuscompany.presentation.RoutesContract;
import quicksilver.com.quicksilverbuscompany.utils.Utils;
import quicksilver.com.quicksilverbuscompany.ui.adapters.RouteRecyclerViewAdapter;
import quicksilver.com.quicksilverbuscompany.model.Route;
import java.util.List;

import javax.inject.Inject;

/**
 * An activity representing a list of Routes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RouteDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RouteListActivity extends AppCompatActivity implements RoutesContract.RoutesView {
    private boolean mTwoPane; // Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
    private Unbinder mUnbinder;
    @BindView(R.id.route_list)  RecyclerView mRoutesRecyclerView;
    @BindView(R.id.progress_bar)  ProgressBar mProgressBar;

    @Inject RequestQueue mVolleyRequestQueue;
    @Inject RoutesContract.RoutesPresenter mRoutesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this); // Use Dagger 2 to inject dependencies
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.routes_title);
        getSupportActionBar().setTitle(R.string.routes_title);

        if (findViewById(R.id.route_detail_container) != null) {
            // The detail container view will be present only in the large-screen layouts (res/values-w900dp).
            // If this view is present, then the activity should be in two-pane mode.
            mTwoPane = true;
        }

        mUnbinder = ButterKnife.bind(this);

        // In a full application we where we would get new/updated routes, store in a DB, listen for changes to the list from server, add/update/remove new/updated/removed routes to DB.
        // We cache here for detail fragment purpose.
        mRoutesPresenter.getRoutes();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRoutesPresenter.unbind();
        mUnbinder.unbind();
    }

    @Override
    public void setupRoutesRecyclerView(List<Route> routes) {
        if (mRoutesRecyclerView != null) {
            mRoutesRecyclerView.setAdapter(new RouteRecyclerViewAdapter(this, routes, mTwoPane));
        }
    }

    @Override
    public void showErrorDialog() {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        };
        Utils.showDialog(this, R.string.get_routes_error, R.string.ok, onClickListener);
    }

    @Override
    public void showProgress() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}