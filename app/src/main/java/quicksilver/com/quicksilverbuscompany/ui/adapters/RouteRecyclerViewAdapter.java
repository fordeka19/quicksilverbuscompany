package quicksilver.com.quicksilverbuscompany.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import quicksilver.com.quicksilverbuscompany.R;
import quicksilver.com.quicksilverbuscompany.ui.activities.RouteDetailActivity;
import quicksilver.com.quicksilverbuscompany.ui.fragments.RouteDetailFragment;
import quicksilver.com.quicksilverbuscompany.ui.activities.RouteListActivity;
import quicksilver.com.quicksilverbuscompany.model.Route;

public class RouteRecyclerViewAdapter extends RecyclerView.Adapter<RouteRecyclerViewAdapter.ViewHolder> {

    private final RouteListActivity mParentActivity;
    private final List<Route> mRoutes;
    private final boolean mTwoPane;

    public RouteRecyclerViewAdapter(RouteListActivity parent, List<Route> routes, boolean twoPane) {
        mRoutes = routes;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.route_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Route route = mRoutes.get(position);
        Picasso.with(mParentActivity).load(route.getImage()).into(holder.mRouteImageView);
        holder.mNameView.setText(route.getName());

        holder.itemView.setTag(route);

        View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Route route = (Route) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(RouteDetailFragment.ARG_ITEM_ID, route.getId());
                    RouteDetailFragment fragment = new RouteDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.route_detail_container, fragment).commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, RouteDetailActivity.class);
                    intent.putExtra(RouteDetailFragment.ARG_ITEM_ID, route.getId());
                    context.startActivity(intent);
                }
            }
        };
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mRoutes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.route_image) @VisibleForTesting  public ImageView mRouteImageView;
        @BindView(R.id.route_name) @VisibleForTesting  public TextView mNameView;

        @VisibleForTesting
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}