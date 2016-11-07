package ru.mcsolutions.restaurants.shisha.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.adapters.DishesRecyclerAdapter;
import ru.mcsolutions.restaurants.shisha.tools.Global;
import ru.mcsolutions.restaurants.shisha.tools.Utils;

public class DishesActivity extends AppCompatActivity {

    Context context = this;
    String idDishType, dishType;
    AppCompatTextView textViewTotal;
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
        Utils.log(idDishType);
        dishType = getIntent().getStringExtra("dishType");

        textViewTotal = (AppCompatTextView) findViewById(R.id.textViewTotal);
        buttonOrder = (AppCompatButton) findViewById(R.id.buttonOrder);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewDishes);
if(recyclerView == null){
    Utils.log("recyclerView is null");
}
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
Utils.log(Global.currentOrder.dishes.size());
        DishesRecyclerAdapter dishesRecyclerAdapter = new DishesRecyclerAdapter(
                context, Global.currentOrder.dishes, "Меню", idDishType, textViewTotal);
        recyclerView.setAdapter(dishesRecyclerAdapter);



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        textViewTotal.setText(Global.decimalFormat.format(Global.currentOrder.getTotal()));
    }
*/
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
