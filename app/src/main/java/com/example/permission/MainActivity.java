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
    private  BatteryManager myBatteryManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        getSystemService();
        initButton();
    }

    private void initButton() {
        main_BTN_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               if(isUSBCharging()){
                   Toast.makeText(MainActivity.this,"phone is charging",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    private void getSystemService() {
         myBatteryManager = (BatteryManager) this.getSystemService(Context.BATTERY_SERVICE);
    }


    public boolean isUSBCharging(){
            return myBatteryManager.isCharging();
        }


    private void findViews() {
        main_BTN_login = findViewById(R.id.main_BTN_login);
        main_EDT_name = findViewById(R.id.main_EDT_name);

    }


}