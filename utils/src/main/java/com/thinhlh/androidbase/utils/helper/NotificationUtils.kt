package com.thinhlh.androidbase.utils.helper

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import java.util.*

/**
 * Created by thinhlh on 08/02/2022.
 * Copyright (c). All rights reserved
 */
object NotificationUtils {
    private var soundUri: Uri? = null

    fun initSound(soundUri: Uri?) {
        this.soundUri = soundUri
    }

    /**
     * Create a notification channel for Android O and later
     *
     * @param context     - Application context
     * @param channelId   - ID of notification
     * @param channelName - Name of notification channel
     */
    fun createNotificationChannel(
        context: Context, channelId: String?, channelName: String?
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH,
            ).apply {
                setShowBadge(true)

                // Sound
                soundUri?.let {
                    val attributes = AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                        .build()
                    setSound(it, attributes)
                }
            }
            val manager = context.getSystemService(NotificationManager::class.java)
            Objects.requireNonNull(manager).createNotificationChannel(serviceChannel)
        }
    }

    /**
     * Build new notification
     *
     * @param context          - Application context
     * @param channelId        - ID of notification channel
     * @param channelName      - Name of notification channel
     * @param notificationIcon - Notification icon
     * @return NotificationCompat.Builder
     */
    fun buildNotification(
        context: Context?,
        channelId: String?,
        channelName: String?,
        notificationIcon: Int,
        contentTitle: String?,
        contentText: String?,
        priority: Int,
        badgeCount: Int? = 0,
        largeIcon: Bitmap? = null,
        pendingIntent: PendingIntent?
    ): Notification {
        val defaults = if (soundUri == null) {
            Notification.DEFAULT_ALL
        } else {
            Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE
        }
        return NotificationCompat.Builder(context!!, channelName!!)
            .setStyle(NotificationCompat.BigTextStyle().bigText(contentText))
            .setSmallIcon(notificationIcon)
            .setColor(Color.argb(255, 137, 206, 170))
            .setChannelId(channelId!!)
            .setContentTitle(contentTitle)
            .setTicker(contentTitle)
            .setContentText(contentText)
            .setLargeIcon(largeIcon)
            .setPriority(priority)
            .setContentIntent(pendingIntent)
            .setDefaults(defaults)
            .setSound(soundUri)
            .setAutoCancel(true)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setNumber(badgeCount ?: 0)
            .build()
    }
}