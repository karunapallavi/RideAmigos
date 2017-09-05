package com.example.karuna.rideamigos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends Activity {
    
    String la = "", lo = "";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLocation = (Button) findViewById(R.id.button);
        Button btnStop = (Button) findViewById(R.id.button2);
        //Button btnStart = (Button)findViewById(R.id.button);
        final CountDownTimer cdtimer = new CountDownTimer(15000, 1000) { 
            //initializing the timer
            @Override
            public void onTick(long millisUntilFinished) {
                postData(la, lo); //call to post lat and long to the URL
            }

            @Override
            public void onFinish() {

            }
        };

        btnLocation.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                // Acquire a reference to the system Location Manager
                LocationManager locationManager = (LocationManager) MainActivity.this.getSystemService(Context.LOCATION_SERVICE);
                // Define a listener that responds to location updates
                LocationListener locationListener = new LocationListener() {
                    public void onLocationChanged(Location location) {
                        // Called when a new location is found by the network location provider.
                        la = Double.toString(location.getLatitude());
                        lo = Double.toString(location.getLongitude());
                        TextView tv = (TextView) findViewById(R.id.textView);
                        //added reference to view the location real time on the device
                        tv.setText("Location:" + la + " " + lo);
                    }

                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }

                    public void onProviderEnabled(String provider) {
                    }

                    public void onProviderDisabled(String provider) {
                    }
                };
                // Register the listener with the Location Manager to receive location updates
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                //can request for GPS location and use the one with best accuracy
            }
        });

        Button btnStart = (Button) findViewById(R.id.button);
        //start button
        btnStart.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                postData(la, lo);
                cdtimer.start();
            }



        });

        btnStop.setOnClickListener(new OnClickListener() {
            //stop button
            @Override
            public void onClick(View v) {
                cdtimer.cancel();
            }
        });

    }


    public void postData(String la, String lo) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet htget = new HttpGet("<add url here>" + la + "/" + lo); // add URL here 

        try {
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(htget);
            String resp = response.getStatusLine().toString();
            Toast.makeText(this, resp, 5000).show();


        } catch (ClientProtocolException e) {
            Toast.makeText(this, "Error", 5000).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error", 5000).show();
        }
    }

    
}
