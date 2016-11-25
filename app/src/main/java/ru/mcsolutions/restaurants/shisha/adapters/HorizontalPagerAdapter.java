package ru.mcsolutions.restaurants.shisha.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;
import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.tools.UtilsScrolling;

import static ru.mcsolutions.restaurants.shisha.tools.UtilsScrolling.setupItem;

/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class HorizontalPagerAdapter extends PagerAdapter {

    private final UtilsScrolling.LibraryObject[] LIBRARIES = new UtilsScrolling.LibraryObject[]{
            new UtilsScrolling.LibraryObject(
                    R.drawable.ic_strategy,
                    "Клуб1"
            ),
            new UtilsScrolling.LibraryObject(
                    R.drawable.ic_design,
                    "Клуб2"
            ),
            new UtilsScrolling.LibraryObject(
                    R.drawable.ic_development,
                    "Клуб3"
            ),
            new UtilsScrolling.LibraryObject(
                    R.drawable.ic_qa,
                    "Клуб4"
            )
    };

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private boolean mIsTwoWay;

    public HorizontalPagerAdapter(final Context context, final boolean isTwoWay) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIsTwoWay = isTwoWay;
    }

    @Override
    public int getCount() {
        return mIsTwoWay ? 6 : LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        if (mIsTwoWay) {
            view = mLayoutInflater.inflate(R.layout.change_location_activity_two_way_item, container, false);

            final VerticalInfiniteCycleViewPager verticalInfiniteCycleViewPager =
                    (VerticalInfiniteCycleViewPager) view.findViewById(R.id.vicvp);
            verticalInfiniteCycleViewPager.setAdapter(
                    new VerticalPagerAdapter(mContext)
            );
            verticalInfiniteCycleViewPager.setCurrentItem(position);
        } else {
            view = mLayoutInflater.inflate(R.layout.change_location_activity_item, container, false);
            CardView cardView = (CardView) view.findViewById(R.id.card_view);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"Выбран клуб "+ position,Toast.LENGTH_SHORT).show();
                }
            });

            setupItem(view, LIBRARIES[position]);
        }

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
