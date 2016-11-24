package ru.mcsolutions.restaurants.shisha.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ru.mcsolutions.restaurants.shisha.activities.AfishaFragments.TabFragment1;
import ru.mcsolutions.restaurants.shisha.activities.AfishaFragments.TabFragment2;
import ru.mcsolutions.restaurants.shisha.activities.AfishaFragments.TabFragment3;
import ru.mcsolutions.restaurants.shisha.activities.AfishaFragments.TabFragment4;
import ru.mcsolutions.restaurants.shisha.activities.AfishaFragments.TabFragment5;
import ru.mcsolutions.restaurants.shisha.activities.AfishaFragments.TabFragment6;
import ru.mcsolutions.restaurants.shisha.activities.AfishaFragments.TabFragment7;

public class AfishaPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public AfishaPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragment1 tab1 = new TabFragment1();
                return tab1;
            case 1:
                TabFragment2 tab2 = new TabFragment2();
                return tab2;
            case 2:
                TabFragment3 tab3 = new TabFragment3();
                return tab3;
            case 3:
                TabFragment4 tab4 = new TabFragment4();
                return tab4;
            case 4:
                TabFragment5 tab5 = new TabFragment5();
                return tab5;
            case 5:
                TabFragment6 tab6 = new TabFragment6();
                return tab6;
            case 6:
                TabFragment7 tab7 = new TabFragment7();
                return tab7;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}