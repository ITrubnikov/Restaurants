package ru.mcsolutions.restaurants.shisha.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.adapters.MainMenusRecyclerAdapter;
import ru.mcsolutions.restaurants.shisha.tools.Global;

public class MainMenuActivity extends AppCompatActivity {

    Context context = this;
    TextView textViewLocation;

    private FloatingActionMenu menuBlue;
    private com.github.clans.fab.FloatingActionButton fab1;
    private com.github.clans.fab.FloatingActionButton fab2;
    private com.github.clans.fab.FloatingActionButton fabEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        MainMenusRecyclerAdapter mainMenusRecyclerAdapter = new MainMenusRecyclerAdapter(context, Global.mainMenus);
        recyclerView.setAdapter(mainMenusRecyclerAdapter);

        /*textViewLocation = (TextView) findViewById(R.id.textViewLocation);
        textViewLocation.setText(Global.location.getName());*/


        Typeface myTaypeface=Typeface.createFromAsset(getAssets(),"NeuchaRegular.ttf");
        textViewLocation = (TextView) findViewById(R.id.textViewLocation);
        textViewLocation.setTypeface(myTaypeface);
        textViewLocation.setText(Global.location.getName());



        menuBlue = (FloatingActionMenu) findViewById(R.id.menu_blue);

        menuBlue.setIconAnimated(true);
        menuBlue.setClosedOnTouchOutside(true);
        fab1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab2);

        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);




    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab1:
                    Toast.makeText(context, "Нажат фаб 1", Toast.LENGTH_LONG).show();
                    break;
                case R.id.fab2:
                    Toast.makeText(context, "Нажат фаб 2", Toast.LENGTH_LONG).show();
                    break;

            }
        }
    };

}
