package com.example.jetcomposesantosalejandro.data.network

import com.example.jetcomposesantosalejandro.data.model.NumFactura
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("facturas")
    suspend fun getFacturas() : Response<NumFactura>
}