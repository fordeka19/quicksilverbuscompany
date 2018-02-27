package quicksilver.com.quicksilverbuscompany.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import quicksilver.com.quicksilverbuscompany.R;
import quicksilver.com.quicksilverbuscompany.model.Route;
import quicksilver.com.quicksilverbuscompany.model.Stop;

public class RouteView extends LinearLayout {
    private Context mContext;

    public RouteView(Context context) {
        this(context, null);
        setOrientation(LinearLayout.VERTICAL);
    }

    public RouteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setOrientation(LinearLayout.VERTICAL);
    }

    public void setupStopViews(Route route) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        List<Stop> stops = route.getStops();
        int size = stops.size();
        for (int i = 0; i < size; i++) {
            Stop stop = stops.get(i);
            ViewGroup stopLayout = (ViewGroup) inflater.inflate(R.layout.stop_layout, null);
            TextView stopTv = stopLayout.findViewById(R.id.stop_name);
            stopTv.setText(stop.getName());
            addView(stopLayout);

            // add divider line between stops
            if (i < size - 1) {
                RelativeLayout stopLine = (RelativeLayout) inflater.inflate(R.layout.stop_line, null);
                addView(stopLine);
            }
        }
    }
}
