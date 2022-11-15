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
    private EditText main_EDT_password;
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
                String input = main_EDT_password.getText().toString();
                int password = Integer.parseInt(input);
               if(getBatteryPercentage() == password){
                   Toast.makeText(MainActivity.this,"login successfully - the battery equal to password",Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(MainActivity.this,"login failed",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    public static int getBatteryPercentage() {
         return myBatteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    }



    private void findViews() {
        main_BTN_login = findViewById(R.id.main_BTN_login);
        main_EDT_password = findViewById(R.id.main_EDT_password);

    }


}