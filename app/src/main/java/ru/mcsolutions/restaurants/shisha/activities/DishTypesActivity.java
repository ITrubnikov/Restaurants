package ru.mcsolutions.restaurants.shisha.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.adapters.DishTypesRecyclerAdapter;
import ru.mcsolutions.restaurants.shisha.tools.Global;

public class DishTypesActivity extends AppCompatActivity {

    public final static String ID = "ID";

    Context context = this;
    AppCompatTextView textViewCaption;
    AppCompatTextView textViewTotal;
    AppCompatTextView textViewPTotal;
    AppCompatButton buttonOrder;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_types);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        DishTypesRecyclerAdapter dishTypesRecyclerAdapter = new DishTypesRecyclerAdapter(context, Global.currentOrder.dishTypes);
        recyclerView.setAdapter(dishTypesRecyclerAdapter);

        textViewCaption = (AppCompatTextView) findViewById(R.id.textViewCaption);
        textViewTotal = (AppCompatTextView) findViewById(R.id.textViewTotal);
        textViewPTotal = (AppCompatTextView) findViewById(R.id.textViewPTotal);
        buttonOrder = (AppCompatButton) findViewById(R.id.buttonOrder);

        textViewCaption.setText("Меню");

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CurrentOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        textViewTotal.setText(Global.decimalFormat.format(Global.currentOrder.getTotal()));
        textViewPTotal.setText(Global.decimalFormat.format(Global.currentOrder.getPTotal()));
    }

}
