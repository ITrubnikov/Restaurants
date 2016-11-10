package ru.mcsolutions.restaurants.shisha.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.activities.DishTypesActivity;
import ru.mcsolutions.restaurants.shisha.classes.MainMenu;

import static android.support.v7.appcompat.R.id.icon;
import static android.support.v7.appcompat.R.id.image;

public class MainMenusRecyclerAdapter extends RecyclerView.Adapter<MainMenusRecyclerAdapter.MainMenusViewHolder> {

    public class MainMenusViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView textViewId;
        AppCompatTextView textViewName;
        AppCompatTextView textViewImageName;
        ImageView icon;

        public MainMenusViewHolder(View itemView) {
            super(itemView);
            this.textViewId = (AppCompatTextView) itemView.findViewById(R.id.textViewId);
            this.textViewName = (AppCompatTextView) itemView.findViewById(R.id.textViewName);
            this.textViewImageName = (AppCompatTextView) itemView.findViewById(R.id.textViewImageName);
            this.icon = (ImageView) itemView.findViewById(R.id.listIcon);

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
        AppCompatTextView textViewId = viewHolder.textViewId;
        textViewId.setText(mainMenus.get(position).getId());
        AppCompatTextView textViewName = viewHolder.textViewName;
        final String name = mainMenus.get(position).getName();
        textViewName.setText(name);
        AppCompatTextView textViewImageName = viewHolder.textViewImageName;
        textViewImageName.setText(mainMenus.get(position).getImageName());
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

        MainMenu current= mainMenus.get(position);



        Uri imgUri=Uri.parse("android.resource://" + context.getPackageName() + "/" + current.getImageName());

        viewHolder.icon.setImageURI(imgUri);


      /*  String imageName = mainMenus.get(position).getImageName();
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(imageName, "drawable", context.getPackageName());
        Drawable icon = null;
        if (resourceId == 0) {
            viewHolder.icon = resources.getDrawable(R.drawable.food);
        } else {
            viewHolder.icon = resources.getDrawable(resourceId);
        }*/


    }

    @Override
    public int getItemCount() {
        return mainMenus.size();
    }

}
