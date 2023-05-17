package com.example.jetcomposesantosalejandro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetcomposesantosalejandro.data.network.domain.model.Factura
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun ItemView(factura: Factura) {

    val formatoFechaEntrada = SimpleDateFormat("dd/MM/yyyy", Locale.forLanguageTag("es"))
    val formatoFechaSalida = SimpleDateFormat("dd MMM yyyy", Locale.forLanguageTag("es"))
    val cambioFecha = factura.fecha
    val fechaFactura: Date = formatoFechaEntrada.parse(cambioFecha)
    val fechaFormateada: String = formatoFechaSalida.format(fechaFactura)

    Card(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(4.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Row() {
                Image(painter = painterResource(id = R.drawable.bombilla_icon), contentDescription = null, modifier = Modifier
                    .size(48.dp, 48.dp))
                Column() {
                    Text(
                        text = fechaFormateada,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.black)
                    )
                    Text(
                        text = factura.descEstado,
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.black)
                    )
                }
            }
            Text(
                text = stringResource(id = R.string.itemFacturas_simboloMoneda,factura.importeOrdenacion.toString()) ,
                fontSize = 20.sp,
                color = colorResource(id = R.color.black)
            )
        }
    }

}