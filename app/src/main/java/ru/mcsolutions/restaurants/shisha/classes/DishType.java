package ru.mcsolutions.restaurants.shisha.classes;

public class DishType {

    String id;
    String name;
    String imageName;

    public DishType(){}
    public DishType(String id, String name, String imageName){
        this.id = id;
        this.name = name;
        this.imageName = imageName;
    }

    public void setId(String id) {this.id = id;}
    public String getId() {return id;}

    public void setName(String name) {this.name = name;}
    public String getName() {return name;}

    public void setImageName(String imageName) {this.imageName = imageName;}
    public String getImageName() {return imageName;}

}
