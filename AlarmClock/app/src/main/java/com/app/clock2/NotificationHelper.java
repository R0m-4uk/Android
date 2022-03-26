package com.app.clock2;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    private final MediaPlayer sound = MediaPlayer.create(this, R.raw.auf);


    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private NotificationManager mManager;

// Помощник по уведомлениям
    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }
// Создание канала уведомление
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }

// Менеджер уведомлений
    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }


// Непосредственно само уведомление
    public NotificationCompat.Builder getChannelNotification() {

        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction("RESET");
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);


        soundPlay();
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Подъём!")
                .setContentText("Время вставать с кроватки.")
                .setSmallIcon(android.R.drawable.ic_menu_recent_history)
                .setContentIntent(contentIntent)
                .setAutoCancel(true);
    }

// Воспроизведение музыки
    private void soundPlay() {
        sound.start();
    }

}

