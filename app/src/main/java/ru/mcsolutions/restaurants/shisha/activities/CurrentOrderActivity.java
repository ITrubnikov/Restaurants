package ru.mcsolutions.restaurants.shisha.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.adapters.OrderDishesRecyclerAdapter;
import ru.mcsolutions.restaurants.shisha.tools.Global;
import ru.mcsolutions.restaurants.shisha.tools.Internet;
import ru.mcsolutions.restaurants.shisha.tools.Utils;

public class CurrentOrderActivity extends AppCompatActivity {

    Context context = this;
    AppCompatTextView textViewCaption;
    AppCompatTextView textViewTotal;
    AppCompatTextView textViewPTotal;
    AppCompatButton buttonSend;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        textViewCaption = (AppCompatTextView) findViewById(R.id.textViewCaption);
        textViewTotal = (AppCompatTextView) findViewById(R.id.textViewTotal);
        textViewPTotal = (AppCompatTextView) findViewById(R.id.textViewPTotal);
        buttonSend = (AppCompatButton) findViewById(R.id.buttonSend);

        Global.currentOrder.removeEmptyDishes();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        OrderDishesRecyclerAdapter dishTypesRecyclerAdapter =
                new OrderDishesRecyclerAdapter(context, Global.currentOrder.orderDishes, Global.currentOrder.portions, textViewTotal, textViewPTotal);
        recyclerView.setAdapter(dishTypesRecyclerAdapter);


        textViewCaption.setText("Мой заказ");

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                if(Global.clientName.equals("")){
//посыл
                    final Internet internet = new Internet(context);

                    internet.addParamNameValue("name", " ");
                    internet.addParamNameValue("birthday", " ");
                    internet.startURL("clients.sendInfo", null);
                }
*/
                final Internet internet = new Internet(context);
                if(internet.isExists()){
                    if(Global.currentOrder.getIdOrder().equals("")){
                        Handler handler = new Handler(){
                            public void handleMessage(Message message) {
                                try {
                                    switch (message.what) {
                                        case Global.HTTP_PENDING:
                                            break;
                                        case Global.HTTP_FINISHED:
                                            String result = internet.result;
                                            if (result.startsWith("-1;")) {
                                                Toast.makeText(context, result.substring(3), Toast.LENGTH_LONG).show();
                                                finish();
                                            } else {
                                                if(result.startsWith("1;")){
                                                    Global.currentOrder.setIdOrder(result.substring(2));
                                                    Toast.makeText(context, "Заказ успешно послан", Toast.LENGTH_LONG).show();
                                                }else{
                                                    Toast.makeText(context, Global.NOT_RECOGNIZED, Toast.LENGTH_LONG).show();
                                                }
                                            }
                                            break;
                                    }
                                } catch (Exception e) {
                                    Utils.log(e.getMessage());
                                }
                            }
                        };
                        internet.addParamNameValue("idlocation", Global.location.getId());
                        internet.addParamNameValue("string", Global.currentOrder.getString());
                        internet.startURL("clients.sendOrder", handler);
                        Global.currentOrder.setPortionDateAmount();
                        Global.currentOrder.setNextPortion();
                    }else{
                        Handler handler = new Handler(){
                            public void handleMessage(Message message) {
                                try {
                                    switch (message.what) {
                                        case Global.HTTP_PENDING:
                                            break;
                                        case Global.HTTP_FINISHED:
                                            String result = internet.result;
                                            if (result.startsWith("-1;")) {
                                                Toast.makeText(context, result.substring(3), Toast.LENGTH_LONG).show();
                                                finish();
                                            } else {
                                                if(result.startsWith("1;")){
                                                    Toast.makeText(context, result.substring(2), Toast.LENGTH_LONG).show();
                                                    Global.currentOrder.setPortionDateAmount();
                                                    Global.currentOrder.setNextPortion();
                                                }else{
                                                    Toast.makeText(context, Global.NOT_RECOGNIZED, Toast.LENGTH_LONG).show();
                                                }
                                            }
                                            break;
                                    }
                                } catch (Exception e) {
                                    Utils.log(e.getMessage());
                                }
                            }
                        };
                        internet.addParamNameValue("idOrder", Global.currentOrder.getIdOrder());
                        internet.addParamNameValue("string", Global.currentOrder.getString());
                        internet.startURL("clients.addToOrder", handler);
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        textViewTotal.setText(Global.decimalFormat.format(Global.currentOrder.getTotal()));
        textViewPTotal.setText(Global.decimalFormat.format(Global.currentOrder.getPTotal()));
    }

}
