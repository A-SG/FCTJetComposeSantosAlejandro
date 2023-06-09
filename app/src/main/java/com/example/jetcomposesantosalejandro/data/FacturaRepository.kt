package com.example.jetcomposesantosalejandro.data

import android.util.Log
import com.example.jetcomposesantosalejandro.data.database.dao.FacturaDao
import com.example.jetcomposesantosalejandro.data.model.FacturaModel
import com.example.jetcomposesantosalejandro.data.network.FacturasService
import com.example.jetcomposesantosalejandro.data.network.domain.model.Factura
import com.example.jetcomposesantosalejandro.data.network.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FacturaRepository @Inject constructor(
    private val facturasDao: FacturaDao,
    private val apiService: FacturasService
) {

    //Obtener facturas de la Api
    suspend fun getFacturasApi(): List<Factura> {
        return withContext(Dispatchers.IO) {
            val response: List<FacturaModel> = apiService.getFacturas().facturas
            Log.d("ConInternet", response.toString())
            response.map { it.toDomain() }
        }
    }

    //Obtener facturas de la base de datos
    suspend fun getFacturasDatabase(): List<Factura> {
        return withContext(Dispatchers.IO) {
            val response: List<FacturaModel> = facturasDao.getFacturas()
            Log.d("BaseDeDatos", response.toString())
            response.map { it.toDomain() }
        }
    }


    suspend fun insertFacturas(facturas: List<FacturaModel>) {
        withContext(Dispatchers.IO)
        {
            facturasDao.insert(facturas)
        }
    }

    suspend fun clearFacturas() {
        withContext(Dispatchers.IO)
        {
            facturasDao.deleteAllFacturas()
        }

    }
}


