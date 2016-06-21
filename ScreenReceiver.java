package com.example.heeill.termproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by User on 2016-06-21.
 */
public class ScreenReceiver extends BroadcastReceiver {

    // 화면이 꺼진 것을 인지한 후 LockScreenActivity를 띄워줌
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Intent i = new Intent(context, LockScreenActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
