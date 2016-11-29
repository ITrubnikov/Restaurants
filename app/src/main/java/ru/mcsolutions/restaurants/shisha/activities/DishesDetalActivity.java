package ru.mcsolutions.restaurants.shisha.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.classes.Dish;
import ru.mcsolutions.restaurants.shisha.tools.Global;

/**
 * Created by Ivan on 29.11.16.
 */

public class DishesDetalActivity extends AppCompatActivity {
    String idDish;
    int resourceId;
    TextView mTextView;
    ImageView mImageView;
    private Resources resources;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dishes_detal_activity);

        mTextView = (TextView) findViewById(R.id.textView2);
        mImageView=(ImageView) findViewById(R.id.imageView3);







        Intent intent = getIntent();

         idDish = intent.getStringExtra("idDish");
        resourceId=intent.getIntExtra("resourceId");





        mTextView.setText( idDish );


        if(resourceId == 0){
            mImageView.setImageDrawable(resources.getDrawable(R.drawable.food));
        }else{
            mImageView.setImageDrawable(resources.getDrawable(resourceId));
        }


    }
}
