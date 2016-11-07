package ru.mcsolutions.restaurants.shisha.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.adapters.DishesRecyclerAdapter;
import ru.mcsolutions.restaurants.shisha.tools.Global;

public class DishesActivity extends AppCompatActivity {

    Context context = this;
    String idDishType, dishType;
    AppCompatTextView textViewTotal;
    AppCompatTextView textViewPTotal;
    AppCompatTextView textViewCaption;
    AppCompatButton buttonOrder;
    RecyclerView recyclerView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);

        idDishType = getIntent().getStringExtra("idDishType");
        dishType = getIntent().getStringExtra("dishType");

        textViewCaption = (AppCompatTextView) findViewById(R.id.textViewCaption);
        textViewTotal = (AppCompatTextView) findViewById(R.id.textViewTotal);
        textViewPTotal = (AppCompatTextView) findViewById(R.id.textViewPTotal);
        buttonOrder = (AppCompatButton) findViewById(R.id.buttonOrder);

        textViewCaption.setText(dishType);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        DishesRecyclerAdapter dishesRecyclerAdapter = new DishesRecyclerAdapter(
                context, Global.currentOrder.dishes, "Меню", idDishType, textViewTotal, textViewPTotal);
        recyclerView.setAdapter(dishesRecyclerAdapter);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderActivity.class);
                startActivity(intent);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        textViewTotal.setText(Global.decimalFormat.format(Global.currentOrder.getTotal()));
        textViewPTotal.setText(Global.decimalFormat.format(Global.currentOrder.getPTotal()));
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Dishes Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}