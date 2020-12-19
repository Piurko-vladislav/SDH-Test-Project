package com.example.sdhtestproject.db.converters

import androidx.room.TypeConverter
import com.example.sdhtestproject.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResultTypeConverter {

    @TypeConverter
    fun fromTradeLabel(value: String): Trade_label {
        val type = object : TypeToken<Trade_label>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toTradeLabel(value: Trade_label): String {
        val type = object : TypeToken<Trade_label>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromManufacturer(value: String): Manufacturer {
        val type = object : TypeToken<Manufacturer>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toManufacturer(value: Manufacturer): String {
        val type = object : TypeToken<Manufacturer>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromPackaging(value: String): Packaging {
        val type = object : TypeToken<Packaging>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toPackaging(value: Packaging): String {
        val type = object : TypeToken<Packaging>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromInn(value: String): Inn {
        val type = object : TypeToken<Inn>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toInn(value: Inn): String {
        val type = object : TypeToken<Inn>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromPharmForm(value: String): PharmForm {
        val type = object : TypeToken<PharmForm>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toPharmForm(value: PharmForm): String {
        val type = object : TypeToken<PharmForm>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun fromComposition(value: String): Composition {
        val type = object : TypeToken<Composition>() {}.type
        return Gson().fromJson(value, type)    }

    @TypeConverter
    fun toComposition(value: Composition): String? {
        val type = object : TypeToken<Composition>() {}.type
        return Gson().toJson(value, type)
    }

}