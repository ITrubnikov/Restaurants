package ru.mcsolutions.restaurants.shisha.classes;

import java.util.ArrayList;
import java.util.Date;

public class CurrentOrder {

    public ArrayList<Dish> dishes;
    public ArrayList<String> sections;
    public ArrayList<DishType> dishTypes;
    public ArrayList<OrderDish> orderDishes;
    public ArrayList<Location> locations;
    public ArrayList<TotalOrderDish> totalOrderDishes;
    public ArrayList<Order> orders;
    public ArrayList<Portion> portions;

    int currentPortion;
    String idOrder;

    public CurrentOrder() {
        dishes = new ArrayList<Dish>();
        sections = new ArrayList<String>();
        dishTypes = new ArrayList<DishType>();
        orderDishes = new ArrayList<OrderDish>();
        locations = new ArrayList<Location>();
        totalOrderDishes = new ArrayList<TotalOrderDish>();
        orders = new ArrayList<Order>();

        portions = new ArrayList<Portion>();
        portions.add(new Portion(1));
        currentPortion = 1;

    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }
    public String getIdOrder(){
        return idOrder;
    }

//    public void setCurrentPortion(int currentPortion){this.currentPortion = currentPortion; }
    public void setMaxPortion(){
        currentPortion = portions.size();
    }
    public int getCurrentPortion(){ return currentPortion; }
    public void setNextPortion(){
        portions.add(new Portion());
        currentPortion = currentPortion + 1;
    }

    public void setPAmount(Double amount){
        portions.get(portions.size()-1).setAmount(amount);
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

    public void deductDish(String idDish){
        for(int i = 0;i<orderDishes.size();i++){
            OrderDish orderDish = orderDishes.get(i);
            if(orderDish.getIdDish().equals(idDish) && currentPortion == orderDish.getPortion()){
                orderDish.minusDish(idDish);
                return;
            }
        }
    }
    public void setDishCount(String idDish, int count){
        for(int i = 0;i<orderDishes.size();i++){
            OrderDish orderDish = orderDishes.get(i);
            if(orderDish.getIdDish().equals(idDish) && currentPortion == orderDish.getPortion()){
                orderDish.setCount(count);
                return;
            }
        }
        orderDishes.add(new OrderDish(idDish, count, -1, currentPortion));
    }

    public void removeEmptyDishes(){
        for(int i = 0;i<orderDishes.size();i++){
            OrderDish orderDish = orderDishes.get(i);
            if(currentPortion == orderDish.getPortion() && orderDish.getCount() == 0){
                orderDishes.remove(i);
                return;
            }
        }
    };

    public void setPortionDateAmount(){
        Portion portion = portions.get(currentPortion - 1);
        portion.setPDate(new Date());
        portion.setAmount(getPTotal());
    }

    public Double getPTotal(){
        Double total = new Double(0);
        for(int i=0;i<orderDishes.size();i++){
            OrderDish orderDish = orderDishes.get(i);
            if(orderDish.getPortion() == currentPortion) {
                total = total + orderDish.getPriceFromDishes() * orderDish.getCount();
            }
        }
        return total;
    }

    public Double getTotal(){
        Double total = new Double(0);
        for(int i=0;i<orderDishes.size();i++){
            OrderDish orderDish = orderDishes.get(i);
            total = total+orderDish.getPriceFromDishes() * orderDish.getCount();
        }
        return total;
    }

    public String getString(){
        String result = "";
        for(int i=0;i<orderDishes.size();i++){
            OrderDish orderDish = orderDishes.get(i);
            if(orderDish.getPortion() == currentPortion && orderDish.getCount()>0){
                result = result + orderDish.getString() + ";";
            }
        }
        return result;
    }

    public Dish getDish(String idDish){
        Dish result = null;
        for(int i=0;i<dishes.size();i++){
            Dish dish = dishes.get(i);
            if(dish.getIdDish().equals(idDish)){
                return dish;
            }
        }
        return result;
    }

}
