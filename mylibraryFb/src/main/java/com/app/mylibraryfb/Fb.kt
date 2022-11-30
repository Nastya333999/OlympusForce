package com.app.mylibraryfb

import android.content.Context
import com.facebook.applinks.AppLinkData
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Fb {
    companion object {
        fun dF(context: Context, resume: (String) -> Unit) {
            val callback = AppLinkData.CompletionHandler {
                resume.invoke(it?.targetUri.toString())
            }
            AppLinkData.fetchDeferredAppLinkData(context, callback)
        }
    }


}