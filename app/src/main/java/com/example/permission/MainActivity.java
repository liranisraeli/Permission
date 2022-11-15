package com.example.permission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private MaterialButton main_BTN_login;
    private EditText main_EDT_password;
    //battery
    private static BatteryManager myBatteryManager;
    //wifi
    private ConnectivityManager connManager;


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
        connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);


    }


    private void initButton() {
        main_BTN_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String input = main_EDT_password.getText().toString();
                int password = Integer.parseInt(input);
                //battery equal to password
               if(getBatteryPercentage() == password){
                   Toast.makeText(MainActivity.this,"login successfully - the battery equal to password",Toast.LENGTH_SHORT).show();
               }
               //airplane mode is on
               else if(isAirplaneModeOn(MainActivity.this)){
                   Toast.makeText(MainActivity.this,"login successfully - airplane mode is open",Toast.LENGTH_SHORT).show();
               }
               //wifi is connect
               else if(isWifiConnect()){
                   Toast.makeText(MainActivity.this,"login successfully - wifi is connect",Toast.LENGTH_SHORT).show();
               }
               //silent mode on
               else if(isSilentModeOn()){
                   Toast.makeText(MainActivity.this,"login successfully - silent mode is open",Toast.LENGTH_SHORT).show();
               }
                //vibrate mode on
               else if(isVibrateOn()){
                   Toast.makeText(MainActivity.this,"login successfully - vibrate mode is open",Toast.LENGTH_SHORT).show();
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

    private static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }

    private boolean isSilentModeOn(){
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if(am.getRingerMode() == AudioManager.RINGER_MODE_SILENT){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isVibrateOn(){
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if(am.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isWifiConnect(){
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifi.isConnected();
    }



    private void findViews() {
        main_BTN_login = findViewById(R.id.main_BTN_login);
        main_EDT_password = findViewById(R.id.main_EDT_password);

    }


}