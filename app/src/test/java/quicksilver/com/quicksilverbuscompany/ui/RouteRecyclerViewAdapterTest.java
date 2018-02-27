package quicksilver.com.quicksilverbuscompany.ui;

import android.test.ActivityInstrumentationTestCase2;
import android.view.LayoutInflater;
import android.view.View;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import quicksilver.com.quicksilverbuscompany.R;
import quicksilver.com.quicksilverbuscompany.model.Route;
import quicksilver.com.quicksilverbuscompany.ui.activities.RouteListActivity;
import quicksilver.com.quicksilverbuscompany.ui.adapters.RouteRecyclerViewAdapter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RouteRecyclerViewAdapterTest extends ActivityInstrumentationTestCase2<RouteListActivity> {
    private RouteRecyclerViewAdapter mAdapter;

    public RouteRecyclerViewAdapterTest() {
        super(RouteListActivity.class);

        Route route1 = mock(Route.class);
        when(route1.getName()).thenReturn("Test");
        Route route2 = mock(Route.class);
        List<Route> routes = Arrays.asList(route1, route2);

        mAdapter = new RouteRecyclerViewAdapter(getActivity(), routes, false);
    }

    @Test
    public void test_onBindViewHolder() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.route_list_content, null, false);
        RouteRecyclerViewAdapter.ViewHolder viewHolder = new RouteRecyclerViewAdapter.ViewHolder(view);
        mAdapter.onBindViewHolder(viewHolder, 0);
        assertEquals("Test", viewHolder.mNameView.getText());
        assertEquals(View.VISIBLE, viewHolder.mNameView);
        assertEquals(View.VISIBLE, viewHolder.mRouteImageView);
    }

    @Test
    public void test_getItemCount() {
        assertEquals(2, mAdapter.getItemCount());
    }
}
