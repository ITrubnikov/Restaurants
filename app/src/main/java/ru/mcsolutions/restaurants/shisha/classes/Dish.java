package ru.mcsolutions.restaurants.shisha.classes;

import ru.mcsolutions.restaurants.shisha.tools.Global;

public class Dish {

    String
            idDish,
            idDishType,
            section,
            dishType,
            dishName,
            description,
            imagename,
            minutes;
    Double  price;
    int     weight;

    public Dish(){}

    public void setSection(String section){
        this.section = section;
    }
    public String getSection(){
        return this.section;
    }

    public void setDishType(String dishType){
        this.dishType = dishType;
    }
    public String getDishType(){
        return dishType;
    }

    public void setDishName(String dishName){
        this.dishName = dishName;
    }
    public String getDishName(){
        return dishName;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    public void setImagename(String imagename){
        this.imagename = imagename;
    }
    public String getImagename(){
        return imagename;
    }

    public void setIdDish(String idDish){this.idDish = idDish;}
    public String getIdDish(){
        return this.idDish;
    }

    public void setIdDishType(String idDishType){this.idDish = idDishType;}
    public String getIdDishType(){
        return idDishType;
    }

    public void setPrice(String price){
        this.price = Double.valueOf(price);
    }
    public Double getPrice(){
        return price;
    }
    public String getPriceString() {return Global.getDecimalString(price);}

    public void setWeight(String weight){
        this.weight = Integer.valueOf(weight);
    }
    public int getWeight(){return weight;}

    public void setMinutes(String minutes){
        this.minutes = minutes;
    }
    public String getMinutes(){return minutes;}
}
