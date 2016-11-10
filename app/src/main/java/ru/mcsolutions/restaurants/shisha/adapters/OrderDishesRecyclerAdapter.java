package ru.mcsolutions.restaurants.shisha.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.classes.OrderDish;
import ru.mcsolutions.restaurants.shisha.classes.Portion;

public class OrderDishesRecyclerAdapter extends RecyclerView.Adapter<OrderDishesRecyclerAdapter.OrderDishesViewHolder>{


    public class OrderDishesViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView textViewDish;
        AppCompatTextView textViewImageName;
        AppCompatButton buttonMinus, buttonPlus;
        AppCompatTextView textViewCount;

        AppCompatTextView textViewPortion, textViewPDate, textViewAmount;

        public OrderDishesViewHolder(View itemView) {
            super(itemView);
            this.textViewDish = (AppCompatTextView) itemView.findViewById(R.id.textViewDish);
            this.textViewImageName = (AppCompatTextView) itemView.findViewById(R.id.textViewImageName);
            this.buttonMinus = (AppCompatButton) itemView.findViewById(R.id.buttonMinus);
            this.buttonPlus = (AppCompatButton) itemView.findViewById(R.id.buttonPlus);
            this.textViewCount = (AppCompatTextView) itemView.findViewById(R.id.textViewCount);

            this.textViewPortion = (AppCompatTextView) itemView.findViewById(R.id.textViewPortion);
            this.textViewPDate = (AppCompatTextView) itemView.findViewById(R.id.textViewPDate);
            this.textViewAmount = (AppCompatTextView) itemView.findViewById(R.id.textViewAmount);
        }
    }

    Context context;
    ArrayList<OrderDish> orderDishes;
    ArrayList<Portion> portions;
    AppCompatTextView textViewTotal;
    AppCompatTextView textViewPTotal;

    public OrderDishesRecyclerAdapter(Context context
            , ArrayList<OrderDish> orderDishes
            , ArrayList<Portion> portions
            , AppCompatTextView textViewTotal
            , AppCompatTextView textViewPTotal) {
        this.context = context;
        this.orderDishes = orderDishes;
        this.textViewTotal = textViewTotal;
        this.textViewPTotal = textViewPTotal;
        portions = new ArrayList<Portion>();
//        portions.add(new Portion());
/*        ArrayList<Integer> pors = new ArrayList<Integer>();
        for(int i=0;i<orderDishes.size();i++){
            Portion portion = portions.get(i);
            int p = portion.getPortion();
            if(pors.indexOf(p)==0){
                pors.add(p);
                portions.add(new Portion(portion.getPortion(),portion.getDate(),portion.getAmount()));
            }
        }*/
    }

    @Override
    public OrderDishesRecyclerAdapter.OrderDishesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(OrderDishesRecyclerAdapter.OrderDishesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return orderDishes.size() + portions.size() - 1;
    }
}
