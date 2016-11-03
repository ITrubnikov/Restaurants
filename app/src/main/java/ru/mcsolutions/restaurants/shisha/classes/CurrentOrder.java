package ru.mcsolutions.restaurants.shisha.classes;

import java.util.ArrayList;
import java.util.Date;

public class CurrentOrder {

    public ArrayList<Dish> dishes;
    public ArrayList<String> sections;
    public ArrayList<String> dishTypes;
    public ArrayList<OrderDish> orderDishes;
    public ArrayList<Location> locations;
    public ArrayList<TotalOrderDish> totalOrderDishes;
    public ArrayList<Order> orders;

    int currentPortion;
    String idOrder;

    public CurrentOrder() {
        dishes = new ArrayList<Dish>();
        sections = new ArrayList<String>();
        dishTypes = new ArrayList<String>();
        orderDishes = new ArrayList<OrderDish>();
        locations = new ArrayList<Location>();
        totalOrderDishes = new ArrayList<TotalOrderDish>();
        orders = new ArrayList<Order>();

        currentPortion = 1;

    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public int getCurrentPortion(){
        return currentPortion;
    }
    public void setNextPortion(){
        currentPortion = currentPortion + 1;
    }

    public void addDish(String idDish){
        int idOrderDish = 0;
        for(int i = 0;i<orderDishes.size();i++){
            OrderDish orderDish = orderDishes.get(i);
            if(orderDish.getIdDish().equals(idDish) && currentPortion == orderDish.getPortion()){
                idOrderDish = i;
                break;
            }
        }
        if(idOrderDish == 0){//не нашлось
            orderDishes.add(new OrderDish(idDish, 1, -1/*приоритет*/, currentPortion));
        }else{//нашлось
            orderDishes.get(idOrderDish).plusDish(idDish);
        }
    }

    public void delDish(int idDish){
        for(int i = 0;i<orderDishes.size();i++){
            OrderDish orderDish = orderDishes.get(i);
            if(orderDish.getIdDish().equals(idDish) && currentPortion == orderDish.getPortion()){
                orderDishes.remove(i);
                return;
            }
        }
    }

    public void setCurrentPortionDate(int portion){
        Date now = new Date();
        for(int i=0;i<orderDishes.size();i++){
            if(orderDishes.get(i).getPortion() == portion){
                orderDishes.get(i).setPortionDate(now);
            }
        }
    }

    public Double getTotal(){
        Double total = new Double(0);
        for(int i=0;i<orderDishes.size();i++){
            OrderDish orderDish = orderDishes.get(i);
            total = total+orderDish.getPriceFromDishes() * orderDish.getCount();
        }
        return total;
    }

    public String getString(int portion){
        String result = "";
        for(int i=0;i<orderDishes.size();i++){
            OrderDish orderDish = orderDishes.get(i);
            if(orderDish.getPortion() == portion){
                result = result + orderDish.getString() + ";";
            }
        }
        return result;
    }

}
