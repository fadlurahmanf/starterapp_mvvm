package com.fadlurahmanf.starterappmvvm.example.presentation.activity.notification

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.core.shared.constant.AppConstant
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
            title = "Ask notification permission",
            desc = "Ask notification permission",
            enum = "ASK_NOTIFICATION_PERMISSION"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Show Notification",
            desc = "Show simple notification type general",
            enum = "SHOW_NOTIFICATION"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Show Image Notification",
            desc = "Show notification with image",
            enum = "SHOW_IMAGE_NOTIFICATION"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Show Custom Sound Notification",
            desc = "Show notification with custom sound",
            enum = "SHOW_CUSTOM_SOUND_NOTIFICATION"
        ),
        FeatureModel(
            featureIcon = R.drawable.baseline_developer_mode_24,
            title = "Show Grouped Notification",
            desc = "Show notification grouped",
            enum = "SHOW_GROUPED_NOTIFICATION"
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
        ),
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
            "ASK_NOTIFICATION_PERMISSION" -> {
                viewModel.askNotificationPermission(this)
            }

            "SHOW_NOTIFICATION" -> {
                viewModel.showSimpleNotification(this)
            }

            "SHOW_IMAGE_NOTIFICATION" -> {
                viewModel.showImageNotification(this)
            }

            "SHOW_CUSTOM_SOUND_NOTIFICATION" -> {
                viewModel.showCustomSoundNotification(this)
            }

            "SHOW_GROUPED_NOTIFICATION" -> {
                viewModel.showGroupedNotification(
                    this,
                    3,
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