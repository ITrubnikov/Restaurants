package ru.mcsolutions.restaurants.shisha.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import ru.mcsolutions.restaurants.shisha.R;

import ru.mcsolutions.restaurants.shisha.activities.DishesActivity;
import ru.mcsolutions.restaurants.shisha.activities.DishesDetalActivity;
import ru.mcsolutions.restaurants.shisha.classes.Dish;

import ru.mcsolutions.restaurants.shisha.tools.Global;
import ru.mcsolutions.restaurants.shisha.tools.Utils;

import static com.stanko.tools.ResHelper.getString;
import static ru.mcsolutions.restaurants.shisha.adapters.DishesRecyclerAdapter.DishesViewHolder.DURATION;

public class DishesRecyclerAdapter extends RecyclerView.Adapter<DishesRecyclerAdapter.DishesViewHolder> {

    public class DishesViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView textViewID;

        AppCompatTextView textViewName;
        AppCompatTextView textViewImageName;
        AppCompatTextView textViewDescription;
        AppCompatTextView textViewPrice;
        AppCompatTextView textViewWeight;
        AppCompatTextView textViewMinutes;
        AppCompatImageView imageView;
        AppCompatButton buttonMinus;
        AppCompatButton buttonPlus;
        AppCompatTextView textViewCount;

      ViewGroup linearLayoutDetails;
         ImageView imageViewExpand;

       static final int DURATION = 250;

        public DishesViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (AppCompatTextView) itemView.findViewById(R.id.textViewName);
            this.textViewImageName = (AppCompatTextView) itemView.findViewById(R.id.textViewImageName);
            this.textViewDescription = (AppCompatTextView) itemView.findViewById(R.id.textViewDescription);
            this.textViewPrice = (AppCompatTextView) itemView.findViewById(R.id.textViewPrice);
            this.textViewWeight = (AppCompatTextView) itemView.findViewById(R.id.textViewWeight);
            this.textViewMinutes = (AppCompatTextView) itemView.findViewById(R.id.textViewMinutes);
            this.imageView = (AppCompatImageView) itemView.findViewById(R.id.imageView);
            this.buttonMinus = (AppCompatButton) itemView.findViewById(R.id.buttonMinus);
            this.buttonPlus = (AppCompatButton) itemView.findViewById(R.id.buttonPlus);
            this.textViewCount = (AppCompatTextView) itemView.findViewById(R.id.textViewCount);
           this.textViewID=(AppCompatTextView)itemView.findViewById(R.id.textViewId);


        }
    }

    private final Context context;
    private final ArrayList<Dish> dishes;
    AppCompatTextView textViewTotal;
    AppCompatTextView textViewPTotal;
    private Resources resources;

    public DishesRecyclerAdapter(
            Context context,
            ArrayList<Dish> allDishes,
            String section,
            String idDishType,
            AppCompatTextView textViewTotal,
            AppCompatTextView textViewPTotal) {
        this.context = context;
        dishes = new ArrayList<Dish>();
        Utils.log(section);
        Utils.log(idDishType);
        for(int i=0;i<allDishes.size();i++){
            Dish dish = allDishes.get(i);
            if(dish.getSection().equals(section) && dish.getIdDishType().equals(idDishType)){
                dishes.add(dish);
                Utils.log(dish.getName());
            }
        }
        Utils.log(dishes.size());
        this.textViewTotal = textViewTotal;
        this.textViewPTotal = textViewPTotal;
        resources =  context.getResources();
    }

    @Override
    public DishesRecyclerAdapter.DishesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dish, parent, false);
        DishesRecyclerAdapter.DishesViewHolder dishesViewHolder = new DishesRecyclerAdapter.DishesViewHolder(view);
        return dishesViewHolder;
    }

    @Override
    public void onBindViewHolder(DishesViewHolder viewHolder, final int position) {

        AppCompatTextView textViewId=viewHolder.textViewID;
        final String idDish = dishes.get(position).getIdDish();


        AppCompatTextView textViewName = viewHolder.textViewName;
        final String name = dishes.get(position).getName();
        textViewName.setText(name);

        AppCompatTextView textViewImageName = viewHolder.textViewImageName;
        String imageName = dishes.get(position).getImageName();
        textViewImageName.setText(imageName);

        AppCompatTextView textViewDescription = viewHolder.textViewDescription;
        String description = dishes.get(position).getDescription();
        textViewDescription.setText(description);

        AppCompatTextView textViewPrice = viewHolder.textViewPrice;
        Double price = dishes.get(position).getPrice();
        textViewPrice.setText("Цена " + Global.decimalFormat.format(price));

        AppCompatTextView textViewWeight = viewHolder.textViewWeight;
        int weight = dishes.get(position).getWeight();
        textViewWeight.setText("Вес " + weight);

        AppCompatTextView textViewMinutes = viewHolder.textViewMinutes;
        String minutes = dishes.get(position).getMinutes();
        textViewMinutes.setText("Время приготовления " + minutes + " минут");

        final AppCompatImageView imageView = viewHolder.imageView;
        final int resourceId = resources.getIdentifier(imageName, "drawable", context.getPackageName());
        if(resourceId == 0){
            imageView.setImageDrawable(resources.getDrawable(R.drawable.food));
        }else{
            imageView.setImageDrawable(resources.getDrawable(resourceId));
        }

        AppCompatButton buttonMinus = viewHolder.buttonMinus;
        AppCompatButton buttonPlus = viewHolder.buttonPlus;

        final ViewGroup linearLayoutDetails=viewHolder.linearLayoutDetails;
        final ImageView imageViewExpand=viewHolder.imageViewExpand;
        final AppCompatTextView textViewCount = viewHolder.textViewCount;
        textViewCount.setText("0");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DishesDetalActivity.class);
                intent.putExtra("idDish", idDish);
                intent.putExtra("resourceId",resourceId);

// Pass data object in the bundle and populate details activity.
                /*intent.putExtra("idDish", name);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context, imageView, getString(R.string.transition_image));*/
              /*  ActivityCompat.startActivity((Activity)context,intent, options.toBundle());*/
                context.startActivity(intent);





            }
        });



        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.valueOf(textViewCount.getText().toString());
                if(count>0){
                    count--;
                    textViewCount.setText("" + count);
                    String idDish = dishes.get(position).getIdDish();
                    Global.currentOrder.setDishCount(idDish, count);
                    textViewTotal.setText(Global.decimalFormat.format(Global.currentOrder.getTotal()));
                    Double pTotal = Global.currentOrder.getPTotal();
                    textViewPTotal.setText(Global.decimalFormat.format(pTotal));
                    Global.currentOrder.setPAmount(pTotal);
                    Utils.log(Global.currentOrder.getString());
                }
            }
        });
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.valueOf(textViewCount.getText().toString());
                count++;
                textViewCount.setText("" + count);
                String idDish = dishes.get(position).getIdDish();
                Global.currentOrder.setDishCount(idDish, count);
                textViewTotal.setText(Global.decimalFormat.format(Global.currentOrder.getTotal()));
                Double pTotal = Global.currentOrder.getPTotal();
                textViewPTotal.setText(Global.decimalFormat.format(pTotal));
                Global.currentOrder.setPAmount(pTotal);
                Utils.log(Global.currentOrder.getString());



            }
        });
    }


    @Override
    public int getItemCount() {
        return dishes.size();
    }

}
