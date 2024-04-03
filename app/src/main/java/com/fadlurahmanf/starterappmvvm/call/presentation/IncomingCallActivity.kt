package com.fadlurahmanf.starterappmvvm.call.presentation

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import com.fadlurahmanf.starterappmvvm.call.domain.receiver.CallNotificationReceiver
import com.fadlurahmanf.starterappmvvm.databinding.ActivityIncomingCallBinding

class IncomingCallActivity :
    BaseCallActivity<ActivityIncomingCallBinding>(ActivityIncomingCallBinding::inflate) {
    override fun onBaseCallInjectActivity() {
        component.inject(this)
    }

    override fun onBaseExampleCreate(savedInstanceState: Bundle?) {
        setOnApplyWindowInsetsListener(binding.main)

        val notificationId = intent.getIntExtra("NOTIFICATION_ID", -1)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        } else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
            )
        }

        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (keyguardManager.isKeyguardLocked) {
                keyguardManager.requestDismissKeyguard(this@IncomingCallActivity, null)
            }
        }

        binding.ivDecline.setOnClickListener {
            CallNotificationReceiver.sendBroadcastDeclinedCallPendingIntent(this, notificationId)
            finish()
        }

        binding.ivAccept.setOnClickListener {
            CallNotificationReceiver.sendBroadcastAcceptCallPendingIntent(this, notificationId)
            finish()
        }
    }

    override fun onDestroy() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(false)
            setTurnScreenOn(false)
        } else {
            window.clearFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
            )
        }
        super.onDestroy()
    }
}