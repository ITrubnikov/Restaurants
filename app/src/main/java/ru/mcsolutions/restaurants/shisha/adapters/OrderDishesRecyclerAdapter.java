package ru.mcsolutions.restaurants.shisha.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.classes.Dish;
import ru.mcsolutions.restaurants.shisha.classes.OrderDish;
import ru.mcsolutions.restaurants.shisha.classes.Portion;
import ru.mcsolutions.restaurants.shisha.tools.Global;
import ru.mcsolutions.restaurants.shisha.tools.Utils;

public class OrderDishesRecyclerAdapter extends RecyclerView.Adapter<OrderDishesRecyclerAdapter.OrderDishesViewHolder>{


    public class OrderDishesViewHolder extends RecyclerView.ViewHolder{

        CardView cardViewPortion;
        AppCompatTextView textViewPortion, textViewPDate, textViewAmount;

        CardView cardViewDish;
        AppCompatTextView textViewDish;
        AppCompatTextView textViewImageName, textViewPrice, textViewWeight;
        AppCompatImageView imageView;
        AppCompatButton buttonMinus, buttonPlus;
        AppCompatTextView textViewCount;

        public OrderDishesViewHolder(View itemView) {
            super(itemView);
            this.cardViewPortion = (CardView) itemView.findViewById(R.id.cardViewPortion);
            this.textViewPortion = (AppCompatTextView) itemView.findViewById(R.id.textViewPortion);
            this.textViewPDate = (AppCompatTextView) itemView.findViewById(R.id.textViewPDate);
            this.textViewAmount = (AppCompatTextView) itemView.findViewById(R.id.textViewAmount);

            this.cardViewDish = (CardView) itemView.findViewById(R.id.cardViewDish);
            this.textViewDish = (AppCompatTextView) itemView.findViewById(R.id.textViewDish);
            this.textViewImageName = (AppCompatTextView) itemView.findViewById(R.id.textViewImageName);
            this.textViewPrice = (AppCompatTextView) itemView.findViewById(R.id.textViewPrice);
            this.textViewWeight = (AppCompatTextView) itemView.findViewById(R.id.textViewWeight);
            this.imageView = (AppCompatImageView) itemView.findViewById(R.id.imageView);
            this.buttonMinus = (AppCompatButton) itemView.findViewById(R.id.buttonMinus);
            this.buttonPlus = (AppCompatButton) itemView.findViewById(R.id.buttonPlus);
            this.textViewCount = (AppCompatTextView) itemView.findViewById(R.id.textViewCount);

        }
    }

    class Card{

        int portion;
        OrderDish orderDish;

        public Card(int portion){
            this.portion = portion;
            this.orderDish = null;
        }
        public Card(int portion, OrderDish orderDish){
            this.portion = portion;
            this.orderDish = orderDish;
        }
        public int getPortion(){return portion;}
        public OrderDish getOrderDish(){return orderDish;}
    }

    Context context;
    Resources resources;

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
        resources = context.getResources();
        this.orderDishes = orderDishes;
        this.portions = portions;
        this.textViewTotal = textViewTotal;
        this.textViewPTotal = textViewPTotal;
        cards = new ArrayList<Card>();
        int s = portions.size();
        for(int i=0;i<s;i++){
            int portion = s - i;
            cards.add(new Card(portion));
            for(int j=0;j<this.orderDishes.size();j++){
                OrderDish orderDish = this.orderDishes.get(j);
                if(orderDish.getPortion() == portion){
                    cards.add(new Card(portion, orderDish));
                }
            }
        }
        cards.remove(0);//последняя порция без заголовка
    }

    @Override
    public OrderDishesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orderdish, parent, false);
        OrderDishesRecyclerAdapter.OrderDishesViewHolder orderDishesViewHolder = new OrderDishesRecyclerAdapter.OrderDishesViewHolder(view);
        return orderDishesViewHolder;
    }

    @Override
    public void onBindViewHolder(OrderDishesRecyclerAdapter.OrderDishesViewHolder viewHolder, final int position) {
        Card card = cards.get(position);
        int p = card.getPortion();
        Portion portion = portions.get(p-1);
        if(card.getOrderDish()==null){
            viewHolder.cardViewPortion.setVisibility(View.VISIBLE);
            viewHolder.cardViewDish.setVisibility(View.GONE);
            viewHolder.textViewPortion.setText("# " + portion.getPortion());
            if(portion.getPDate()!=null){
                viewHolder.textViewPDate.setText(Global.simpleDateTimeFormat.format(portion.getPDate()));
            }
            viewHolder.textViewAmount.setText(Global.decimalFormat.format(portion.getAmount()));
        }else{
            OrderDish orderDish = card.getOrderDish();
            viewHolder.cardViewPortion.setVisibility(View.GONE);
            viewHolder.cardViewDish.setVisibility(View.VISIBLE);
            Dish dish = Global.currentOrder.getDish(orderDish.getIdDish());
            viewHolder.textViewDish.setText(dish.getName());
            viewHolder.textViewPrice.setText(Global.decimalFormat.format(dish.getPrice()));
            viewHolder.textViewWeight.setText(dish.getWeight()+"");
            viewHolder.textViewDish.setText(dish.getName());
            String imageName = dish.getImageName();
            AppCompatImageView imageView = viewHolder.imageView;
            int resourceId = resources.getIdentifier(imageName, "drawable", context.getPackageName());
            if(resourceId == 0){
                imageView.setImageDrawable(resources.getDrawable(R.drawable.food));
            }else{
                imageView.setImageDrawable(resources.getDrawable(resourceId));
            }

            AppCompatButton buttonMinus = viewHolder.buttonMinus;
            AppCompatButton buttonPlus = viewHolder.buttonPlus;
            final AppCompatTextView textViewCount = viewHolder.textViewCount;
            viewHolder.textViewCount.setText(orderDish.getCount() + "");

            if(orderDish.getPortion() == Global.currentOrder.getCurrentPortion()){
                buttonMinus.setVisibility(View.VISIBLE);
                buttonPlus.setVisibility(View.VISIBLE);
                buttonMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int count = Integer.valueOf(textViewCount.getText().toString());
                        if(count>0){
                            count--;
                            textViewCount.setText("" + count);
                            String idDish = orderDishes.get(position).getIdDish();
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
                        String idDish = orderDishes.get(position).getIdDish();
                        Global.currentOrder.setDishCount(idDish, count);
                        textViewTotal.setText(Global.decimalFormat.format(Global.currentOrder.getTotal()));
                        Double pTotal = Global.currentOrder.getPTotal();
                        textViewPTotal.setText(Global.decimalFormat.format(pTotal));
                        Global.currentOrder.setPAmount(pTotal);
                        Utils.log(Global.currentOrder.getString());
                    }
                });
            }else{
                buttonMinus.setVisibility(View.INVISIBLE);
                buttonPlus.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
