package ru.mcsolutions.restaurants.shisha.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.adapters.MainPagerAdapter;

public class ChangeLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_location_activity);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_main);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);

        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts);
        navigationTabStrip.setTitles("HOW WE WORK", "WE WORK WITH");
        navigationTabStrip.setViewPager(viewPager);
    }
}
