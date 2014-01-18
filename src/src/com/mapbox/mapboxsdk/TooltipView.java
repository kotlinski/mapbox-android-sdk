package com.mapbox.mapboxsdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.osmdroid.util.GeoPoint;

/**
 * Created by Francisco on 18/01/14.
 */
public class TooltipView extends LinearLayout {

    private final MapView mapView;
    private TextView defaultTextView;
    private Point location;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public TooltipView(Context context, MapView mapView) {
        super(context);
        this.mapView = mapView;
        mapView.addView(this);
        this.getLayoutParams().width = 400;
        this.getLayoutParams().height = 100;
        this.setBackgroundColor(Color.WHITE);
        this.invalidate();
        defaultTextView = new TextView(context);
        defaultTextView.setGravity(Gravity.CENTER);
        defaultTextView.setText("this is a test");
        this.addView(defaultTextView);
        defaultTextView.getLayoutParams().width = 400;

    }


    public void setText(String title){

    }
    public void setPosition(GeoPoint point){
        calculatePoint(point);
        this.setPadding(location.x, location.y, 0, 0);
    }

    public void reconfigure(GeoPoint point, String title) {
        setText(title);
        setPosition(point);
    }

    private void calculatePoint(GeoPoint point){
        MapView.Projection projection = mapView.getProjection();
        projection.toPixels(point, location);
    }
}
