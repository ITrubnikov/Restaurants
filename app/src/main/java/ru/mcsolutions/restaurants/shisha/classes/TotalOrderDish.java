package ru.mcsolutions.restaurants.shisha.classes;

public class TotalOrderDish {

    String idOrder, idDish, dish, count, rating, clientResponse;
    Double price;

    public TotalOrderDish() {
        this.idOrder = "";
        this.idDish = "";
        this.dish = "";
        this.count = "";
        this.rating = "";
        this.clientResponse = "";
        this.price = new Double(0);
    }

    public void setIdOrder(String idOrder) {this.idOrder = idOrder;}
    public String getIdOrder() {return idOrder;}

    public void setIdDish(String idDish) {this.idDish = idDish;}
    public String getIdDish() {return idDish;}

    public void setDish(String dish) {this.dish = dish;}
    public String getDish() {return dish;}

    public void setCount(String count) {this.count = count;}
    public String getCount() {return count;}

    public void setRating(String rating) {this.rating = rating;}
    public String getRating() {return rating;}

    public void setClientResponse(String clientResponse) {this.clientResponse = clientResponse;}
    public String getClientResponse() {return clientResponse;}

    public void setPrice(String price) {this.price = Double.valueOf(price);}
    public Double getPrice() {return price;}

}
