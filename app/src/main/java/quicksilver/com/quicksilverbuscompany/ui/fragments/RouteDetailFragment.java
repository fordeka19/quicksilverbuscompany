package quicksilver.com.quicksilverbuscompany.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import quicksilver.com.quicksilverbuscompany.R;
import quicksilver.com.quicksilverbuscompany.presentation.RouteDetailContract;
import quicksilver.com.quicksilverbuscompany.ui.activities.RouteListActivity;
import quicksilver.com.quicksilverbuscompany.model.Route;
import quicksilver.com.quicksilverbuscompany.ui.activities.RouteDetailActivity;
import quicksilver.com.quicksilverbuscompany.ui.views.RouteView;

/**
 * A fragment representing a single Route detail screen.
 * This fragment is either contained in a {@link RouteListActivity}
 * in two-pane mode (on tablets) or a {@link RouteDetailActivity}
 * on handsets.
 */
public class RouteDetailFragment extends Fragment implements RouteDetailContract.RouteDetailView{

    public static final String ARG_ITEM_ID = "item_id";

    private Unbinder mUnbinder;
    private LinearLayout mLayout;

    @BindView(R.id.route_image) ImageView mRouteImageView;
    @BindView(R.id.route_name) TextView mNameView;
    @BindView(R.id.route_description) TextView mDescriptionView;
    @BindView(R.id.accessability_image) ImageView mAccessibilityImageView;

    @Inject RouteDetailContract.RouteDetailPresenter mRouteDetailPresenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RouteDetailFragment() {
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this); // Use Dagger 2 to inject presenter dependency
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayout = (LinearLayout) inflater.inflate(R.layout.route_detail, container, false);

        mUnbinder = ButterKnife.bind(this, mLayout);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mRouteDetailPresenter.getRoute(getArguments().getString(ARG_ITEM_ID));
        }

        return mLayout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void displayRouteDetail(@NonNull Route route) {
        setupRouteViews(route);
        setupStopViews(route);
    }

    private void setupRouteViews(Route route) {
        Picasso.with(getActivity()).load(route.getImage()).into(mRouteImageView);
        mNameView.setText(route.getName());

        mDescriptionView.setVisibility(View.VISIBLE);
        mDescriptionView.setText(route.getDescription());

        if (route.isAccessible()) {
            mAccessibilityImageView.setVisibility(View.VISIBLE);
        } else {
            mAccessibilityImageView.setVisibility(View.GONE);
        }
    }

    private void setupStopViews(Route route) {
        RouteView routeView = new RouteView(getActivity());
        routeView.setupStopViews(route);
        mLayout.addView(routeView);
    }
}
