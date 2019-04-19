package com.catchman.data.github

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object BorderConverters {

    @TypeConverter
    fun stringToBorderList(data: String?): List<String> {
        if (data == null) {
            return emptyList()
        }

        val listType = object : TypeToken<List<String>>() {

        }.type

        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun BorderListToString(someObjects: List<String>): String {
        return Gson().toJson(someObjects)
    }
}
