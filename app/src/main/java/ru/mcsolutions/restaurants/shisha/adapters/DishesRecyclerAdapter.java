package ru.mcsolutions.restaurants.shisha.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.mcsolutions.restaurants.shisha.classes.Dish;

public class DishesRecyclerAdapter extends RecyclerView.Adapter<DishesRecyclerAdapter.DishesViewHolder> {

    public class DishesViewHolder extends RecyclerView.ViewHolder{
        public DishesViewHolder(View itemView) {
            super(itemView);
        }
    }

    private final Context context;
    private final ArrayList<Dish> dishes;
    AppCompatTextView textViewTotal;
    AppCompatButton buttonTotal;

    public DishesRecyclerAdapter(
            Context context,
            ArrayList<Dish> dishes,
            String section,
            String idDishType,
            AppCompatTextView textViewTotal) {
        this.context = context;
        this.dishes = new ArrayList<Dish>();
        for(int i=0;i<dishes.size();i++){
            Dish dish = dishes.get(i);
            if(dish.getSection().equals(section) && dish.getIdDishType().equals(idDishType)){
                this.dishes.add(dish);
            }
        }
        this.textViewTotal = textViewTotal;
    }

    @Override
    public DishesRecyclerAdapter.DishesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DishesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

}
