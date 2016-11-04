package ru.mcsolutions.restaurants.shisha.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.adapters.MainMenusRecyclerAdapter;
import ru.mcsolutions.restaurants.shisha.tools.Global;

public class MainMenuActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        MainMenusRecyclerAdapter mainMenusRecyclerAdapter = new MainMenusRecyclerAdapter(context, Global.mainMenus);
        recyclerView.setAdapter(mainMenusRecyclerAdapter);
    }
}