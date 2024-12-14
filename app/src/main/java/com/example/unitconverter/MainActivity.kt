package com.example.unitconverter

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement.Center as Center1
import androidx.compose.ui.platform.LocalContext as LocalContext1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {  padding->
                  UnitConverter()
                }
            }
        }
    }
}



@Composable
fun UnitConverter(){
    var inputValue by remember{ mutableStateOf("")}
    var outputValue by remember{ mutableStateOf("")}
    var inputUnit by remember{ mutableStateOf("meters")}
    var outputUnit by remember{ mutableStateOf("meters  ")}
    var isExpanded by remember{ mutableStateOf(false)}
    var oExpanded by remember{ mutableStateOf(false)}
    val conversionFactor= remember {
        mutableStateOf(1.00)
    }
    val oconversionFactor= remember {
        mutableStateOf(1.00)
    }

    fun converUnits(){

        val inputValueDouble=inputValue.toDoubleOrNull()?:0.0
        val result=(inputValueDouble*conversionFactor.value*100.0/oconversionFactor.value).roundToInt()/100.0
        outputValue=result.toString()
    }
    Spacer(modifier = Modifier.height(100.dp))
    Image(
        painter = painterResource(id = R.drawable.img), // Replace with your drawable image
        contentDescription = "Top Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), // Adjust height as needed
       // Scales the image to fill the width while cropping excess
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            ,


        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text("UNIT CONVERTER" , style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            fontFamily = FontFamily.Serif,


            textDecoration = TextDecoration.Underline,
            lineHeight = 24.sp,// Line height to improve readability
            letterSpacing = 2.5.sp
        ),
            modifier = Modifier
                .background(Color(0xFF03DAC5)) // Custom teal background
                .padding(10.dp) // Padding inside the text
                .fillMaxWidth() // Fill the width of the parent),
            ,textAlign = TextAlign.Center


        )
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue=it
        },
            label = { Text("please enter value")})

       Row {
           Box {
               val context=LocalContext1.current
               Button(onClick = {isExpanded=true}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)), // Styling the button
                 )

               {
                   Text(text = inputUnit,color = Color.Green, // Text color inside the button
                       fontSize = 16.sp, // Font size for button text
                       modifier = Modifier.padding(8.dp))
                   Icon(Icons.Default.ArrowDropDown , contentDescription = "unit which you want to convert ")

               }
               DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded=false }) {
                   DropdownMenuItem(text = { Text("kilometers") },
                       onClick = {


                           isExpanded=false
                           inputUnit="kilometers"
                           conversionFactor.value=1000.00
                           converUnits()

                       })
                   DropdownMenuItem(text = { Text("meters") },
                       onClick = {
                           isExpanded=false
                           inputUnit="meters"
                           conversionFactor.value=1.00
                           converUnits()

                       })
                   DropdownMenuItem(text = { Text("centemeters") },
                       onClick = {
                           isExpanded=false
                           inputUnit="centemeters"
                           conversionFactor.value=0.01
                           converUnits()
                       })
                   DropdownMenuItem(text = { Text("feet") },
                       onClick = {
                           isExpanded=false
                           inputUnit="kilometers"
                           conversionFactor.value=0.3048
                           converUnits()
                       })


               }

           }

           Box {
               val context=LocalContext1.current
               Button(onClick = {oExpanded=true}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)), // Styling the button

                  )   {
                   Text(text = outputUnit,
                       color = Color.Green, // Text color inside the button
                       fontSize = 16.sp, // Font size for button text
                       modifier = Modifier.padding(8.dp))
                   Icon(Icons.Default.ArrowDropDown , contentDescription = "unit which you want to convert ")

               }
               DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                   DropdownMenuItem(text = { Text("kilometers") },
                       onClick = {
                           oExpanded=false
                           outputUnit="kilometers"
                           oconversionFactor.value=1000.00
                           converUnits()
                       })
                   DropdownMenuItem(text = { Text("meters") },
                       onClick = {
                           oExpanded=false
                           outputUnit="meters"
                           oconversionFactor.value=1.00
                           converUnits()
                       })
                   DropdownMenuItem(text = { Text("centemeters") },
                       onClick = {
                           oExpanded=false
                           outputUnit="centemeters"
                           oconversionFactor.value=0.01
                           converUnits()
                       })
                   DropdownMenuItem(text = { Text("feet") },
                       onClick = {
                           oExpanded=false
                           outputUnit="feet"
                           oconversionFactor.value=0.3048
                           converUnits()
                       })

                   
               }


           }


       }
        Spacer(modifier = Modifier.height(16.dp))
        Text("RESULT"
       , style = TextStyle(
                color = Color.Blue, // Text color
                fontSize = 24.sp, // Font size
                fontWeight = FontWeight.Bold, // Font weight
                letterSpacing = 2.sp, // Letter spacing
                textAlign = TextAlign.Center // Text alignment
            ),)
        Text("$outputValue" , color= Color.Red,
            style = TextStyle(
                color = Color.Blue, // Txt color
                fontSize = 24.sp, // Font size
                fontWeight = FontWeight.Bold, // Font weight
                letterSpacing = 2.sp, // Letter spacing
                textAlign = TextAlign.Center // Text alignment
            ),)


        Box(modifier = Modifier.height(100.dp)) // 16.dp gap
        Image(
            painter = painterResource(id = R.drawable.img_1), // Replace with your drawable image
            contentDescription = "Top Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), // Adjust height as needed
          // Scales the image to fill the width while cropping excess
        )
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview @Composable
fun UnitConverterpreview(){
    UnitConverter()
}