package com.example.packagetracking.Screens;

import static com.example.packagetracking.MainActivity.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.packagetracking.DatabaseService.MyDatabase;
import com.example.packagetracking.R;

public class DetailActivity extends AppCompatActivity {
         EditText releaseDate,estimitedArrivalTime,DateOfShipment,deliveryNumber;
         Button btnShipment,btnCompleteOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        releaseDate=findViewById(R.id.et_releaseDate);
        estimitedArrivalTime=findViewById(R.id.et_estimitedArrivalTime);
        DateOfShipment=findViewById(R.id.et_DateOfShipment);
        deliveryNumber=findViewById(R.id.et_deliveryNumber);
        btnShipment=findViewById(R.id.btnShipment);
        btnCompleteOrder=findViewById(R.id.btnCompleteOrder);

        releaseDate.setText(order.getReleaseDate());
        estimitedArrivalTime.setText(order.getEstimitedArrivalTime());
        DateOfShipment.setText(order.getDateOfShipment());
        deliveryNumber.setText(order.getDeliveryNumber());
        if(order.getOrderStatus().equals("open")){
            btnShipment.setVisibility(View.VISIBLE);
            btnCompleteOrder.setVisibility(View.GONE);
        }
        else {
            btnShipment.setVisibility(View.GONE);
            btnCompleteOrder.setVisibility(View.VISIBLE);
        }
    }

    public void addShipment(View view) {
        startActivity(new Intent(this,AddShipmentActivity.class));
    }

    public void completeOrder(View view) {
        MyDatabase myDatabase=new MyDatabase(this);
        myDatabase.DeleteShippingOrder(order.getOrderNumber());
        myDatabase.DeleteOrder(order.getId());
        Toast.makeText(this,"order complete",Toast.LENGTH_LONG).show();
        finish();



    }
}