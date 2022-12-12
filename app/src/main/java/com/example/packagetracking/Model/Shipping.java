package com.example.packagetracking.Model;

public class Shipping {
    String orderNumber,shipmentNumber,shipmentDate,shipmentTime,shipmentStatus,id;


    public Shipping(String orderNumber, String shipmentNumber, String shipmentDate, String shipmentTime, String shipmentStatus) {
        this.orderNumber = orderNumber;
        this.shipmentNumber = shipmentNumber;
        this.shipmentDate = shipmentDate;
        this.shipmentTime = shipmentTime;
        this.shipmentStatus = shipmentStatus;
    }

    public Shipping( String id ,String orderNumber, String shipmentNumber, String shipmentDate, String shipmentTime, String shipmentStatus) {
        this.orderNumber = orderNumber;
        this.shipmentNumber = shipmentNumber;
        this.shipmentDate = shipmentDate;
        this.shipmentTime = shipmentTime;
        this.shipmentStatus = shipmentStatus;
        this.id = id;
    }
    public Shipping() {

    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public String getShipmentTime() {
        return shipmentTime;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public String getId() {
        return id;
    }
}
