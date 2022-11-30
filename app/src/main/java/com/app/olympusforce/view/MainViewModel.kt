package com.app.olympusforce.view

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.olympusforce.App
import com.app.olympusforce.data.DataUrl

class MainViewModel(private val app: Application) : AndroidViewModel(app) {

    val _data = MutableLiveData<String>()

    fun viewModelInit() {
        app as App
        if (app.dataBase.dataUrlDao().getAll().isNotEmpty()) {
            _data.postValue( (app).dataBase.dataUrlDao().getAll().first().uri)
            Log.e("mvm", "uri is ${_data.value}")
        } else {
            app.init {
                _data.postValue(it)
                Log.e("mvm2", "uri is ${_data.value}")

            }
        }
    }

    fun saveUrl(url: String) {

            val dao = (app as App).dataBase.dataUrlDao()

        val dataUrls = dao.getAll()

        if (dataUrls.isEmpty()) {
            dao.insert(DataUrl(0, url))
        } else {
//                val data = dataUrls.first()
//                val updatedData = data.copy(uri = url)
//                dao.update(updatedData)
            }

    }
}
