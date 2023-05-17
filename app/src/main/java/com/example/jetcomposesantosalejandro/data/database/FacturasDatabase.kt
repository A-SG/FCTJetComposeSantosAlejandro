package com.example.jetcomposesantosalejandro.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetcomposesantosalejandro.data.database.dao.FacturaDao
import com.example.jetcomposesantosalejandro.data.model.FacturaModel


@Database(entities = [FacturaModel::class], version = 1)
abstract class FacturasDatabase : RoomDatabase() {

    abstract fun getFacturas(): FacturaDao
}
