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
import ru.mcsolutions.restaurants.shisha.tools.Utils;

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
        Global.init(getApplicationContext());
        locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
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
        videoView.start();
        {
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
                                    if (result.startsWith("-1;")) {
                                        Toast.makeText(context, result.substring(3), Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Global.mainMenus = Global.parsers.getMainMenus(result);
                                    }
                                    break;
                            }
                        } catch (Exception e) {
                            Utils.log(e.getMessage());
                        }
                    }

                };
                internet.startURL("clients.getMainMenu", handler);
            } else {
                Toast.makeText(context, Global.INTERNET_NOT_AVAILABLE, Toast.LENGTH_LONG).show();
            }
        }
        {
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
                                    if (result.startsWith("-1;")) {
                                        Toast.makeText(context, result.substring(3), Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Global.currentOrder.orders = Global.parsers.getOrders(result);
                                    }
                                    break;
                            }
                        } catch (Exception e) {
                            Utils.log(e.getMessage());
                        }
                    }

                };
                internet.startURL("clients.getOrders", handler);
            } else {
                Toast.makeText(context, Global.INTERNET_NOT_AVAILABLE, Toast.LENGTH_LONG).show();
            }
        }
        {
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
                                    if (result.startsWith("-1;")) {
                                        Toast.makeText(context, result.substring(3), Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        if(result.startsWith("1;")){
                                            Global.clientName = result.substring(2);
                                            Utils.log(Global.clientName);
                                        }
                                    }
                                    break;
                            }
                        } catch (Exception e) {
                            Utils.log(e.getMessage());
                        }
                    }

                };
                internet.startURL("clients.getInfo", handler);
            } else {
                Toast.makeText(context, Global.INTERNET_NOT_AVAILABLE, Toast.LENGTH_LONG).show();
            }
        }
        {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(context, MainMenuActivity.class);
                    startActivity(intent);
                }
            };
            new Handler().postDelayed(runnable, 5000);//5 секунд
        }
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            try {
                getLocation(location);
            } catch (IOException e) {
                Utils.log(e.getMessage());
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
            try {
                getLocation(locationManager.getLastKnownLocation(provider));
            } catch (IOException e) {
                Utils.log(e.getMessage());
                e.printStackTrace();
            }
        }

        void getLocation(Location location) throws IOException {
            if (location == null) {
                return;
            } else {
                Utils.log("Real location: " + location.getLatitude() + ", " + location.getLongitude());
                if (
                        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        &&
                        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.removeUpdates(locationListener);
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
                                            Global.isIn = Global.location.getDistance()<100;
                                            if(Global.isIn){
                                                {
                                                    final Internet internet = new Internet(context);
                                                    Handler handler = new Handler() {
                                                        public void handleMessage(Message message) {
                                                            try {
                                                                switch (message.what) {
                                                                    case Global.HTTP_PENDING:
                                                                        break;
                                                                    case Global.HTTP_FINISHED:
                                                                        String result = internet.result;
                                                                        if (result.startsWith("-1;")) {
                                                                            Toast.makeText(context, result.substring(3), Toast.LENGTH_LONG).show();
                                                                            finish();
                                                                        } else {
                                                                            Global.currentOrder.dishes = Global.parsers.getDishes(result);
                                                                        }
                                                                        break;
                                                                }
                                                            } catch (Exception e) {
                                                                Utils.log(e.getMessage());
                                                            }
                                                        }

                                                    };
                                                    internet.addParamNameValue("idlocation", Global.location.getId());
                                                    internet.startURL("clients.getDishes", handler);
                                                }
                                                {
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
                                                                            if (result.startsWith("-1;")) {
                                                                                Toast.makeText(context, result.substring(3), Toast.LENGTH_LONG).show();
                                                                                finish();
                                                                            } else {
                                                                                Global.currentOrder.dishTypes = Global.parsers.getDishTypes(result);
                                                                            }
                                                                            break;
                                                                    }
                                                                } catch (Exception e) {
                                                                    Utils.log(e.getMessage());
                                                                }
                                                            }

                                                        };
                                                        internet.addParamNameValue("idlocation", Global.location.getId());
                                                        internet.startURL("clients.getDishTypes", handler);
                                                    } else {
                                                        Toast.makeText(context, Global.INTERNET_NOT_AVAILABLE, Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                }
                            } catch (Exception e) {
                                Utils.log(e.getMessage());
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

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
