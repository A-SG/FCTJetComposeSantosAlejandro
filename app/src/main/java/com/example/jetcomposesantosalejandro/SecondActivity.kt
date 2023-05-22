package com.example.jetcomposesantosalejandro

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetcomposesantosalejandro.data.network.domain.model.Factura
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SecondActivity : ComponentActivity() {

    private lateinit var jsonFiltroFacturasModel: String

    private val facturasViewModel: FacturasViewModel by viewModels()
    private var json: Gson = Gson()
    private lateinit var facturas: List<Factura>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContenedorVista()

        }
    }



    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun ContenedorVista() {
        Scaffold(
            topBar = { Toolbar() },
            content = { App() }
        )
    }


    @Composable
    fun Toolbar() {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .size(120.dp),
            title = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(id = R.string.activitySecond_toolbar_titulo),
                        color = colorResource(id = R.color.black),
                        style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.icono_salir),
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp, 48.dp)
                            .clickable { finish() }
                    )
                }

            },
            backgroundColor = colorResource(id = R.color.white),
        )
    }

    @Preview
    @Composable
    fun App() {
        val scrollState = rememberScrollState()
        Column() {
            Column(modifier = Modifier
                .verticalScroll(state = scrollState)
                .height(420.dp)) {
                CardViewFiltroFecha()
                CardViewFiltroImporte()
                CardViewFiltroEstado()
            }
            BotonesAplicarYBorrarFiltros()
        }
    }


    @Composable
    fun CardViewFiltroFecha() {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(4.dp))
        ) {
            Column() {
                Text(
                    text = stringResource(id = R.string.activitySecond_cardViewFiltroFecha_titulo),
                    style = TextStyle(fontSize = 15.sp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Column() {
                        Text(
                            text = stringResource(id = R.string.activitySecond_cardViewFiltroFecha_tvFechainicio),
                            style = TextStyle(fontSize = 15.sp)
                        )
                        BotonDatePicker()
                    }
                    Column() {
                        Text(
                            text = stringResource(id = R.string.activitySecond_cardViewFiltroFecha_tvFechaFin),
                            style = TextStyle(fontSize = 15.sp)
                        )
                        BotonDatePicker()
                    }
                }
            }
        }
    }


    @Composable
    fun CardViewFiltroImporte() {
        val range = 0f..100f
        var selection by remember { mutableStateOf(50f) }

        val colorVerde = Color(0xFF79BC2C)
        val colorGris = Color(0xFFD1CECE)
        Card(
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(4.dp))
        ) {

            Column {
                Text(
                    text = stringResource(id = R.string.activitySecond_cardviewFiltroImporte_titulo),
                    style = TextStyle(fontSize = 15.sp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = ("0€"),
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Text(
                        text = facturasViewModel.valorSlider.toInt().toString(),
                        style = TextStyle(fontSize = 15.sp)
                    )
                    Text(
                        text = ("62€"),
                        style = TextStyle(fontSize = 15.sp)
                    )
                }
                SimpleContinuousSlider()
            }
        }
    }


    @Composable
    fun CardViewFiltroEstado() {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(4.dp))
        ) {

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.activitySecond_cardviewFiltroEstado_cbestado),
                    style = TextStyle(fontSize = 15.sp)
                )
                Column() {
                    CheckBox(textoCheckbox = "Pagada")
                    CheckBox(textoCheckbox = "Anuladas")
                    CheckBox(textoCheckbox = "Cuota Fija")
                    CheckBox(textoCheckbox = "Plan de pago")
                    CheckBox(textoCheckbox = "Pendiente de pago")
                }
            }
        }
    }

    @Preview
    @Composable
    fun BotonesAplicarYBorrarFiltros() {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    Log.d("click", "click")
                    val resultIntent = Intent()
                    val listaFacturas: String
                    val json = Gson()
                    listaFacturas = json.toJson(getParametrosEntradaActividad())
                    Log.d("ListaFiltradaParaIntent", listaFacturas)
                    resultIntent.putExtra("ListaFiltrada", listaFacturas)
                    setResult(RESULT_OK, resultIntent)
                    finish()
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF79BC2C),
                    contentColor = Color(0xFFFFFFFF)
                ),
                modifier = Modifier
                    .size(360.dp, 75.dp)
                    .padding(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.activitySecond_btnAplicarFiltro),
                    fontSize = 20.sp
                )

            }

            Button(onClick = {},shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFFFFFF),
                contentColor = Color(0xFF79BC2C)), modifier = Modifier
                .size(360.dp, 75.dp)
                .padding(10.dp)) {
                Text(text = stringResource(id = R.string.activitySecond_btnBorrarFiltro),
                    fontSize = 20.sp)

            }
        }
    }


    @Composable
    fun SimpleContinuousSlider() {
        val range = 0f..100f
        val colorVerde = Color(0xFF79BC2C)
        val colorGris = Color(0xFFD1CECE)

        Slider(
            value = facturasViewModel.valorSlider,
            valueRange = range,
            onValueChange = { facturasViewModel.valorSlider = it},
            colors = SliderDefaults.colors(
                thumbColor = colorVerde,
                activeTrackColor = colorVerde,
                inactiveTickColor = colorGris,
                activeTickColor = colorVerde,
                inactiveTrackColor = colorGris
            )
        )
    }


    @Composable
    fun LabelledCheckbox(
        checked: Boolean = false,
        onCheckedChange: (Boolean) -> Unit,
        label: String,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        colors: CheckboxColors = CheckboxDefaults.colors(checkedColor = Color(0xFFD1CECE), checkmarkColor = Color(0xFF79BC2C), disabledColor =  Color(0xFF79BC2C))) {
        Row(
            modifier = modifier.height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(checked = checked,
                     onCheckedChange = onCheckedChange,
                     enabled = enabled,
                     colors = colors)
            Spacer(Modifier.width(32.dp))
            Text(label)
        }
    }
    @Composable
    fun CheckBox(textoCheckbox :String) {
        val checked = remember { mutableStateOf(true) }
        LabelledCheckbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            label = textoCheckbox
        )
    }


    @Preview
    @Composable
    fun BotonDatePicker(){
        val anio:Int
        val mes:Int
        val dia:Int
        facturasViewModel.valorFecha = getString(R.string.activitySecond_cardviewFiltroFecha_textoBtnFechaInicio)

        val calendar:Calendar = Calendar.getInstance()
        anio = calendar.get(Calendar.YEAR)
        mes = calendar.get(Calendar.MONTH)
        dia = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            LocalContext.current,
            {
                _,anio:Int,mes:Int,dia:Int-> facturasViewModel.valorFecha = "$dia/${mes+1}/$anio"
            },anio,mes,dia
        )

        Button(onClick = {datePickerDialog.show()}, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFD1CECE),
                contentColor = Color(0xFFFFFFFF)
            )
        ) {
            Text(text = facturasViewModel.valorFecha)
        }
    }


    private fun getParametrosEntradaActividad(): List<Factura> {
        //Variables
        jsonFiltroFacturasModel = intent.getStringExtra("listaFacturasSinFiltrar").toString()
        var listaFiltrada = emptyList<Factura>()

        //Obtención de listado de facturas procedentes del main activity
        if (jsonFiltroFacturasModel != null && jsonFiltroFacturasModel.isNotEmpty()) {
            facturas = json.fromJson(jsonFiltroFacturasModel, object : TypeToken<List<Factura?>?>() {}.type)

            listaFiltrada = facturas

            //Filtrado de factura por su importe
                if (listaFiltrada.isEmpty()) {
                    listaFiltrada = facturas.filter { factura: Factura -> factura.importeOrdenacion <= facturasViewModel.valorSlider }
                } else {
                    if (facturasViewModel.valorSlider != 0.0.toFloat()) {
                        listaFiltrada = listaFiltrada.filter { factura: Factura -> factura.importeOrdenacion <= facturasViewModel.valorSlider }
                    }
                }
        }

        facturasViewModel.listaFacturaResponse = listaFiltrada
        Log.d("FacturasFiltradasImporte", facturasViewModel.listaFacturaResponse.toString())

        return listaFiltrada
    }
}

