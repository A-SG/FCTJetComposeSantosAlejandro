package com.example.jetcomposesantosalejandro

import com.example.jetcomposesantosalejandro.data.FacturaRepository
import com.example.jetcomposesantosalejandro.data.model.toDatabase
import com.example.jetcomposesantosalejandro.data.network.domain.model.Factura

import javax.inject.Inject

class GetFacturasUseCase @Inject constructor(private val repository : FacturaRepository){

    suspend operator fun invoke(): List<Factura> {
        val facturas = repository.getFacturasApi()

        return if (facturas.isNotEmpty()){
            repository.clearFacturas()
            repository.insertFacturas(facturas.map { it.toDatabase() })
            facturas
        }else{
            repository.getFacturasDatabase()
        }
    }
}