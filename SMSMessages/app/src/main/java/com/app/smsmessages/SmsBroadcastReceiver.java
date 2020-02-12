package com.app.smsmessages;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.Date;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onReceive(Context context, Intent intent) {
        Object[] sms;
        String format, smsBody, address;
        SmsMessage smsMessage;
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            format = intentExtras.getString("format");
            smsMessage = SmsMessage.createFromPdu((byte[]) sms[0], format);
            smsBody = smsMessage.getMessageBody().toString();
            address = smsMessage.getOriginatingAddress();
        }
        else {
            return;
        }
        if(InboxActivity.active) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            InboxActivity.instance().refreshInbox();
        }
        else {
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context, "notify_channel")
                            .setSmallIcon(R.mipmap.ic_launcher_round)
                            .setContentTitle("New Message from " + address)
                            .setContentText(smsBody)
                            .setAutoCancel(true)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            Intent notificationIntent = new Intent(context, InboxActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
            builder.setContentIntent(pendingIntent);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                String channelId = "notify_channel";
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "Notification channel",
                        NotificationManager.IMPORTANCE_HIGH);
                manager.createNotificationChannel(channel);
                builder.setChannelId(channelId);
            }
            manager.notify((int) ((new Date().getTime()/1000) % Integer.MAX_VALUE), builder.build());
        }
    }
}