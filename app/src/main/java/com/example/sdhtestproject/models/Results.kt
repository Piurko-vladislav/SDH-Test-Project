package com.example.sdhtestproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class Results(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "composition") var composition: Composition? = null,
    @ColumnInfo(name = "packaging")var packaging: Packaging? = null,
    @ColumnInfo(name = "trade_label")var trade_label: Trade_label? = null,
    @ColumnInfo(name = "manufacturer")var manufacturer: Manufacturer? = null,
    @ColumnInfo(name = "code")var code: String? = null
)