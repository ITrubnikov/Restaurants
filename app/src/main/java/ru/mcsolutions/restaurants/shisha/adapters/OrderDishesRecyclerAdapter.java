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

    class Card{

        int portion;
        String idOrderDish;

        public Card(int portion){
            this.portion = portion;
            this.idOrderDish = null;
        }
        public Card(int portion, String idOrderDish){
            this.portion = portion;
            this.idOrderDish = idOrderDish;
        }
        public int getPortion(){return portion;}
        public String getIdOrderDish(){return idOrderDish;}
    }

    Context context;
    ArrayList<OrderDish> orderDishes;
    ArrayList<Portion> portions;
    AppCompatTextView textViewTotal;
    AppCompatTextView textViewPTotal;
    ArrayList<Card> cards;

    public OrderDishesRecyclerAdapter(Context context
            , ArrayList<OrderDish> orderDishes
            , ArrayList<Portion> portions
            , AppCompatTextView textViewTotal
            , AppCompatTextView textViewPTotal) {
        this.context = context;
        this.orderDishes = orderDishes;
        this.portions = portions;
        this.textViewTotal = textViewTotal;
        this.textViewPTotal = textViewPTotal;
        cards = new ArrayList<Card>();
        int s = portions.size();
        for(int i=0;i<s;i++){
            int portion = s - i;
            cards.add(new Card(portion));
            for(int j=0;j<orderDishes.size();j++){
                OrderDish orderDish = orderDishes.get(j);
                if(orderDish.getPortion() == portion){

                }
            }
        }
        cards.remove(0);
    }

    @Override
    public OrderDishesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(OrderDishesRecyclerAdapter.OrderDishesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
