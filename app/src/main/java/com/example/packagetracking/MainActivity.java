package com.example.packagetracking;

import static com.example.packagetracking.Utils.Constant.setUserLoginStatus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.packagetracking.DatabaseService.MyDatabase;
import com.example.packagetracking.Model.Order;
import com.example.packagetracking.Screens.AccountActivity;
import com.example.packagetracking.Screens.DetailActivity;

public class MainActivity extends AppCompatActivity {
    EditText et_search;
    MyDatabase myDatabase;


    LottieAnimationView lottieAnimationView;
    ImageView search_img,cancel_img;
    Button btn_orderDate,btn_item_number,btn_description,btn_country_origin,btn_status;
    TextView orderDate,itemNumber,description,countryOrigin,ordeerStatus;
    public static Order order;
    boolean isOrderDate=false,isItem=false,isDescription=false,isCountry=false,isStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_search=findViewById(R.id.et_search);
        myDatabase=new MyDatabase(this);
        lottieAnimationView = findViewById(R.id.lottie);
        search_img=findViewById(R.id.search_img);
        cancel_img=findViewById(R.id.cancel_img);
        btn_country_origin=findViewById(R.id.btn_country_origin);
        btn_description=findViewById(R.id.btn_description);
        btn_item_number=findViewById(R.id.btn_item_number);
        btn_orderDate=findViewById(R.id.btn_orderDate);
        orderDate=findViewById(R.id.orderDate);
        itemNumber=findViewById(R.id.itemNumber);
        description=findViewById(R.id.description);
        countryOrigin=findViewById(R.id.countryOrigin);
        ordeerStatus=findViewById(R.id.ordeerStatus);
        btn_status=findViewById(R.id.btn_status);

        btn_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOrderDate){
                    isStatus=false;
                    btn_status.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(MainActivity.this,R.drawable.arrow_down), null);
                    ordeerStatus.setVisibility(View.GONE);
                }
                else {
                    isStatus=true;
                    btn_status.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(MainActivity.this,R.drawable.arrow_up), null);
                    ordeerStatus.setVisibility(View.VISIBLE);
                }


            }
        });
        btn_orderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOrderDate){
                    isOrderDate=false;
                    btn_orderDate.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(MainActivity.this,R.drawable.arrow_down), null);
                    orderDate.setVisibility(View.GONE);
                }
                else {
                    isOrderDate=true;
                    btn_orderDate.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(MainActivity.this,R.drawable.arrow_up), null);

                    orderDate.setVisibility(View.VISIBLE);
                }


            }
        });
        btn_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isDescription){
                    isDescription=false;
                    description.setVisibility(View.GONE);
                    btn_description.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(MainActivity.this,R.drawable.arrow_down), null);

                }
                else {
                    isDescription=true;
                    description.setVisibility(View.VISIBLE);
                    btn_description.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(MainActivity.this,R.drawable.arrow_up), null);

                }


            }
        });
        btn_item_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isItem){
                    isItem=false;
                    btn_item_number.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(MainActivity.this,R.drawable.arrow_down), null);
                    itemNumber.setVisibility(View.GONE);
                }
                else {
                    isItem=true;
                    btn_item_number.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(MainActivity.this,R.drawable.arrow_up), null);
                    itemNumber.setVisibility(View.VISIBLE);
                }

            }
        });
        btn_country_origin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCountry){
                    isCountry=false;
                    btn_country_origin.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(MainActivity.this,R.drawable.arrow_down), null);

                    countryOrigin.setVisibility(View.GONE);
                }
                else {
                    isCountry=true;
                    btn_country_origin.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(MainActivity.this,R.drawable.arrow_up), null);
                    countryOrigin.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    public void search(View view) {
        if(et_search.getText().toString().isEmpty()){
            et_search.setError("required");
        }else {
             order= myDatabase.getOrderData(et_search.getText().toString());
            if(order==null){
                cancel_img.setVisibility(View.VISIBLE);
                search_img.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,"no record",Toast.LENGTH_LONG).show();
            }
            else {
              showRecord();
            }
        }
    }

    @Override
    protected void onStart() {
        hideRecord();
        super.onStart();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logoutUser:
                setUserLoginStatus(MainActivity.this,false);
                startActivity(new Intent(MainActivity.this, AccountActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void cancel(View view) {
                 hideRecord();
    }
    public void showRecord(){
        isOrderDate=false;
        isItem=false;
        isDescription=false;
        isCountry=false;
        isStatus=false;
        btn_status.setVisibility(View.VISIBLE);
        ordeerStatus.setText(order.getOrderStatus());
        cancel_img.setVisibility(View.VISIBLE);
        search_img.setVisibility(View.GONE);
        lottieAnimationView.setVisibility(View.GONE);
        btn_country_origin.setVisibility(View.VISIBLE);
        btn_description.setVisibility(View.VISIBLE);
        btn_item_number.setVisibility(View.VISIBLE);
        btn_orderDate.setVisibility(View.VISIBLE);
        itemNumber.setText(order.getItemNumber());
        description.setText(order.getItemDescription());
        countryOrigin.setText(order.getCountryOrigin());
        orderDate.setText(order.getOrderDate());
    }
    public void hideRecord(){
        isOrderDate=false;
        isItem=false;
        isDescription=false;
        isCountry=false;
        ordeerStatus.setVisibility(View.GONE);
        isStatus=false;
        btn_status.setVisibility(View.GONE);
        cancel_img.setVisibility(View.GONE);
        et_search.setText("");
        search_img.setVisibility(View.VISIBLE);
        lottieAnimationView.setVisibility(View.VISIBLE);
         btn_country_origin.setVisibility(View.GONE);
         btn_description.setVisibility(View.GONE);
         btn_item_number.setVisibility(View.GONE);
         btn_orderDate.setVisibility(View.GONE);
         description.setVisibility(View.GONE);
         countryOrigin.setVisibility(View.GONE);
         itemNumber.setVisibility(View.GONE);
         orderDate.setVisibility(View.GONE);
    }

//    public void detail(View view) {
//        startActivity(new Intent(this, DetailActivity.class));
//    }
}