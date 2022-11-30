package com.app.mylibraryapps

import android.content.Context
import com.appsflyer.AppsFlyerLib

class Apssss {
    companion object {
        private val AFK = "AUHdz8siguwvzq7uNLuxHZ"

        fun gAf(context: Context, result: (MutableMap<String, Any>?) -> Unit) {
            val callback = object : Aw {
                override fun onConversionDataSuccess(convData: MutableMap<String, Any>?) {
                    result.invoke(convData)
                }

                override fun onConversionDataFail(p0: String?) {
                    result.invoke(null)
                }
            }
            AppsFlyerLib.getInstance().init(AFK, callback, context)
            AppsFlyerLib.getInstance().start(context)

        }

        fun uid(context: Context): String {
            return AppsFlyerLib.getInstance().getAppsFlyerUID(context)!!.toString()
        }
    }

}
