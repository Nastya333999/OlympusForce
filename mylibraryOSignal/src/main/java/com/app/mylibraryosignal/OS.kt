package com.app.mylibraryosignal

import android.content.Context
import com.onesignal.OneSignal

class OS (context: Context, uid: String) {
    init {
        OneSignal.initWithContext(context)
        OneSignal.setAppId(ID)
        OneSignal.setExternalUserId(uid)
    }

    fun send(campaign: String, deep: String) {
        when {
            campaign == "null" && deep == "null" -> {
                OneSignal.sendTag("key2", "organic")
            }
            deep != "null" -> {
                OneSignal.sendTag("key2", deep.replace("myapp://", "").substringBefore("/"))
            }
            campaign != "null" -> {
                OneSignal.sendTag("key2", campaign.substringBefore("_"))
            }
        }
    }

    companion object {
        private const val ID = "cc89922e-46ef-428f-8c4c-3ae1da67fdaf"
    }
}