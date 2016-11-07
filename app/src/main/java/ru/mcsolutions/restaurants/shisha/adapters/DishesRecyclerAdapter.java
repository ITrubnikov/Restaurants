package ru.mcsolutions.restaurants.shisha.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.classes.Dish;
import ru.mcsolutions.restaurants.shisha.tools.Utils;

public class DishesRecyclerAdapter extends RecyclerView.Adapter<DishesRecyclerAdapter.DishesViewHolder> {

    public class DishesViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView textViewName;
        AppCompatTextView textViewImageName;

        public DishesViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (AppCompatTextView) itemView.findViewById(R.id.textViewName);
            this.textViewImageName = (AppCompatTextView) itemView.findViewById(R.id.textViewImageName);
        }
    }

    private final Context context;
    private final ArrayList<Dish> dishes;
    AppCompatTextView textViewTotal;

    public DishesRecyclerAdapter(
            Context context,
            ArrayList<Dish> allDishes,
            String section,
            String idDishType,
            AppCompatTextView textViewTotal) {
        this.context = context;
//        this.dishes = new ArrayList<Dish>();
        this.dishes = allDishes;
        try{
            Utils.log(context.toString());
            if(allDishes == null){
                Utils.log("allDishes is null");
            }else{
                Utils.log(allDishes.toString());
            }
            Utils.log(section);
            Utils.log(idDishType);
            Utils.log(textViewTotal.toString());
        }catch (Exception e){
            Utils.log(e.getMessage());
        }
/*        for(int i=0;i<allDishes.size();i++){
            Dish dish = allDishes.get(i);
            if(dish.getSection().equals(section) && dish.getIdDishType().equals(idDishType)){
                this.dishes.add(dish);
            }
        }*/
        Utils.log(this.dishes.size()+"");
        this.textViewTotal = textViewTotal;
    }

    @Override
    public DishesRecyclerAdapter.DishesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dish, parent, false);
        DishesRecyclerAdapter.DishesViewHolder dishesViewHolder = new DishesRecyclerAdapter.DishesViewHolder(view);
        return dishesViewHolder;
    }

    @Override
    public void onBindViewHolder(DishesViewHolder viewHolder, int position) {
        AppCompatTextView textViewName = viewHolder.textViewName;
        final String name = dishes.get(position).getName();
        textViewName.setText(name);
        AppCompatTextView textViewImageName = viewHolder.textViewImageName;
        final String imageName = dishes.get(position).getImageName();
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

}
