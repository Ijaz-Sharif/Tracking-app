package com.example.packagetracking.DatabaseService;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.packagetracking.Model.Order;
import com.example.packagetracking.Model.Shipping;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DBName = "ParcelTracking_Database";
    public static final String TName = "OrderTb";
    public static final String TName1 = "ShippingTb";

    Context context;
          // order colums
    public static final String Col1 = "orderNumber";
    public static final String Col2 = "OrderDate";
    public static final String Col3 = "itemNumber";
    public static final String Col4 = "itemDescription";
    public static final String Col5 = "countryOrigin";
    public static final String Col6 = "releaseDate";
    public static final String Col7 = "estimitedArrivalTime";
    public static final String Col8 = "DateOfShipment";
    public static final String Col9 = "orderStatus";
    public static final String Col10 = "deliveryNumber";
    public static final String Col11 = "Id";




        // shipping colums
    public static final String Col_1 = "orderNumber";
    public static final String Col_2 = "shipmentNumber";
    public static final String Col_3 = "shipmentDate";
    public static final String Col_4 = "shipmentTime";
    public static final String Col_5 = "shipmentStatus";
    public static final String Col_6 = "Id";
    public MyDatabase(Context context) {

        super(context, DBName, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" create table " + TName + "(" + Col1 + " TEXT," + Col2 + " TEXT," + Col3 + " TEXT,"
                + Col4 + " TEXT," + Col5 + " TEXT," + Col6 + " TEXT," + Col7 + " TEXT," + Col8 + " TEXT,"
                + Col9 + " TEXT," + Col10 + " TEXT,"
                + Col11 + " INTEGER PRIMARY KEY AUTOINCREMENT)");



        db.execSQL(" create table " + TName1 + "(" + Col_1 + " TEXT," + Col_2 + " TEXT," + Col_3 + " TEXT," + Col_4 + " TEXT," + Col_5 + " TEXT,"
                + Col_6 + " INTEGER PRIMARY KEY AUTOINCREMENT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TName);
        db.execSQL("DROP TABLE IF EXISTS " + TName1);
        onCreate(db);
    }



    public void addOrderData(Order order) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Col1,order.getOrderNumber());
        cv.put(Col2,order.getOrderDate());
        cv.put(Col3,order.getItemNumber());
        cv.put(Col4,order.getItemDescription());
        cv.put(Col5,order.getCountryOrigin());
        cv.put(Col6,order.getReleaseDate());
        cv.put(Col7,order.getEstimitedArrivalTime());
        cv.put(Col8,order.getDateOfShipment());
        cv.put(Col9,order.getOrderStatus());
        cv.put(Col10,order.getDeliveryNumber());
        long result=  db.insert(TName,null,cv);
//        if(result!=-1){
//            Toast.makeText(context, "record add successfully", Toast.LENGTH_LONG).show();
//        }
//        else {
//            Toast.makeText(context, "record not add successfully", Toast.LENGTH_LONG).show();
//        }
        db.close();
    }

    public void addShippingData(Shipping shipping) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Col_1,shipping.getOrderNumber());
        cv.put(Col_2,shipping.getShipmentNumber());
        cv.put(Col_3,shipping.getShipmentDate());
        cv.put(Col_4,shipping.getShipmentTime());
        cv.put(Col_5,shipping.getShipmentStatus());
        long result=  db.insert(TName1,null,cv);
        if(result!=-1){
            Toast.makeText(context, "shipment add successfully", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "shipment not add successfully", Toast.LENGTH_LONG).show();
        }
        db.close();
    }
    public Shipping getShippingData(String Id){
        Shipping shipping=null;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM ShippingTb WHERE orderNumber ='" + Id + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            shipping =new Shipping(String.valueOf(cursor.getInt(5)),cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(5));
            cursor.moveToNext();
        }
        return shipping;
    }
    public Order getOrderData(String Id){
        Order order=null;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM OrderTb WHERE orderNumber ='" + Id + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            order =new Order(String.valueOf(cursor.getInt(10)),cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)
            ,cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
            cursor.moveToNext();
        }
        return order;
    }



    public void updateOrderStatus(String orderNumber ,String status){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Col9,status);
        db.update(TName,cv,"Id=?",new String[]{orderNumber});
    }
    public void updateShippingStatus(String itemId){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Col_5,"Close");
        db.update(TName1,cv,"Id=?",new String[]{itemId});
    }
    public int DeleteOrder(String id){
        SQLiteDatabase db=this.getReadableDatabase();
        int b=  db.delete(TName,"Id=?",new String[]{id});
        return b;
    }

    public int DeleteShippingOrder(String id){
        SQLiteDatabase db=this.getReadableDatabase();
        int b=  db.delete(TName1,"orderNumber=?",new String[]{id});
        return b;
    }
}
