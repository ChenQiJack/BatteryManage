package com.guang.batterymanage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.TextView;

public class BatteryInfoBroadcastReceiver extends BroadcastReceiver {// 广播接收器

    @Override
    public void onReceive(Context context, Intent intent) {// 接受广播
        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {// 判断Action
            int level = intent.getIntExtra("level", 0);// 取得电池剩余容量
            int scale = intent.getIntExtra("scale", 100);// 取得电池总理
            int status = intent.getIntExtra("status", 0);
            int health = intent.getIntExtra("health", 0);
            boolean present = intent.getBooleanExtra("present", false);
            int icon_small = intent.getIntExtra("icon-small", 0);
            int plugged = intent.getIntExtra("plugged", 0);
            int voltage = intent.getIntExtra("voltage", 0);
            int temperature = intent.getIntExtra("temperature", 0);
            String technology = intent.getStringExtra("technology");
            StringBuilder information = new StringBuilder();
            String statusString = "電池狀態：";
            switch (status) {
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    statusString += "未知";
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    statusString += "充電中";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    statusString += "放電中";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    statusString += "未充電中";
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    statusString += "電量全滿中";
                    break;
            }

            String healthString = "健康狀態：";
            switch (health) {
                case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                    healthString += "未知";
                    break;
                case BatteryManager.BATTERY_HEALTH_GOOD:
                    healthString += "健康";
                    break;
                case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                    healthString += "過熱";
                    break;
                case BatteryManager.BATTERY_HEALTH_DEAD:
                    healthString += "損壞";
                    break;
                case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                    healthString += "電壓過大";
                    break;
                case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                    healthString += "未明示故障";
                    break;
            }

            String acString = "充電方式：";
            switch (plugged) {
                case BatteryManager.BATTERY_PLUGGED_AC:
                    acString += "交流電電源";
                    break;
                case BatteryManager.BATTERY_PLUGGED_USB:
                    acString += "USB電源";
                    break;
            }

            information.append(statusString+"\n");
            information.append(healthString+"\n");
            information.append("目前剩餘電量："+level+"%\n");
            if(present==true)
                information.append("有無電池：有\n");
            else if(present==true)
                information.append("有無電池：無\n");
            information.append("電磁是否存在："+scale+"%\n");
            information.append(acString+"\n");
            information.append("目前電壓："+voltage+"V\n");
            information.append("目前溫度："+temperature+"℃\n");
            information.append("電池類型："+technology+"\n");

//            Log.v("status", statusString);
//            Log.v("health", healthString);
//            Log.v("present", String.valueOf(present));
//            Log.v("level", String.valueOf(level));
//            Log.v("scale", String.valueOf(scale));
//            Log.v("icon_small", String.valueOf(icon_small));
//            Log.v("plugged", acString);
//            Log.v("voltage", String.valueOf(voltage));
//            Log.v("temperature", String.valueOf(temperature));
//            Log.v("technology", technology);


            if (MainActivity.mThis != null) {
                ((TextView)MainActivity.mThis.findViewById(R.id.textInfo)).setText(information);
            }
        }
    }

}