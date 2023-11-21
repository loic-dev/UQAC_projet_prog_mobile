package com.example.projet_prog_mobile.data

import android.app.Notification.FOREGROUND_SERVICE_IMMEDIATE
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context;
import android.content.pm.ServiceInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener;
import android.hardware.SensorManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.Worker;
import androidx.work.WorkerParameters
import com.example.projet_prog_mobile.R
import kotlinx.coroutines.delay

class HardwareStepCounterSource(private val context:Context, params: WorkerParameters): CoroutineWorker(context, params), SensorEventListener {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val sensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    private var numberOfStep = 0
    private var baseStepNumber: Int? = null

    override fun onSensorChanged(event: SensorEvent?) {
        event?.also {
            if (baseStepNumber == null) {
                baseStepNumber = it.values[0].toInt()
            }

            numberOfStep += (it.values[0].toInt() - baseStepNumber!!)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not implemented
    }

    override suspend fun doWork(): Result {
        if (stepCounterSensor == null) {
            return Result.failure()
        }

        sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL)

        setForeground(getForegroundInfo())
        update()
        return Result.success()
    }

    private suspend fun update() {
        while (!isStopped) {
            delay(5000)
            setForeground(getForegroundInfo())
        }
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return createForegroundInfo()
    }

    private fun createForegroundInfo(): ForegroundInfo {
        val title = "Steps"
        val channelId = "com.example.projet_prog_mobile.STEP_CHANNEL_ID"


        var notificationBuilder:NotificationCompat.Builder
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel(channelId)
            notificationBuilder = NotificationCompat.Builder(context, channelId)
        } else {
            notificationBuilder = NotificationCompat.Builder(context)
        }


        val notification = notificationBuilder
            .setContentTitle(title)
            .setTicker(title)
            .setContentText("$numberOfStep steps")
            .setOngoing(true)
            .setForegroundServiceBehavior(FOREGROUND_SERVICE_IMMEDIATE)
            .build()

        return ForegroundInfo(0, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_HEALTH)



    }

    private fun createChannel(channelId:String){
        val name = context.getString(R.string.channel_name)
        val descriptionText = context.getString(R.string.channel_description)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            // Register the channel with the system
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }



}