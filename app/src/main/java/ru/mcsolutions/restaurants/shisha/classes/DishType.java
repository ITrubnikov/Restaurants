package ru.mcsolutions.restaurants.shisha.classes;

public class DishType {

    String id;
    String dishType;
    String imageName;

    public DishType(){}
    public DishType(String id, String dishType, String imageName){
        this.id = id;
        this.dishType = dishType;
        this.imageName = imageName;
    }

    public void setId(String id) {this.id = id;}
    public String getId() {return id;}

    public void setDishType(String dishType) {this.dishType = dishType;}
    public String getDishType() {return dishType;}

    public void setImageName(String imageName) {this.imageName = imageName;}
    public String getImageName() {return imageName;}

}
