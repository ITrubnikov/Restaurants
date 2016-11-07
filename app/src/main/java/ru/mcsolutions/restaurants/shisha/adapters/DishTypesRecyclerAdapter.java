package ru.mcsolutions.restaurants.shisha.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.activities.DishesActivity;
import ru.mcsolutions.restaurants.shisha.classes.DishType;

public class DishTypesRecyclerAdapter extends RecyclerView.Adapter<DishTypesRecyclerAdapter.DishTypesViewHolder> {

    public class DishTypesViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView textViewId;
        AppCompatTextView textViewName;
        AppCompatTextView textViewImageName;

        public DishTypesViewHolder(View itemView) {
            super(itemView);
            this.textViewId = (AppCompatTextView) itemView.findViewById(R.id.textViewId);
            this.textViewName = (AppCompatTextView) itemView.findViewById(R.id.textViewName);
            this.textViewImageName = (AppCompatTextView) itemView.findViewById(R.id.textViewImageName);
        }
    }

    private final Context context;
    private final ArrayList<DishType> dishTypes;

    public DishTypesRecyclerAdapter(Context context, ArrayList<DishType> dishTypes) {
        this.context = context;
        this.dishTypes = dishTypes;
    }

    @Override
    public DishTypesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dishtype, parent, false);
        DishTypesRecyclerAdapter.DishTypesViewHolder dishTypesViewHolder = new DishTypesRecyclerAdapter.DishTypesViewHolder(view);
        return dishTypesViewHolder;
    }

    @Override
    public void onBindViewHolder(DishTypesViewHolder viewHolder, int position) {
        AppCompatTextView textViewId = viewHolder.textViewId;
        final String idDishType = dishTypes.get(position).getId();
        textViewId.setText(idDishType);
        AppCompatTextView textViewName = viewHolder.textViewName;
        final String dishType = dishTypes.get(position).getName();
        textViewName.setText(dishType);
        AppCompatTextView textViewImageName = viewHolder.textViewImageName;
        textViewImageName.setText(dishTypes.get(position).getImageName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DishesActivity.class);
                intent.putExtra("idDishType", idDishType);
                intent.putExtra("dishType", dishType);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishTypes.size();
    }

}
