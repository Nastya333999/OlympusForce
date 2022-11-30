package com.app.mylibraryapps

import com.appsflyer.AppsFlyerConversionListener

interface Aw : AppsFlyerConversionListener {
    override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}
    override fun onAttributionFailure(p0: String?) {}
}
