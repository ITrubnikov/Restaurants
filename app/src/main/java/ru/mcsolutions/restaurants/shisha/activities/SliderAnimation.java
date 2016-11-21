package ru.mcsolutions.restaurants.shisha.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.common.collect.Lists;

import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;
import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.fragments.GuideFragment;

public class SliderAnimation extends AppCompatActivity {


        ScrollerViewPager viewPager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_slider_animation);

            viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
            SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
            Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
            setSupportActionBar(toolbar);


            PagerModelManager manager = new PagerModelManager();
            manager.addCommonFragment(GuideFragment.class, getBgRes(), getTitles());
            ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
            viewPager.setAdapter(adapter);
            viewPager.fixScrollSpeed();

            // just set viewPager
            springIndicator.setViewPager(viewPager);

        }

        private List<String> getTitles(){
            return Lists.newArrayList("1", "2", "3", "4");
        }

        private List<Integer> getBgRes(){
            return Lists.newArrayList(R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4);
        }

    }

