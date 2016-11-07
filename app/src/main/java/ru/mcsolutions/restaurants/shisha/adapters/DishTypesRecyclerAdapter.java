package ru.mcsolutions.restaurants.shisha.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.activities.DishesActivity;
import ru.mcsolutions.restaurants.shisha.classes.DishType;

public class DishTypesRecyclerAdapter extends RecyclerView.Adapter<DishTypesRecyclerAdapter.DishTypesViewHolder> {

    public class DishTypesViewHolder extends RecyclerView.ViewHolder {

        TextView textViewId;
        TextView textViewName;
        TextView textViewImageName;

        public DishTypesViewHolder(View itemView) {
            super(itemView);
            this.textViewId = (TextView) itemView.findViewById(R.id.textViewId);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewImageName = (TextView) itemView.findViewById(R.id.textViewImageName);
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
        TextView textViewId = viewHolder.textViewId;
        textViewId.setText(dishTypes.get(position).getId());
        TextView textViewName = viewHolder.textViewName;
        final String name = dishTypes.get(position).getName();
        textViewName.setText(name);
        TextView textViewImageName = viewHolder.textViewImageName;
        textViewImageName.setText(dishTypes.get(position).getImageName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DishesActivity.class);
                intent.putExtra("dishType", name);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishTypes.size();
    }

}
