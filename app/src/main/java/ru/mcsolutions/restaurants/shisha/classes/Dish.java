package ru.mcsolutions.restaurants.shisha.classes;

import ru.mcsolutions.restaurants.shisha.tools.Utils;

public class Dish {

    String
            section,
            idDish,
            idDishType,
            name,
            description,
            imageName,
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

    public void setIdDishType(String idDishType){this.idDishType = idDishType;}
    public String getIdDishType(){
        return idDishType;
    }

    public void setIdDish(String idDish){this.idDish = idDish;}
    public String getIdDish(){
        return this.idDish;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    public void setImageName(String imageName){
        this.imageName = imageName;
    }
    public String getImageName(){
        return imageName;
    }

    public void setPrice(String price){
        this.price = Double.valueOf(price);
    }
    public Double getPrice(){
        return price;
    }
    public String getPriceString() {return Utils.getDecimalString(price);}

    public void setWeight(String weight){
        this.weight = Integer.valueOf(weight);
    }
    public int getWeight(){return weight;}

    public void setMinutes(String minutes){
        this.minutes = minutes;
    }
    public String getMinutes(){return minutes;}
}
