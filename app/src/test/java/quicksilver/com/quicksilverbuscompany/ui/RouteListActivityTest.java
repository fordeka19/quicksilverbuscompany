package quicksilver.com.quicksilverbuscompany.ui;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import org.junit.Test;

import quicksilver.com.quicksilverbuscompany.R;
import quicksilver.com.quicksilverbuscompany.ui.activities.RouteListActivity;

public class RouteListActivityTest extends ActivityInstrumentationTestCase2<RouteListActivity> {

    public RouteListActivityTest() {
        super(RouteListActivity.class);
    }

    @Test
    public void test_recyclerViewIsPresent() {
        RouteListActivity activity = getActivity();
        View recyclerView = activity.findViewById(R.id.route_list);
        assertNotNull(recyclerView);
    }
}
