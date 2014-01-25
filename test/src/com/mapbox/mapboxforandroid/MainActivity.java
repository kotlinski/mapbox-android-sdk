package com.mapbox.mapboxforandroid;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.mapbox.mapboxforandroid.utils.Chrono;
import com.mapbox.mapboxsdk.MapView;
import com.testflightapp.lib.TestFlight;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.PathOverlay;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.Date;
import java.util.logging.Logger;

public class MainActivity extends Activity {
	private IMapController mapController;
	private GeoPoint startingPoint = new GeoPoint(51f, 0f);
	private MapTileProviderBasic tileProvider;
	private MapView mv;
	private MyLocationNewOverlay myLocationOverlay;
    private Paint paint;
    private String satellite = "brunosan.map-cyglrrfu";
    private String street = "examples.map-vyofok3q";
    private String terrain = "examples.map-zgrqqx0w";
    private String currentLayer = "terrain";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        TestFlight.takeOff(getApplication(), "b1425515-299c-4aaf-b85e-b9a7c99b0fa5");
        double thisTime = System.nanoTime();
        setContentView(R.layout.activity_main);
        Chrono.track("setContentView",thisTime);
        mv = (MapView)findViewById(R.id.mapview);
        mapController = mv.getController();
        mapController.setCenter(startingPoint);
        mapController.setZoom(4);
        mv.parseFromGeoJSON("https://gist.github.com/fdansv/8541618/raw/09da8aef983c8ffeb814d0a1baa8ecf563555b5d/geojsonpointtest");
        setButtonListeners();

    }

    private void setButtonListeners() {
        Button satBut = changeButtonTypeface((Button)findViewById(R.id.satbut));
        satBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!currentLayer.equals("satellite")){
                    replaceMapView(satellite);
                    currentLayer = "satellite";
                }
            }
        });
        Button terBut = changeButtonTypeface((Button)findViewById(R.id.terbut));
        terBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!currentLayer.equals("terrain"))
                {
                    replaceMapView(terrain);
                    currentLayer = "terrain";
                }
            }
        });
        Button strBut = changeButtonTypeface((Button)findViewById(R.id.strbut));
        strBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!currentLayer.equals("street")){
                    replaceMapView(street);
                    currentLayer = "street";
                }
            }
        });
    }

    protected void replaceMapView(String layer){
        mv.switchToLayer(layer);

    }

    private void addLocationOverlay(){
        // Adds an icon that shows location
        myLocationOverlay = new MyLocationNewOverlay(this, mv);
        myLocationOverlay.enableMyLocation();
        myLocationOverlay.setDrawAccuracyEnabled(true);
        mv.getOverlays().add(myLocationOverlay);
    }
    private void addLine(){
        // Configures a line
        PathOverlay po = new PathOverlay(Color.RED, this);
        Paint linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.BLUE);
        linePaint.setStrokeWidth(5);
        po.setPaint(linePaint);
        po.addPoint(startingPoint);
        po.addPoint(new GeoPoint(51.7, 0.3));
        po.addPoint(new GeoPoint(51.2, 0));

        // Adds line and marker to the overlay
        mv.getOverlays().add(po);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}
    private Button changeButtonTypeface(Button button){
        Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/semibold.ttf");
        button.setTypeface(tf);
        return button;
    }


}
