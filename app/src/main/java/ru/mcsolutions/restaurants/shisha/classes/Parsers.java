package ru.mcsolutions.restaurants.shisha.classes;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import ru.mcsolutions.restaurants.shisha.tools.Global;
import ru.mcsolutions.restaurants.shisha.tools.Utils;

public class Parsers {

    XmlPullParserFactory factory;
    XmlPullParser xpp;

    public Parsers() {
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            xpp = factory.newPullParser();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Location> getLocations(String xml) throws XmlPullParserException, IOException {
        ArrayList<Location> locations = new ArrayList<Location>();
        xpp.setInput(new StringReader(xml));
        Location location = null;
        while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
            switch (xpp.getEventType()) {
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equals("row") && xpp.getDepth()==2) {
                        location = new Location();
                    }else{
                        if (xpp.getDepth() == 3) {
                            String startTag = xpp.getName();
                            xpp.next();
                            String value = xpp.getText();
                            if(value==null){
                                value = "";
                            }
                            switch(startTag){
                                case "idlocation":      location.setId(value);          break;
                                case "name":            location.setName(value);        break;
                                case "address":         location.setAddress(value);     break;
                                case "latitude":        location.setLatitude(value);    break;
                                case "longitude":       location.setLongitude(value);   break;
                                case "distance":        location.setDistance(value);    break;
                                case "msisdn":          location.setMsisdn(value);      break;
                                case "idworkday":       location.setIdWorkDay(value);   break;
                                case "isworkhour":      location.setIsWorkHour(value);  break;
                                case "beghour":         location.setBeghour(value);     break;
                                case "endhour":         location.setEndhour(value);     break;
                                case "blbeghour":       location.setBlbeghour(value);   break;
                                case "blendhour":       location.setBlendhour(value);   break;
                            }
                        }
                    };
                    break;
                case XmlPullParser.END_TAG:
                    if(xpp.getName().equals("row")){
                        locations.add(location);
                    }
                    break;
                default:
                    break;
            }
            xpp.next();
        }
        Utils.log("locations.size() = " + locations.size());
        return locations;
    }

    public ArrayList<MainMenu> getMainMenus(String xml) throws XmlPullParserException, IOException {
        ArrayList<MainMenu> mainMenus = new ArrayList<MainMenu>();
        xpp.setInput(new StringReader(xml));
        MainMenu mainMenu = null;
        while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
            switch (xpp.getEventType()) {
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equals("row") && xpp.getDepth()==2) {
                        mainMenu = new MainMenu();
                    }else{
                        if (xpp.getDepth() == 3) {
                            String startTag = xpp.getName();
                            xpp.next();
                            String value = xpp.getText();
                            if(value==null){
                                value = "";
                            }
                            switch(startTag){
                                case "id": mainMenu.setId(value);break;
                                case "name": mainMenu.setName(value);break;
                                case "imagename": mainMenu.setImageName(value);break;
                            }
                        }
                    };
                    break;
                case XmlPullParser.END_TAG:
                    if(xpp.getName().equals("row")){
                        mainMenus.add(mainMenu);
                    }
                    break;
                default:
                    break;
            }
            xpp.next();
        }
        Utils.log("mainMenus.size() = " + mainMenus.size());
        return mainMenus;
    }

    public ArrayList<Order> getOrders(String xml) throws XmlPullParserException, IOException, ParseException {
        ArrayList<Order> orders = new ArrayList<Order>();
        xpp.setInput(new StringReader(xml));
        Order order = null;
        while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
            switch (xpp.getEventType()) {
                case XmlPullParser.START_TAG:
                    if (xpp.getName().equals("row") && xpp.getDepth() == 2) {
                        order = new Order();
                    } else {
                        if (xpp.getDepth() == 3) {
                            String startTag = xpp.getName();
                            xpp.next();
                            String value = xpp.getText();
                            if (value == null) {
                                value = "";
                            }
                            switch (startTag) {
                                case "idorder":
                                    order.setId(value);
                                    break;
                                case "idorderstatus":
                                    order.setIdOrderStatus(value);
                                    break;
                                case "name":
                                    order.setName(value);
                                    break;
                                case "idlocation":
                                    order.setIdLocation(value);
                                    break;
                                case "idworkday":
                                    order.setIdWorkDay(value);
                                    break;
                                case "odate":
                                    order.setODate(value);
                                    break;
                                case "pdate":
                                    order.setPDate(value);
                                    break;
                                default: break;
                            }
                        }
                    }
                    ;
                    break;
                case XmlPullParser.END_TAG:
                    if (xpp.getName().equals("row")) {
                        orders.add(order);
                    }
                    break;
                default:
                    break;
            }
            xpp.next();
        }
        Utils.log("orders.size() = " + orders.size());
        return orders;
    }

    public ArrayList<DishType> getDishTypes(String xml) throws XmlPullParserException, IOException {
        ArrayList<DishType> dishTypes = new ArrayList<DishType>();
        xpp.setInput(new StringReader(xml));
        DishType dishType = null;
        while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
            switch (xpp.getEventType()) {
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equals("row") && xpp.getDepth()==2) {
                        dishType = new DishType();
                    }else{
                        if (xpp.getDepth() == 3) {
                            String startTag = xpp.getName();
                            xpp.next();
                            String value = xpp.getText();
                            if(value==null){
                                value = "";
                            }
                            switch(startTag){
                                case "iddishtype":  dishType.setId(value);  break;
                                case "name":        dishType.setName(value);    break;
                                case "imagename":   dishType.setImageName(value);   break;
                            }
                        }
                    };
                    break;
                case XmlPullParser.END_TAG:
                    if(xpp.getName().equals("row")){
                        dishTypes.add(dishType);
                    }
                    break;
                default:
                    break;
            }
            xpp.next();
        }
        Utils.log("dishTypes.size() = " + dishTypes.size());
        return dishTypes;
    }

    public ArrayList<Dish> getDishes(String xml) throws XmlPullParserException, IOException {
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        xpp.setInput(new StringReader(xml));
        Dish dish = null;
        String section = "";
        while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
            switch (xpp.getEventType()) {
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equals("row") && xpp.getDepth()==2) {
                        dish = new Dish();
                    }else{
                        if (xpp.getDepth() == 3) {
                            String startTag = xpp.getName();
                            xpp.next();
                            String value = xpp.getText();
                            if(value==null){
                                value = "";
                            }
                            switch(startTag){
                                case "section":     dish.setSection(value);     section = value;break;
                                case "iddishtype":  dish.setIdDishType(value);  break;
                                case "iddish":      dish.setIdDish(value);      break;
                                case "name":        dish.setName(value);    break;
                                case "price":       dish.setPrice(value);       break;
                                case "weight":      dish.setWeight(value);      break;
                                case "minutes":     dish.setMinutes(value);     break;
                                case "description": dish.setDescription(value); break;
                                case "imagename":   dish.setImageName(value);   break;
                            }
                        }
                    };
                    break;
                case XmlPullParser.END_TAG:
                    if(xpp.getName().equals("row")){
                        dishes.add(dish);
                        if(Global.currentOrder.sections.indexOf(section)==0){
                            Global.currentOrder.sections.add(section);
                        }
                    }
                    break;
                default:
                    break;
            }
            xpp.next();
        }
        Utils.log("dishes.size() = " + dishes.size());
        return dishes;
    }

    public ArrayList<OrderDish> getOrderDishes(String xml) throws XmlPullParserException, IOException, ParseException {
        ArrayList<OrderDish> orderDishes = new ArrayList<OrderDish>();
        xpp.setInput(new StringReader(xml));
        OrderDish orderDish = null;
        while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
            switch (xpp.getEventType()) {
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equals("row") && xpp.getDepth()==2) {
                        orderDish = new OrderDish();
                    }else{
                        if (xpp.getDepth() == 3) {
                            String startTag = xpp.getName();
                            xpp.next();
                            String value = xpp.getText();
                            if(value==null){
                                value = "";
                            }
                            switch(startTag){
                                case "iddish":              orderDish.setIdDish(value);          break;
                                case "dish":                orderDish.setDish(value);        break;
                                case "portion":             orderDish.setPortion(value);    break;
                                case "portiondate":         orderDish.setPortionDate(value);     break;
                                case "count":               orderDish.setCount(value);      break;
                                case "price":               orderDish.setPrice(value);   break;
                                case "priority":            orderDish.setPriority(value);   break;
                                case "plandate":            orderDish.setPlanDate(value);   break;
                                case "deliverydate":        orderDish.setDeliveryDate(value);   break;
                                case "idorderdishstatus":   orderDish.setIdStatus(value);   break;
                                default: break;
                            }
                        }
                    };
                    break;
                case XmlPullParser.END_TAG:
                    if(xpp.getName().equals("row")){
                        orderDishes.add(orderDish);
                    }
                    break;
                default:
                    break;
            }
            xpp.next();
        }
        Utils.log("orderDishes.size() = " + orderDishes.size());
        return orderDishes;
    }

    public ArrayList<TotalOrderDish> getTotalOrderDishes(String xml) throws XmlPullParserException, IOException {
        ArrayList<TotalOrderDish> totalOrderDishes = new ArrayList<TotalOrderDish>();
        xpp.setInput(new StringReader(xml));
        TotalOrderDish totalOrderDish = null;
        while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
            switch (xpp.getEventType()) {
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equals("row") && xpp.getDepth()==2) {
                        totalOrderDish = new TotalOrderDish();
                    }else{
                        if (xpp.getDepth() == 3) {
                            String startTag = xpp.getName();
                            xpp.next();
                            String value = xpp.getText();
                            if(value==null){
                                value = "";
                            }
                            switch(startTag){
                                case "idorder":         totalOrderDish.setIdOrder(value);     break;
                                case "iddish":          totalOrderDish.setIdDish(value);      break;
                                case "dish":            totalOrderDish.setDish(value);    break;
                                case "count":           totalOrderDish.setCount(value);      break;
                                case "price":           totalOrderDish.setPrice(value);       break;
                                case "rating":          totalOrderDish.setRating(value);      break;
                                case "clientResponse":  totalOrderDish.setClientResponse(value);     break;
                            }
                        }
                    };
                    break;
                case XmlPullParser.END_TAG:
                    if(xpp.getName().equals("row")){
                        totalOrderDishes.add(totalOrderDish);
                    }
                    break;
                default:
                    break;
            }
            xpp.next();
        }
        Utils.log("totalOrderDishes.size() = " + totalOrderDishes.size());
        return totalOrderDishes;
    }

    public ArrayList<HashMap<String, String>> getArrayListHashMap(String xml) throws XmlPullParserException, IOException {
        ArrayList<HashMap<String, String>> arrayListHashMap = new ArrayList<HashMap<String, String>>();
        xpp.setInput(new StringReader(xml));
        HashMap<String, String> hashMap = null;
        while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
            switch (xpp.getEventType()) {
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equals("row") && xpp.getDepth()==2) {
                        hashMap = new HashMap<String, String>();
                    }else{
                        if (xpp.getDepth() == 3) {
                            String startTag = xpp.getName();
                            xpp.next();
                            String value = xpp.getText();
                            if(value==null){
                                value = "";
                            }
                            hashMap.put(startTag, value);
                        }
                    };
                    break;
                case XmlPullParser.END_TAG:
                    if(xpp.getName().equals("row")){
                        arrayListHashMap.add(hashMap);
                    }
                    break;
                default:
                    break;
            }
            xpp.next();
        }
        Utils.log("arrayListHashMap.size() = " + arrayListHashMap.size());
        return arrayListHashMap;
    }

}
