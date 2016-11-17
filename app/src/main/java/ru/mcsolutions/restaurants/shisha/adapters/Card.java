package ru.mcsolutions.restaurants.shisha.adapters;

import ru.mcsolutions.restaurants.shisha.classes.OrderDish;
import ru.mcsolutions.restaurants.shisha.classes.Portion;

class Card{

    Portion portion;
    OrderDish orderDish;

    public Card(Portion portion){
        this.portion = portion;
        this.orderDish = null;
    }
    public Card(Portion portion, OrderDish orderDish){
        this.portion = portion;
        this.orderDish = orderDish;
    }
    public Portion getPortion(){return portion;}
    public OrderDish getOrderDish(){return orderDish;}

    public String toString(){
        String result = "";
        if(orderDish == null){
            result = "portion = " + portion.toString();
        }else{
            result = "portion = " + portion.toString() + ", idDish = " + orderDish.getIdDish();
        }
        return result;
    }
}

