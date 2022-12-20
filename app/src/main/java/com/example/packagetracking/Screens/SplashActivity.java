package com.example.packagetracking.Screens;

import static com.example.packagetracking.Utils.Constant.getAppStatus;
import static com.example.packagetracking.Utils.Constant.getUserLoginStatus;
import static com.example.packagetracking.Utils.Constant.setAppStatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.packagetracking.DatabaseService.MyDatabase;
import com.example.packagetracking.MainActivity;
import com.example.packagetracking.Model.Order;
import com.example.packagetracking.R;
import com.example.packagetracking.Utils.Constant;
import com.google.firebase.database.DatabaseReference;

public class SplashActivity extends AppCompatActivity {
      MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        myDatabase=new MyDatabase(this);
          if(getAppStatus(this)){
              start();
          }
          else {
              addRecord();
          }
    }
    public void start(){
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    if(getUserLoginStatus(SplashActivity.this)){
                        // call the function for update status
                        updateOrderStatus();
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                    // if no one login move to login screen
                    else {
                        startActivity(new Intent(SplashActivity.this, AccountActivity.class));
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
    // add record in order table database
    public void addRecord() {
        myDatabase.addOrderData(new Order("001", "10/10/2222", "4", "testing", "India", "15/10/2222", "11:00", "11/10/2222", "open", "00961234567"));
        myDatabase.addOrderData(new Order("002", "10/11/2222", "6", "testing", "USA", "12/11/2222", "15:00", "11/10/2222", "open", "00967654321"));
        myDatabase.addOrderData(new Order("003", "10/12/2222", "10", "testing", "Canada", "12/12/2222", "18:00", "11/12/2222", "open", "00961134567"));
        myDatabase.addOrderData(new Order("004", "8/8/2222", "14", "testing", "Russia", "10/8/2222", "1:00", "11/8/2222", "open", "00961234997"));
        myDatabase.addOrderData(new Order("005", "7/7/2222", "2", "testing", "France", "10/7/2222", "7:00", "14/7/2222", "open", "00961236666"));
        setAppStatus(this, true);
        start();

    }

    // update order record status
    public void updateOrderStatus(){
            String orderId="001";
            String status="close";
        myDatabase.updateOrderStatus(orderId,status);





    }


}