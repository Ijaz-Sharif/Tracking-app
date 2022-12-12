package com.example.packagetracking.DatabaseService;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        db.execSQL(" create table " + TName + "(" + Col_1 + " TEXT," + Col_2 + " TEXT," + Col_3 + " TEXT,"
                + Col_4 + " INTEGER PRIMARY KEY AUTOINCREMENT)");



        db.execSQL(" create table " + TName1 + "(" + Col_1 + " TEXT," + Col_2 + " TEXT," + Col_3 + " TEXT," + Col_4 + " TEXT," + Col_5 + " TEXT,"
                + Col_6 + " INTEGER PRIMARY KEY AUTOINCREMENT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TName);
        db.execSQL("DROP TABLE IF EXISTS " + TName1);
        onCreate(db);
    }





    public void updateOrderStatus(String itemId){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Col9,"close");
        db.update(TName,cv,"Id=?",new String[]{itemId});
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
    public int DeleteShipping(String id){
        SQLiteDatabase db=this.getReadableDatabase();
        int b=  db.delete(TName1,"Id=?",new String[]{id});
        return b;
    }
}