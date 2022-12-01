package com.app.olympusforce

import android.app.Application
import androidx.room.Room
import com.app.mylibraryapps.Apssss
import com.app.mylibraryfb.Fb
import com.app.mylibraryosignal.OS
import com.app.olympusforce.data.AppDatabase
import com.app.olympusforce.data.FileDataCreator
import com.google.android.gms.ads.identifier.AdvertisingIdClient

class App : Application() {

    lateinit var dataBase: AppDatabase

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "DataUrl"
        ).allowMainThreadQueries().build()
    }

    fun init(callbacks: (String) -> Unit) {
        Apssss.gAf(this) { apps ->

            Fb.dF(this) { deep ->
                val uId = Apssss.uid(this)
                val adId = AdvertisingIdClient.getAdvertisingIdInfo(this@App).id.toString()
                val oneWrapper = OS(this@App, adId)
                oneWrapper.send(apps?.get("campaign").toString(), deep)

                callbacks.invoke(
                    FileDataCreator.create(
                        res = this@App.resources,
                        baseFileData = Const.BAU,
                        gadid = adId,
                        apps = if (deep == "null") apps else null,
                        deep = deep,
                        uid = if (deep == "null") uId else null
                    )
                )
            }
        }
    }
}