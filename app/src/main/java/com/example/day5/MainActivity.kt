package com.example.day5

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.day5.ui.theme.Day5Theme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Day5Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Greeting("Android")
                    UnitConvertor()
                }

            }
        }
    }
}


@Composable
fun UnitConvertor(){
    var inputValue by remember{ mutableStateOf("") }
    var outputValue by remember{mutableStateOf("")}
    var inputUnit by remember{ mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var isExpanded by remember{ mutableStateOf(false) }
    var oExpanded by remember {  mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oconversionFactor = remember { mutableStateOf(1.00) }



    fun ConvertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
        val result = (inputValueDouble*conversionFactor.value*100.0/oconversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
//        Greeting(name = "Mohamed Abdiaziz")
//        Greeting(name = "Omar Abdiaziz"
        Text("Unit Convertor",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp,
                color = Color.DarkGray,
                fontFamily = FontFamily.Monospace
            )
        )
        Spacer(modifier= Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue= it

        },
            label = { Text(text = "Enter Value")})
        Spacer(modifier= Modifier.height(16.dp))


        Row {
      Box{
          Button(onClick = {isExpanded=true }) {

              Text(inputUnit)
              Icon(Icons.Default.ArrowDropDown,
                  contentDescription = "")
          }
          DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded=false }) {
              DropdownMenuItem(text = {Text("Centimeters") }, onClick = {
                  isExpanded = false
                  inputUnit="Centimeters"
                  conversionFactor.value=0.01
                  ConvertUnits()
              })
              DropdownMenuItem(text = {Text("Meters") }, onClick = {
                  isExpanded = false
                  inputUnit="Meters"
                  conversionFactor.value=1.0
                  ConvertUnits()
              })

              DropdownMenuItem(text = {Text("Feet") }, onClick = {  isExpanded = false
                  inputUnit="Feet"
                  conversionFactor.value=0.3048
                  ConvertUnits() })


          }

      }
            Spacer(modifier = Modifier.width(16.dp))
        Box{
            Button(onClick = { oExpanded=true }) {

                Text(outputUnit)
                Icon(Icons.Default.ArrowDropDown,
                    contentDescription = "")
            }
            DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                DropdownMenuItem(text = {Text("Centimeters") }, onClick = {
                    oExpanded = false
                    outputUnit="Centimeters"
                    oconversionFactor.value=0.01
                    ConvertUnits()
                })
                DropdownMenuItem(text = {Text("Meters") }, onClick = {
                    oExpanded = false
                    outputUnit="Meters"
                   oconversionFactor.value=1.0
                    ConvertUnits()
                })

                DropdownMenuItem(text = {Text("Feet") }, onClick = {  oExpanded = false
                    outputUnit="Feet"
                    oconversionFactor.value=0.3048
                    ConvertUnits() })


            }

        }
    }
        Spacer(modifier= Modifier.height(16.dp))

        Text(text = "Result is : $outputValue $outputUnit",

            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp,
                color = Color.DarkGray,
                fontFamily = FontFamily.Monospace
            )

        )
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConvertorPreview(){
    UnitConvertor()
}