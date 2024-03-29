package ru.mcsolutions.restaurants.shisha.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.adapters.AfishaPagerAdapter;

/**
 * Created by Ivan on 22.11.16.
 */

public class AfishaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afisha);



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Понедельник"));
        tabLayout.addTab(tabLayout.newTab().setText("Вторник"));
        tabLayout.addTab(tabLayout.newTab().setText("Среда"));
        tabLayout.addTab(tabLayout.newTab().setText("Четверг"));
        tabLayout.addTab(tabLayout.newTab().setText("Пятница"));
        tabLayout.addTab(tabLayout.newTab().setText("Суббота"));
        tabLayout.addTab(tabLayout.newTab().setText("Воскресенье"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final AfishaPagerAdapter adapter = new AfishaPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}