package ru.mcsolutions.restaurants.shisha.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.classes.Dish;
import ru.mcsolutions.restaurants.shisha.tools.ExpandAndCollapseViewUtil;
import ru.mcsolutions.restaurants.shisha.tools.Global;
import ru.mcsolutions.restaurants.shisha.tools.Utils;

import static ru.mcsolutions.restaurants.shisha.R.id.imageViewExpand;

public class DishesRecyclerAdapter extends RecyclerView.Adapter<DishesRecyclerAdapter.DishesViewHolder> {

    public class DishesViewHolder extends RecyclerView.ViewHolder{

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
         ViewGroup viewRoot;

        ViewGroup linearLayoutDetails;
        ImageView imageViewExpand;




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
            this.viewRoot=(ViewGroup)itemView.findViewById(R.id.linerDish);

            this.linearLayoutDetails = (ViewGroup) itemView.findViewById(R.id.linearLayoutDetails);
            this.imageViewExpand = (ImageView) itemView.findViewById(R.id.imageViewExpand);
        }
    }

    private final Context context;
    private final ArrayList<Dish> dishes;
    AppCompatTextView textViewTotal;
    AppCompatTextView textViewPTotal;
    private Resources resources;
    boolean sizeChanged=false;
    private int savedWidth;
    private static final int DURATION = 250;

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
        AppCompatTextView textViewName = viewHolder.textViewName;
        String name = dishes.get(position).getName();
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


        final ViewGroup viewRoot= viewHolder.viewRoot;
        final ViewGroup linearLayoutDetails=viewHolder.linearLayoutDetails;

        final AppCompatImageView imageView = viewHolder.imageView;
        int resourceId = resources.getIdentifier(imageName, "drawable", context.getPackageName());
        if(resourceId == 0){
            imageView.setImageDrawable(resources.getDrawable(R.drawable.food));
        }else{
            imageView.setImageDrawable(resources.getDrawable(resourceId));
        }

        AppCompatButton buttonMinus = viewHolder.buttonMinus;
        AppCompatButton buttonPlus = viewHolder.buttonPlus;
        final AppCompatTextView textViewCount = viewHolder.textViewCount;
        textViewCount.setText("0");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = imageView.getLayoutParams();

                /*LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)viewRoot.getLayoutParams();*/


                TransitionSet transition = new TransitionSet();
                ChangeBounds changeBounds = new ChangeBounds();
                changeBounds.setDuration(1500);
                Fade fadeOut = new Fade(Fade.OUT);
                fadeOut.setDuration(1500);
                Fade fadeIn = new Fade(Fade.IN);
                fadeIn.setDuration(1500);

                transition
                        .addTransition(fadeOut)
                        .addTransition(changeBounds)
                        .addTransition(fadeIn);


                TransitionManager.beginDelayedTransition(viewRoot,transition);



                if (sizeChanged) {
                    params.width = savedWidth;
                } else {
                    savedWidth = params.width;
                    params.width = 1000;
                    params.height=400;

                }
                sizeChanged = !sizeChanged;
                imageView.setLayoutParams(params);
               /* viewRoot.setLayoutParams(lp);*/


                if (linearLayoutDetails.getVisibility() == View.GONE) {
                    ExpandAndCollapseViewUtil.expand(linearLayoutDetails, DURATION);
                    imageViewExpand.setImageResource(R.mipmap.more);

                } else {
                    ExpandAndCollapseViewUtil.collapse(linearLayoutDetails, DURATION);
                    imageViewExpand.setImageResource(R.mipmap.less);

                }

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
