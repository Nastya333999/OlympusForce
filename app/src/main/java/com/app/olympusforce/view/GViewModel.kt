package com.app.olympusforce.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.olympusforce.R
import com.app.olympusforce.data.DTItem

class GViewModel : ViewModel() {

    var position = MutableLiveData<Int>(0)
    var credits = MutableLiveData<Int>(110)

    private val resIdis = listOf<Int>(
        R.drawable.i1,
        R.drawable.i2,
        R.drawable.i3,
        R.drawable.i4,
        R.drawable.i5,
    )


    val items = MutableLiveData<MutableList<DTItem>>(mutableListOf())

    init {
        addRandomSlot()
        Log.e("true", "Credits  ${credits.value}")

    }

    fun addPosition() {
        if (credits.value == 0) {

            return
        }
        addRandomSlot()
        checkSlot()
        position.value = position.value?.plus(4)
    }

    fun checkSlot() {
        val lastItems = items.value?.takeLast(4)

        val idis: MutableList<MutableList<Int>> = mutableListOf()
        lastItems?.forEach {
            val smallList = mutableListOf<Int>()
            smallList.add(it.resIdfirst)
            smallList.add(it.resIdSecond)
            smallList.add(it.resIdTherd)
            smallList.add(it.resIdFour)

            idis.add(smallList)
        }
        idis.forEachIndexed() { index, it ->
            if (check(it)) {
                credits.value = credits.value?.plus(15)
                Log.e("true", "Win ${it}, $index  ${credits.value}")

            }
        }

        val column1 = mutableListOf<Int>()
        val column2 = mutableListOf<Int>()
        val column3 = mutableListOf<Int>()
        val column4 = mutableListOf<Int>()

        idis.forEach {
            column1.add(it[0])
            column2.add(it[1])
            column3.add(it[2])
            column4.add(it[3])
        }

        if (check(column1)) {
            credits.value = credits.value?.plus(15)
            Log.e("true", "Win column1 ${credits.value}")
        }
        if (check(column2)) {
            credits.value = credits.value?.plus(15)
            Log.e("true", "Win column2 ${credits.value}")
        }
        if (check(column3)) {
            credits.value = credits.value?.plus(15)
            Log.e("true", "Win column3 ${credits.value}")
        }
        if (check(column4)) {
            credits.value = credits.value?.plus(15)
            Log.e("true", "Win column4 ${credits.value}")
        }

        val diagonal1 = mutableListOf<Int>(
            idis[0][0],
            idis[1][1],
            idis[2][2],
            idis[3][3],
        )

        val diagonal2 = mutableListOf<Int>(
            idis[0][3],
            idis[1][2],
            idis[2][1],
            idis[3][0],
        )

        if (check(diagonal1)) {
            credits.value = credits.value?.plus(15)
            Log.e("true", "Win diagonal1 ${credits.value}")
        }
        if (check(diagonal2)) {
            credits.value = credits.value?.plus(15)
            Log.e("true", "Win diagonal2 ${credits.value}")
        }
    }

    private fun check(sours: List<Int>): Boolean {
        return sours.groupingBy { it }.eachCount().filter { it.value == 3 }.isNotEmpty()
    }

    private fun addRandomSlot() {

        Log.e("Credits", "Credistsssss is ${credits.value}")
        credits.value = credits.value?.minus(20)
        Log.e("Credits", "Credistttt is ${credits.value}")
        val list = items.value
        for (i in 1..4) {
            list?.add(
                DTItem(
                    resIdfirst = resIdis.random(),
                    resIdSecond = resIdis.random(),
                    resIdTherd = resIdis.random(),
                    resIdFour = resIdis.random(),
                    id = 1
                )
            )
        }
        items.value = list!!
    }

    fun addMoney() {
        credits.value = credits.value?.plus(100)
    }
}