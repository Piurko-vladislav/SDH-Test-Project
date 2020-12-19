package com.example.sdhtestproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class Results(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "composition") val composition: Composition? = null,
    @ColumnInfo(name = "packaging")val packaging: Packaging? = null,
    @ColumnInfo(name = "trade_label")val trade_label: Trade_label? = null,
    @ColumnInfo(name = "manufacturer")val manufacturer: Manufacturer? = null,
    @ColumnInfo(name = "code")val code: String? = null
)