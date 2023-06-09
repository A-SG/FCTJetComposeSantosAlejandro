package com.example.jetcomposesantosalejandro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetcomposesantosalejandro.data.network.domain.GetFacturasUseCase
import com.example.jetcomposesantosalejandro.data.network.domain.model.Factura
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacturasViewModel @Inject constructor(private val getFacturasUseCase: GetFacturasUseCase) :
    ViewModel() {

    //Variables
    var listaFacturaResponse: List<Factura> by mutableStateOf(listOf())
    var listaFacturaResponseIntent: List<Factura> by mutableStateOf(listOf())
    var valorSlider : Float by mutableStateOf(50F)
    var valorFecha : String by mutableStateOf("")

    fun getListaFacturas(){
        viewModelScope.launch {
            val result = getFacturasUseCase()
            listaFacturaResponse = result
        }
    }
}
