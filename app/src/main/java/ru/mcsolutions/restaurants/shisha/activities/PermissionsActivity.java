package ru.mcsolutions.restaurants.shisha.activities;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.mcsolutions.restaurants.shisha.R;

public class PermissionsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_CODE = 0;
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        mButton= (Button) findViewById(R.id.buttonp);
        mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

            ActivityCompat.requestPermissions(this,
                    new String[] {
                            Manifest.permission.INTERNET,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.GET_ACCOUNTS,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CALL_PHONE,
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION

                    },
                    PERMISSION_REQUEST_CODE);


    }
}
