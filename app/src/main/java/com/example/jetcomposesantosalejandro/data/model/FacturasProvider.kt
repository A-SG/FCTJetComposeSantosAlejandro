package com.example.jetcomposesantosalejandro.data.model


import javax.inject.Inject

class FacturasProvider @Inject constructor(){
    var facturas:List<FacturaModel> = emptyList()
}