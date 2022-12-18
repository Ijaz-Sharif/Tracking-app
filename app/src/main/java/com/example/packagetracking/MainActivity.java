package com.example.packagetracking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.packagetracking.DatabaseService.MyDatabase;
import com.example.packagetracking.Model.Order;

public class MainActivity extends AppCompatActivity {
          EditText et_search;
          MyDatabase myDatabase;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_search=findViewById(R.id.et_search);
        myDatabase=new MyDatabase(this);
        lottieAnimationView = findViewById(R.id.lottie);

    }

    public void search(View view) {
        if(et_search.getText().toString().isEmpty()){
            et_search.setError("required");
        }else {
            Order order= myDatabase.getOrderData(et_search.getText().toString());
            if(order==null){
                Toast.makeText(MainActivity.this,"no record",Toast.LENGTH_LONG).show();
            }
            else {
                lottieAnimationView.setVisibility(View.GONE);
            }
        }
    }
}