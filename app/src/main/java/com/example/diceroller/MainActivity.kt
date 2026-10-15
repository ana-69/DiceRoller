package com.example.diceroller

//Al final sí he usado el código de la solución que nos daba el tutorial
// Sigo sin saber porqué no me compila la opción del la carpeta del
// tutorial, porque al final ha quedado igual, salvo que yo he puesto
// Lanzar dado en vez de Roll. (Bueno, yo añado comentarios para
// a la hora de estudiar para el examen tener las cosas más claras).

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme

// Actividad principal de la aplicación en Android
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContent define la interfaz de usuario mediante Jetpack Compose
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }

}

// Función Composable para la vista previa en el panel de diseño de Android Studio
@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

// Función Composable principal de la pantalla
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf( 1) }
    val imageResource = when(result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    // Column organiza los elementos secundarios de manera vertical (uno sobre otro).
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        // Muestra la imagen del dado seleccionada en 'imageResource'
        Image(painter = painterResource(imageResource), contentDescription = result.toString())

        Button(
            // La lambda 'onClick' se ejecuta cada vez que el usuario presiona el botón
            onClick = {
                // Genera un número aleatorio entre 1 y 6.
                // Al cambiar 'result', Compose vuelve a pintar la pantalla (recomposición)
                // actualizando la imagen del dado automáticamente.
                result = (1..6).random() },
        ) {

            // Texto dentro del botón cargado desde el recurso strings.xml (R.string.roll -> "Lanzar dado")
            Text(text = stringResource(R.string.roll), fontSize = 24.sp)
        }
    }
}