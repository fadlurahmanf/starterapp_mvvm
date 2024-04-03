package com.fadlurahmanf.starterappmvvm.example.presentation.activity.notification

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.core.constant.AppConstant
import com.fadlurahmanf.starterappmvvm.call.domain.player.CallNotificationPlayerService
import com.fadlurahmanf.starterappmvvm.databinding.ActivityExampleNotificationBinding
import com.fadlurahmanf.starterappmvvm.example.data.model.FeatureModel
import com.fadlurahmanf.starterappmvvm.example.presentation.BaseExampleActivity
import com.fadlurahmanf.starterappmvvm.example.presentation.utilities.notification.ExampleNotificationUtility
import com.fadlurahmanf.starterappmvvm.example.presentation.utilities.recycle_view.ListExampleAdapter
import javax.inject.Inject
import kotlin.random.Random

class ExampleNotificationActivity :
    BaseExampleActivity<ActivityExampleNotificationBinding>(ActivityExampleNotificationBinding::inflate),
    ListExampleAdapter.Callback {
    var incomingCallNotificationId: Int? = null
    override fun onBaseExampleInjectActivity() {
        component.inject(this)
    }

    private val features: List<FeatureModel> = listOf<FeatureModel>(
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Notification Permission Status",
            desc = "Check whether permission status of notification is granted",
            enum = "IS_PERMISSION_NOTIFICATION_GRANTED"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Ask notification permission",
            desc = "Ask notification permission",
            enum = "ASK_NOTIFICATION_PERMISSION"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Show notification",
            desc = "Show simple notification type general",
            enum = "SHOW_NOTIFICATION"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Show notification 2",
            desc = "Show simple notification type detail",
            enum = "SHOW_NOTIFICATION_2"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Show Chat Notification",
            desc = "Show chat notification with different sound",
            enum = "SHOW_NOTIFICATION_3"
        ), FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Show Image Notification",
            desc = "Show notification with image",
            enum = "SHOW_IMAGE_NOTIFICATION"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Show Incoming Call Notification",
            desc = "Show incoming call notification",
            enum = "SHOW_INCOMING_CALL_NOTIFICATION"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Cancel Incoming Call Notification",
            desc = "Cancel incoming call notification",
            enum = "CANCEL_INCOMING_CALL_NOTIFICATION"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Start Notification Player",
            desc = "Ringing incoming call notification",
            enum = "START_NOTIFICATION_INCOMING_CALL_PLAYER"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Stop Notification Player",
            desc = "Stop ringing incoming call notification",
            enum = "STOP_NOTIFICATION_INCOMING_CALL_PLAYER"
        )
    )

    private lateinit var adapter: ListExampleAdapter

    override fun onBaseExampleCreate(savedInstanceState: Bundle?) {
        setOnApplyWindowInsetsListener(binding.main)
        binding.rv.setItemViewCacheSize(features.size)
        binding.rv.setHasFixedSize(true)

        adapter = ListExampleAdapter()
        adapter.setCallback(this)
        adapter.setList(features)
        adapter.setHasStableIds(true)
        binding.rv.adapter = adapter

        val isGoToNotificationDetail = intent.getBooleanExtra("IS_GO_TO_NOTIFICATION_DETAIL", false)
        if (isGoToNotificationDetail) {
            val notificationId = intent.getIntExtra("NOTIFICATION_ID", -1)
            val intent = Intent(this, ExampleNotificationDetailActivity::class.java)
            intent.apply {
                putExtra("DEBUG_TEXT", "NOTIFICATION_ID:$notificationId")
            }
            startActivity(intent)
        }
    }

    @Inject
    lateinit var viewModel: ExampleNotificationViewModel

    override fun onClicked(item: FeatureModel) {
        when (item.enum) {
            "IS_PERMISSION_NOTIFICATION_GRANTED" -> {
                val isGranted = viewModel.checkIsPermissionGranted(this)
                Log.d(AppConstant.LOGGER_TAG, "IS NOTIFICATION GRANTED: $isGranted")
            }

            "ASK_NOTIFICATION_PERMISSION" -> {
                viewModel.askNotificationPermission(
                    this,
                    onGranted = {
                        Log.d(AppConstant.LOGGER_TAG, "NOTIFICATION PERMISSION ALREADY GRANTED")
                    },
                    onShouldShowRequestPermissionRationale = {
                        Log.d(AppConstant.LOGGER_TAG, "ASK NOTIFICATION PERMISSION")
                    },
                    onLaunchPermission = {
                        Log.d(AppConstant.LOGGER_TAG, "ON LAUNCH PERMISSION")
                    },
                )
            }

            "SHOW_NOTIFICATION" -> {
                val randomInt = Random.nextInt(99)
                viewModel.showSimpleNotification(
                    this,
                    randomInt,
                    "Random Title General 1 $randomInt",
                    "Random Message General 1 $randomInt",
                    ExampleNotificationUtility.getPendingIntentNotificationDetail(this, randomInt)
                )
            }

            "SHOW_NOTIFICATION_2" -> {
                val randomInt = Random.nextInt(99)
                viewModel.showSimpleNotification(
                    this,
                    randomInt,
                    "Random Title Detail 2 $randomInt",
                    "Random Message Detail 2 $randomInt",
                    ExampleNotificationUtility.getPendingIntentNotificationDetail(this, randomInt)
                )
            }

            "SHOW_NOTIFICATION_3" -> {
                val randomInt = Random.nextInt(99)
                viewModel.showChatNotification(
                    this,
                    randomInt,
                    "Random Title Chat $randomInt",
                    "Random Message Chat $randomInt",
                )
            }

            "SHOW_IMAGE_NOTIFICATION" -> {
                val randomInt = Random.nextInt(99)
                viewModel.showImageNotification(
                    this,
                    randomInt,
                    "Random Title Image $randomInt",
                    "Random Message Image $randomInt",
                    "https://raw.githubusercontent.com/TutorialsBuzz/cdn/main/android.jpg"
                )
            }

            "SHOW_INCOMING_CALL_NOTIFICATION" -> {
                incomingCallNotificationId = 0
                viewModel.showIncomingCallNotification(
                    this,
                    incomingCallNotificationId!!,
                    callerName = "Taufik Dev",
                    phoneNumberCaller = "081283602320"
                )
            }

            "CANCEL_INCOMING_CALL_NOTIFICATION" -> {
                if (incomingCallNotificationId != null) {
                    viewModel.cancelIncomingCallNotification(this, incomingCallNotificationId!!)
                }
            }

            "START_NOTIFICATION_INCOMING_CALL_PLAYER" -> {
                CallNotificationPlayerService.startIncomingCallNotificationPlayer(this)
            }

            "STOP_NOTIFICATION_INCOMING_CALL_PLAYER" -> {
                CallNotificationPlayerService.stopIncomingCallNotificationPlayer(this)
            }
        }
    }
}