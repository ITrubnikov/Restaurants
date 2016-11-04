package ru.mcsolutions.restaurants.shisha.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.tools.Global;
import ru.mcsolutions.restaurants.shisha.tools.Internet;

public class SplashActivity extends AppCompatActivity {
    VideoView videoView;
    View decorView;
    LocationManager locationManager;
    Context context = this;
    TextView textViewGPSCaption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.shi));
        decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // прячем панель навигации
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // прячем строку состояния
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, MainMenuActivity.class);
                startActivity(intent);
            }
        }, 10000);//5 секунд

        Global.init(getApplicationContext());
        locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        &&
                        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Нет разрешений на GPS и GPS от сети", Toast.LENGTH_LONG).show();
            finish();
        } else {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 1000 * 10, 10, locationListener);
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 1000 * 10, 10, locationListener);
        }
//        videoView.start();

        final Internet internet = new Internet(context);
        if (internet.isExists()) {
            Handler handler = new Handler() {
                public void handleMessage(Message message) {
                    try {
                        switch (message.what) {
                            case Global.HTTP_PENDING:
                                break;
                            case Global.HTTP_FINISHED:
                                String result = internet.result;
                                if(result.startsWith("-1;")){
                                    Toast.makeText(context, result.substring(3), Toast.LENGTH_LONG).show();
                                    finish();
                                }else{
                                    Global.mainMenus = Global.parsers.getMainMenus(result);
                                }
                                break;
                        }
                    } catch (Exception e) {

                    }
                }

            };
            internet.startURL("clients.getMainMenu", handler);
        } else {
            Toast.makeText(context, Global.INTERNET_NOT_AVAILABLE, Toast.LENGTH_LONG).show();
        }
    }
/*
    @Override
    protected void onStop(){
        super.onStop();
        finish();
    }
*/
    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            try {
                getLocation(location);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderDisabled(String provider) {
//            checkEnabled();
        }

        @Override
        public void onProviderEnabled(String provider) {
//            checkEnabled();
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, Global.GPS_NOT_AVAILABLE, Toast.LENGTH_LONG).show();
                return;
            }
            //Toast.makeText(context, provider, Toast.LENGTH_LONG).show();
            try {
                getLocation(locationManager.getLastKnownLocation(provider));
            } catch (IOException e) {
                e.printStackTrace();
            }
//            locationManager.removeUpdates(this);
        }

        void getLocation(Location location) throws IOException {
            if (location == null) {
                return;
            } else {
Toast.makeText(context, location.getLatitude()+", "+location.getLongitude(), Toast.LENGTH_LONG);

//                Global.latitude = location.getLatitude();
//                Global.longitude = location.getLongitude();
//Тестирование. Рядом в Happy Bar
                Global.latitude = 55.7676;
                Global.longitude = 37.5596;
                final Internet internet = new Internet(context);
                if (internet.isExists()) {
                    Handler handler = new Handler() {
                        public void handleMessage(Message message) {
                            try {
                                switch (message.what) {
                                    case Global.HTTP_PENDING:
                                        break;
                                    case Global.HTTP_FINISHED:
                                        String result = internet.result;
                                        if(result.startsWith("-1;")){
                                            Toast.makeText(context, result.substring(3), Toast.LENGTH_LONG).show();
                                            finish();
                                        }else{
                                            Global.currentOrder.locations = Global.parsers.getLocations(result);
                                            Global.location = Global.currentOrder.locations.get(0);
Toast.makeText(context, Global.location.getName(), Toast.LENGTH_LONG);
                                            Global.canOrder = Global.location.getDistance()<100;
                                        }
                                        break;
                                }
                            } catch (Exception e) {

                            }
                        }

                    };
                    internet.addDoubleParamNameValue("latitude", Global.latitude);
                    internet.addDoubleParamNameValue("longitude", Global.longitude);
                    internet.startURL("clients.getLocations", handler);
                } else {
                    Toast.makeText(context, Global.INTERNET_NOT_AVAILABLE, Toast.LENGTH_LONG).show();
                }
            }
        }
    };

}
