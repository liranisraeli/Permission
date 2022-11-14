package com.example.permission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private MaterialButton main_BTN_login;
    private EditText main_EDT_name;
    private static BatteryManager myBatteryManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initSystemService(MainActivity.this);
        initButton();
    }

    private void initSystemService(Context context) {
        myBatteryManager = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
    }

    private void initButton() {
        main_BTN_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               if(getBatteryPercentage()>90){
                   Toast.makeText(MainActivity.this,"login successfully - the battery is over 90",Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(MainActivity.this,"login failed - the battery must be over 90",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    public static int getBatteryPercentage() {
         return myBatteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    }


    private void findViews() {
        main_BTN_login = findViewById(R.id.main_BTN_login);
        main_EDT_name = findViewById(R.id.main_EDT_name);

    }


}