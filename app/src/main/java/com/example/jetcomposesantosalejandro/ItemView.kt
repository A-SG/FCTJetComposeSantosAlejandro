package com.example.jetcomposesantosalejandro

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.jetcomposesantosalejandro.data.network.domain.model.Factura


@Composable
fun ItemView(factura:Factura) {

    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Column() {
            Text(text = factura.fecha)

            Text(
                text = factura.descEstado,
                color = colorResource(id = R.color.black)
            )
        }
        Text(text = factura.importeOrdenacion.toString())
    }
}