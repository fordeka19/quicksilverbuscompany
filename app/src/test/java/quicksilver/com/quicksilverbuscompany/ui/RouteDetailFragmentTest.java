package quicksilver.com.quicksilverbuscompany.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quicksilver.com.quicksilverbuscompany.R;
import quicksilver.com.quicksilverbuscompany.cache.RouteCache;
import quicksilver.com.quicksilverbuscompany.model.Route;
import quicksilver.com.quicksilverbuscompany.ui.activities.RouteListActivity;
import quicksilver.com.quicksilverbuscompany.ui.fragments.RouteDetailFragment;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static quicksilver.com.quicksilverbuscompany.ui.fragments.RouteDetailFragment.ARG_ITEM_ID;

public class RouteDetailFragmentTest extends ActivityInstrumentationTestCase2<RouteListActivity> {
    private Fragment mFragment;

    public RouteDetailFragmentTest() {
        super(RouteListActivity.class);
    }

    @Before
    public void before() {
        Route route = mock(Route.class);
        when(route.getId()).thenReturn("testId");
        when(route.getName()).thenReturn("testName");
        when(route.getDescription()).thenReturn("testDescription");
        when(route.isAccessible()).thenReturn(true);
        RouteCache.getInstance().addRoute(route);

        mFragment = new RouteDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_ITEM_ID, "testId");
        mFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().add(1, mFragment, null).commit();
    }

    @Test
    public void test_routeName() {
        TextView routeName = mFragment.getView().findViewById(R.id.route_name);
        assertNotNull(routeName);
        assertEquals("testName", routeName.getText());
    }

    @Test
    public void test_routeDescription() {
        TextView routeDescription = mFragment.getView().findViewById(R.id.route_description);
        assertNotNull(routeDescription);
        assertEquals("testDescription", routeDescription.getText());
    }

    @Test
    public void test_accessibilityImage() {
        ImageView accessibilityImage = mFragment.getView().findViewById(R.id.accessability_image);
        assertNotNull(accessibilityImage);
        assertEquals(accessibilityImage.getVisibility(), View.VISIBLE);
    }

    @Test
    public void test_accessibilityImageGone() {
        Route route = mock(Route.class);
        when(route.getId()).thenReturn("testId2");
        when(route.getName()).thenReturn("testName");
        when(route.getDescription()).thenReturn("testDescription");
        when(route.isAccessible()).thenReturn(true);
        RouteCache.getInstance().addRoute(route);

        Fragment fragment = new RouteDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_ITEM_ID, "testId2");
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().add(2, fragment, null).commit();

        ImageView accessibilityImage = fragment.getView().findViewById(R.id.accessability_image);
        assertNotNull(accessibilityImage);
        assertEquals(accessibilityImage.getVisibility(), View.GONE);
    }

    @After
    public void after() {
        RouteCache.getInstance().clear();
    }
}
