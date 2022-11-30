package com.app.olympusforce.data

import android.content.res.Resources
import androidx.core.net.toUri
import com.app.olympusforce.Const
import com.app.olympusforce.R
import java.util.*

class FileDataCreator {
    companion object {
        fun create(
            res: Resources, baseFileData: String,
            gadid: String, apps: MutableMap<String, Any>?,
            deep: String, uid: String?
        ): String {
            val url = baseFileData.toUri().buildUpon().apply {
                appendQueryParameter(
                    res.getString(R.string.fdghjk),
                    res.getString(R.string.nbvcx)
                )
                appendQueryParameter(
                    res.getString(R.string.oijhihiudnvdjfn),
                    TimeZone.getDefault().id
                )
                appendQueryParameter(res.getString(R.string.qwerf), gadid)
                appendQueryParameter(res.getString(R.string.oiuygbn), deep)
                appendQueryParameter(
                    res.getString(R.string.cvgbn),
                    if (deep != "null") "deeplink" else apps?.get("media_source").toString()
                )
                appendQueryParameter(
                    res.getString(R.string.crtvybhnj),
                    uid
                )
                appendQueryParameter(
                    res.getString(R.string.wasdfgb),
                    apps?.get("adset_id").toString()
                )
                appendQueryParameter(
                    res.getString(R.string.iuytr),
                    apps?.get("campaign_id").toString()
                )
                appendQueryParameter(
                    res.getString(R.string.nhuij),
                    apps?.get("campaign").toString()
                )
                appendQueryParameter(
                    res.getString(R.string.qasdfv),
                    apps?.get("adset").toString()
                )
                appendQueryParameter(
                    res.getString(R.string.uytrew),
                    apps?.get("adgroup").toString()
                )
                appendQueryParameter(
                    res.getString(R.string.cdsasd),
                    apps?.get("orig_cost").toString()
                )
                appendQueryParameter(
                    res.getString(R.string.af_siteid_key),
                    apps?.get("af_siteid").toString()
                )
            }.toString()
            return Const.PR + url
        }
    }
}