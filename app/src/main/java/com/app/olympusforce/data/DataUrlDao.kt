package com.app.olympusforce.data

import androidx.room.*

@Dao
interface DataUrlDao {

    @Query("SELECT * FROM DataUrl ")
    fun getAll(): List<DataUrl>

    @Insert
    fun insert(data: DataUrl)

    @Update
    fun update(data: DataUrl)

    @Query("DELETE FROM DataUrl")
    fun deleteAll()
}