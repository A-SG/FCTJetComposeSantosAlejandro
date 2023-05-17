package com.example.jetcomposesantosalejandro.data.network.domain.model

import com.example.jetcomposesantosalejandro.data.model.FacturaModel

data class Factura (var descEstado : String, var importeOrdenacion: Double, var fecha: String)

fun FacturaModel.toDomain() = Factura(descEstado, importeOrdenacion, fecha)
