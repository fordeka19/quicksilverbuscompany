package quicksilver.com.quicksilverbuscompany.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;

import quicksilver.com.quicksilverbuscompany.R;
import quicksilver.com.quicksilverbuscompany.ui.fragments.RouteDetailFragment;

/**
 * An activity representing a single Route detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RouteListActivity}.
 */
public class RouteDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(RouteDetailFragment.ARG_ITEM_ID, getIntent().getStringExtra(RouteDetailFragment.ARG_ITEM_ID));
            RouteDetailFragment fragment = new RouteDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.route_detail_container, fragment).commit();
        }
    }
}
