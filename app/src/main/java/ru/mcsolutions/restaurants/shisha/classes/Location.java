package ru.mcsolutions.restaurants.shisha.classes;

public class Location {
    String id;
    String name;
    String address;
    String msisdn;
    String idWorkDay;
    String isWorkHour;
    int distance;
    int beghour, endhour;
    int blbeghour, blendhour;
    Double latitude, longitude;


    public void setId(String id) {
        this.id = id;
    }
    public String getId() {return id;}

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setDistance(String distance) {this.distance = Integer.valueOf(distance); }
    public int getDistance() {
        return distance;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
    public String getMsisdn() {
        return msisdn;
    }

    public void setIdWorkDay(String idWorkDay) {
        this.idWorkDay = idWorkDay;
    }
    public String getIdWorkDay() {
        return idWorkDay;
    }

    public void setIsWorkHour(String isWorkHour) {
        this.isWorkHour = isWorkHour;
    }
    public String getIsWorkHour() {
        return isWorkHour;
    }

    public void setBeghour(String beghour) {
        if(beghour.equals("")){
            this.beghour = -1;
        }else{
            this.beghour = Integer.valueOf(beghour);
        }
    }
    public int getBeghour() {
        return beghour;
    }

    public void setEndhour(String endhour) {
        if(endhour.equals("")){
            this.endhour = -1;
        }else{
            this.endhour = Integer.valueOf(endhour);
        }
    }
    public int getEndhour() {
        return endhour;
    }

    public void setBlbeghour(String blbeghour) {
        if(blbeghour.equals("")){
            this.blbeghour = -1;
        }else{
            this.blbeghour = Integer.valueOf(blbeghour);
        }
    }
    public int getBlbeghour() {
        return blbeghour;
    }

    public void setBlendhour(String blendhour) {
        if(blendhour.equals("")){
            this.blendhour = -1;
        }else{
            this.blendhour = Integer.valueOf(blendhour);
        }
    }
    public int getBlendhour() {
        return blendhour;
    }

    public void setLatitude(String latitude) {
        this.latitude = Double.valueOf(latitude.replace(",", "."));
    }
    public Double getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = Double.valueOf(longitude.replace(",", "."));
    }
    public Double getLongitude() {
        return longitude;
    }

}
