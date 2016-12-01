package com.guang.batterymanage;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends Activity {
    TextView textInfo;
    public static MainActivity mThis = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BatteryInfoBroadcastReceiver receiver = new BatteryInfoBroadcastReceiver();
        IntentFilter filter = new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, filter);
        textInfo = (TextView)findViewById(R.id.textInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mThis = this;
    }
    @Override
    protected void onPause() {
        super.onPause();
        mThis = null;
    }
}
