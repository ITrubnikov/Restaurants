package ru.mcsolutions.restaurants.shisha.classes;

public class MainMenu {

    String id;
    String name;
    String imageName;

    public MainMenu(){}

    public MainMenu(
            String id
            ,String name){
        this.id = id;
        this.name = name;
    }

    public void setId (String id) {this.id = id;}
    public String getId(){return this.id;}

    public void setName (String name) {this.name = name;}
    public String getName(){return this.name;}

    public void setImageName (String imageName) {this.imageName = imageName;}
    public String getImageName(){return this.imageName;}

}
