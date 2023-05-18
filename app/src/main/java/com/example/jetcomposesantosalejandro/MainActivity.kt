package com.example.jetcomposesantosalejandro


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetcomposesantosalejandro.data.network.domain.model.Factura
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val facturasViewModel by viewModels<FacturasViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {
                Toolbar()
                ListaFactura(listaFacturas = facturasViewModel.listaFacturaResponse)
                facturasViewModel.getListaFacturas()
            }
        }
    }
    @Composable
    fun Toolbar() {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .size(120.dp),
            title = {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Column() {
                        Button(
                            onClick = { /*....*/ },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFFFFFFF),
                                contentColor = Color(0xFF79BC2C))
                        ) {
                            Text(
                                text = stringResource(id = R.string.activityMain_toolbar_tvConsumo),
                                color = colorResource(id = R.color.green)
                            )
                        }

                        Text(
                            text = stringResource(id = R.string.activityMain_toolbar_tvFacturas),
                            color = colorResource(id = R.color.black),
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp

                        )
                    }
                    //ImageButton()
                    Image(painter = painterResource(id = R.drawable.icono_filtro), contentDescription = null, modifier = Modifier.size(48.dp, 48.dp)
                        .clickable { val navigate = Intent(this@MainActivity,SecondActivity::class.java)
                        startActivity(navigate)})
                }

            },
            backgroundColor = colorResource(id = R.color.white),
        )
    }



    @Composable
    fun ListaFactura(listaFacturas: List<Factura>) {
        LazyColumn(){
            itemsIndexed(items = listaFacturas){_, item ->  ItemView(factura = item)}
        }
    }
    //Index sustituido por _


}





