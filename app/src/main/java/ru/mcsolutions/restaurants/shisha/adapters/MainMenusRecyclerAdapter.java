package ru.mcsolutions.restaurants.shisha.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.activities.AfishaActivity;
import ru.mcsolutions.restaurants.shisha.activities.DishTypesActivity;
import ru.mcsolutions.restaurants.shisha.classes.MainMenu;

public class MainMenusRecyclerAdapter extends RecyclerView.Adapter<MainMenusRecyclerAdapter.MainMenusViewHolder> {

    public class MainMenusViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView textViewId;
        AppCompatTextView textViewName;
        AppCompatTextView textViewImageName;
        AppCompatImageView imageView;
        CardView cardView;

        public MainMenusViewHolder(View itemView) {
            super(itemView);
            this.textViewId = (AppCompatTextView) itemView.findViewById(R.id.textViewId);
            this.textViewName = (AppCompatTextView) itemView.findViewById(R.id.textViewName);
            this.textViewImageName = (AppCompatTextView) itemView.findViewById(R.id.textViewImageName);
            this.imageView = (AppCompatImageView) itemView.findViewById(R.id.imageView);
            cardView=(CardView) itemView.findViewById(R.id.cardViewMainMenum);
        }
    }

    private final Context context;
    private final ArrayList<MainMenu> mainMenus;
    Resources resources;

    public MainMenusRecyclerAdapter(Context context, ArrayList<MainMenu> mainMenus) {
        this.context = context;
        this.mainMenus = mainMenus;
        resources =  context.getResources();
    }

    @Override
    public MainMenusViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainmenu, parent, false);
        MainMenusViewHolder mainMenusViewHolder = new MainMenusViewHolder(view);
        return mainMenusViewHolder;
    }

    @Override
    public void onBindViewHolder(final MainMenusViewHolder viewHolder, final int position) {

        YoYo.with(Techniques.FadeIn).duration(1500).playOn(viewHolder.cardView);


        Typeface myTaypeface=Typeface.createFromAsset(context.getAssets(),"NeuchaRegular.ttf");//подключение стиля
        AppCompatTextView textViewId = viewHolder.textViewId;
        textViewId.setText(mainMenus.get(position).getId());
        AppCompatTextView textViewName = viewHolder.textViewName;

        final String name = mainMenus.get(position).getName();
        textViewName.setTypeface(myTaypeface);
        textViewName.setText(name);

        AppCompatTextView textViewImageName = viewHolder.textViewImageName;
        String imageName = mainMenus.get(position).getImageName();
        textViewImageName.setText(imageName);

        AppCompatImageView imageView = viewHolder.imageView;
        int resourceId = resources.getIdentifier(imageName, "drawable", context.getPackageName());
        if(resourceId == 0){
            imageView.setImageDrawable(resources.getDrawable(R.drawable.food));
        }else{
            imageView.setImageDrawable(resources.getDrawable(resourceId));
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(name){
//                    case "Афиша":
//                      break;
                    case "Меню":
                        Intent intent = new Intent(context, DishTypesActivity.class);
                        //context.startActivity(intent);

                        ActivityOptionsCompat transitionActivityOptions =
                                ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, viewHolder.textViewName, "NameTT");
                        context.startActivity(intent, transitionActivityOptions.toBundle());
                        break;
                    case "Афиша":
                        Intent intent1 = new Intent(context, AfishaActivity.class);
                        context.startActivity(intent1);
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
