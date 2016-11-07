package ru.mcsolutions.restaurants.shisha.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.adapters.DishTypesRecyclerAdapter;
import ru.mcsolutions.restaurants.shisha.tools.Global;

public class DishTypesActivity extends AppCompatActivity {

    Context context = this;
    AppCompatTextView textViewTotal;
    AppCompatButton buttonOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_types);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        DishTypesRecyclerAdapter dishTypesRecyclerAdapter = new DishTypesRecyclerAdapter(context, Global.currentOrder.dishTypes);
        recyclerView.setAdapter(dishTypesRecyclerAdapter);

        textViewTotal = (AppCompatTextView) findViewById(R.id.textViewTotal);
        buttonOrder = (AppCompatButton) findViewById(R.id.buttonOrder);

    }
}
