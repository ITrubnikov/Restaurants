package ru.mcsolutions.restaurants.shisha.classes;

import java.text.ParseException;
import java.util.Date;

import ru.mcsolutions.restaurants.shisha.tools.Global;

public class OrderDish {

    String idDish, dish, idStatus;
    int count, priority, portion;
    Double price;
    Date planDate, deliveryDate;

    public OrderDish(){
        idDish = "";
        dish = "";
        count = 0;
        priority = -1;
        portion = 1;
        price = null;
        idStatus = "1";
        planDate = null;
        deliveryDate = null;
    }

    public OrderDish(String idDish, int count, int priority, int portion) {
        this.idDish = idDish;
        this.dish = "";
        this.count = count;
        this.priority = priority;
        this.portion = portion;
        price = new Double(0);
        this.idStatus = "1";
        this.planDate = null;
        this.deliveryDate = null;
    }

    public void plusDish(String idDish){
        count = count + 1;
    }

    public void minusDish(String idDish){
        if(count > 0){
            count = count - 1;
        }
    }

    public void plusPriority(int idDish){
        priority = priority + 1;
    }

    public void minusPriority(int idDish){
        priority = priority - 1;
    }

    public void setIdDish(String idDish){this.idDish = idDish;}
    public String getIdDish(){
        return idDish;
    }

    public void setDish(String dish){this.dish = dish;}
    public String getDish(){
        return dish;
    }

    public void setCount(String count){this.count = Integer.valueOf(count);}
    public void setCount(int count){this.count = count;}
    public int getCount(){
        return count;
    }

    public void setPrice(String price){this.price = Double.valueOf(price);}
    public Double getPrice(){return price;}

    public void setPortion(String portion){this.portion = Integer.valueOf(portion);}
    public int getPortion(){
        return portion;
    }

    public void setPriority(String priority){this.priority = Integer.valueOf(priority);}
    public void setPriority(int priority){
        this.priority = priority;
    }
    public int getPriority(){
        return priority;
    }

    public void setIdStatus(String idStatus){this.idStatus = idStatus;}
    public String getIdStatus(){return idStatus;}

    public void setPlanDate(String planDate)throws ParseException {
        if(!planDate.equals("")){
            this.planDate = Global.simpleDateTimeFormat.parse(planDate);
        }
    }
    public Date getPlanDate(){return planDate;}


    public void setDeliveryDate(String deliveryDate) throws ParseException {
        if(!deliveryDate.equals("")) {
            this.deliveryDate = Global.simpleDateFormat.parse(deliveryDate);
        }
    }
    public Date getDeliveryDate(){
        return deliveryDate;
    }

    public Double getPriceFromDishes(){
        Double price = new Double(0);
        for(int i=0;i< Global.currentOrder.dishes.size();i++){
            Dish dish = Global.currentOrder.dishes.get(i);
            if(dish.getIdDish().equals(idDish)){
                price = dish.getPrice();
                break;
            }
        }
        return price;
    }

    public String getString(){
        return idDish + "," + count + "," + priority + "," + portion;
    }

}
