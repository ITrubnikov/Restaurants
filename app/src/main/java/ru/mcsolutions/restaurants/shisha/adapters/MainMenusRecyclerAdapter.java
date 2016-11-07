package ru.mcsolutions.restaurants.shisha.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.activities.DishTypesActivity;
import ru.mcsolutions.restaurants.shisha.classes.MainMenu;

public class MainMenusRecyclerAdapter extends RecyclerView.Adapter<MainMenusRecyclerAdapter.MainMenusViewHolder> {

    public class MainMenusViewHolder extends RecyclerView.ViewHolder {

        TextView textViewId;
        TextView textViewName;

        public MainMenusViewHolder(View itemView) {
            super(itemView);
            this.textViewId = (TextView) itemView.findViewById(R.id.textViewId);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
        }
    }

    private final Context context;
    private final ArrayList<MainMenu> mainMenus;

    public MainMenusRecyclerAdapter(Context context, ArrayList<MainMenu> mainMenus) {
        this.context = context;
        this.mainMenus = mainMenus;
    }

    @Override
    public MainMenusViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainmenu, parent, false);
        MainMenusViewHolder mainMenusViewHolder = new MainMenusViewHolder(view);
        return mainMenusViewHolder;
    }

    @Override
    public void onBindViewHolder(final MainMenusViewHolder viewHolder, final int position) {
        TextView textViewId = viewHolder.textViewId;
        textViewId.setText(mainMenus.get(position).getId());
        TextView textViewName = viewHolder.textViewName;
        final String name = mainMenus.get(position).getName();
        textViewName.setText(name);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(name){
//                    case "Афиша":
//                      break;
                    case "Меню":
                        Intent intent = new Intent(context, DishTypesActivity.class);
                        context.startActivity(intent);
                        break;
                    case "Мои заказы":
                        break;
                    default:
                        Toast.makeText(context, name + " еще не разработано", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mainMenus.size();
    }

}
