package com.example.packagetracking.Screens;

import static com.example.packagetracking.MainActivity.order;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.packagetracking.DatabaseService.MyDatabase;
import com.example.packagetracking.MainActivity;
import com.example.packagetracking.Model.Shipping;
import com.example.packagetracking.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddShipmentActivity extends AppCompatActivity {
    EditText setDate,setTime;
    final Calendar myCalendar= Calendar.getInstance();
    DatePickerDialog datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shipment);
        setDate=findViewById(R.id.setDate);
        setTime=findViewById(R.id.setTime);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                String myFormat="MM/dd/yyyy";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                setDate.setText(dateFormat.format(myCalendar.getTime()));
            }
        };

        setDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View view) {
                datePicker =  new DatePickerDialog(AddShipmentActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
                datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });


        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddShipmentActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if(selectedMinute==0){
                            setTime.setText( selectedHour + ":" + "00");
                        }
                        else if(selectedMinute>=1 && selectedMinute<=9){
                            setTime.setText( selectedHour + ":0" + selectedMinute);
                        }
                        else {
                            setTime.setText( selectedHour + ":" + selectedMinute);
                        }

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }


        });
    }

    public void saveRecord(View view) {

         if(setDate.getText().toString().isEmpty()){
             setDate.setError("required");
         }
         else if(setTime.getText().toString().isEmpty()){
             setTime.setError("required");
         }
          else {
             Shipping shipping=new Shipping(order.getOrderNumber(),order.getItemNumber(),setDate.getText().toString(),setTime.getText().toString(),"open");
             MyDatabase myDatabase=new MyDatabase(this);
             myDatabase.addShippingData(shipping);
             myDatabase.updateOrderStatus(order.getOrderNumber());
             startActivity(new Intent(this, MainActivity.class)
                     .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
         }

    }
}