package com.example.sdhtestproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sdhtestproject.view_models.BaseViewModel

@Entity(tableName = "results")
class DbResults(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "trade_label_name")var db_trade_label: String? = null,
    @ColumnInfo(name = "manufacturer_name")var db_manufacturer_name: String? = null,
    @ColumnInfo(name = "packaging_name")var db_packaging_name: String? = null,
    @ColumnInfo(name = "composition_discription")var db_composition_discription: String? = null,
    @ColumnInfo(name = "composition_inn_name")var db_composition_inn_name: String? = null,
    @ColumnInfo(name = "composition_pharm_form_name")var db_composition_pharm_form_name: String? = null): BaseViewModel() {
}