package com.mapbox.mapboxsdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
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

    private TextView defaultTextView;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public TooltipView(Context context, MapView mapView) {
        super(context);
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

    }

    public void reconfigure(GeoPoint point, String title) {
        setText(title);
        setPosition(point);
    }
}
