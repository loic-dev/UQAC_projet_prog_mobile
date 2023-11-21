package com.example.projet_prog_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import com.example.projet_prog_mobile.data.HardwareStepCounterSource
import com.example.projet_prog_mobile.util.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val modifier = Modifier
                .padding(resources.getDimension(R.dimen.screen_padding).toInt().dp)
            Navigation(modifier)
        }
    }

    //appel lors du focus de l'app
    override fun onResume() {
        super.onResume()

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()


        val stepWorkerRequest = OneTimeWorkRequestBuilder<HardwareStepCounterSource>()
            .setConstraints(constraints)
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()


        WorkManager.getInstance(applicationContext).enqueueUniqueWork("step_counter", ExistingWorkPolicy.KEEP, stepWorkerRequest)
    }
}