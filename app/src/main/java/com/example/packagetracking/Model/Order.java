package com.example.packagetracking.Model;

public class Order {


    String  orderNumber,OrderDate,itemNumber,itemDescription,countryOrigin,releaseDate,estimitedArrivalTime,DateOfShipment,orderStatus,deliveryNumber,id;


    public Order(String orderNumber, String orderDate, String itemNumber, String itemDescription, String countryOrigin, String releaseDate, String estimitedArrivalTime, String dateOfShipment, String orderStatus, String deliveryNUmber) {
        this.orderNumber = orderNumber;
        OrderDate = orderDate;
        this.itemNumber = itemNumber;
        this.itemDescription = itemDescription;
        this.countryOrigin = countryOrigin;
        this.releaseDate = releaseDate;
        this.estimitedArrivalTime = estimitedArrivalTime;
        DateOfShipment = dateOfShipment;
        this.orderStatus = orderStatus;
        this.deliveryNumber = deliveryNUmber;
    }

    public Order( String id,String orderNumber, String orderDate, String itemNumber, String itemDescription, String countryOrigin, String releaseDate, String estimitedArrivalTime, String dateOfShipment, String orderStatus, String deliveryNUmber) {
        this.orderNumber = orderNumber;
        OrderDate = orderDate;
        this.itemNumber = itemNumber;
        this.itemDescription = itemDescription;
        this.countryOrigin = countryOrigin;
        this.releaseDate = releaseDate;
        this.estimitedArrivalTime = estimitedArrivalTime;
        DateOfShipment = dateOfShipment;
        this.orderStatus = orderStatus;
        this.deliveryNumber = deliveryNUmber;
        this.id = id;
    }

    public Order(){

    }
    public String getOrderNumber() {
        return orderNumber;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getEstimitedArrivalTime() {
        return estimitedArrivalTime;
    }

    public String getDateOfShipment() {
        return DateOfShipment;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public String getId() {
        return id;
    }
}
