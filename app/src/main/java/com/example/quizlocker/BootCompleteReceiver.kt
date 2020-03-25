package com.example.quizlocker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.preference.PreferenceManager


class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when {

            intent?.action == Intent.ACTION_BOOT_COMPLETED -> {
                Log.d("quizlocker", "부팅이 완료됨")
                context?.let {

                    val pref = PreferenceManager.getDefaultSharedPreferences(context)
                    val useLockScreen = pref.getBoolean("useLockScreen", false)
                    if (useLockScreen) {
                        // LockScreenService 시작
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            it.startForegroundService(Intent(context, LockScreenService::class.java))
                        } else {
                            it.startService(Intent(context, LockScreenService::class.java))
                        }
                    }
                }
            }
        }
    }
}