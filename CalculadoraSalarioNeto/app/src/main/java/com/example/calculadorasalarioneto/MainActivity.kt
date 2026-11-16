package com.example.calculadorasalarioneto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var vista by remember { mutableStateOf(1) }
            var edad by remember { mutableStateOf("") }
            var ecivil by remember { mutableStateOf("") }
            var gradod by remember { mutableStateOf("") }
            var grupop by remember { mutableStateOf("") }
            var nhijos by remember { mutableStateOf("") }
            var npagas by remember { mutableStateOf("") }
            var salarioba by remember { mutableStateOf("") }

            var salariobruto by remember { mutableStateOf("") }
            var irpf by remember { mutableDoubleStateOf(0.0) }
            var ssocial by remember { mutableDoubleStateOf(0.0) }
            var deducciones by remember { mutableDoubleStateOf(0.0) }
            var salarioneto by remember { mutableDoubleStateOf(0.0) }
            LaunchedEffect(Unit) {
                delay(5000)
                vista = 2
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
            ) {
                when (vista) {
                    1 -> {
                        Text("Bienvenido a la calculadora", fontSize = 32.sp)
                        Text("A continuación introduce tus datos", fontSize = 16.sp)
                    }
                    2 -> {
                        OutlinedTextField(
                            value = edad,
                            onValueChange = { edad = it },
                            label = { Text("Edad") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        OutlinedTextField(
                            value = ecivil,
                            onValueChange = { ecivil = it },
                            label = { Text("Estado civil") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )
                        OutlinedTextField(
                            value = gradod,
                            onValueChange = { gradod = it },
                            label = { Text("Grado de discapacidad") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                        OutlinedTextField(
                            value = grupop,
                            onValueChange = { grupop = it },
                            label = { Text("Categoría profesional") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        OutlinedTextField(
                            value = nhijos,
                            onValueChange = { nhijos = it },
                            label = { Text("Número de hijos") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        OutlinedTextField(
                            value = npagas,
                            onValueChange = { npagas = it },
                            label = { Text("Pagas anuales") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        OutlinedTextField(
                            value = salarioba,
                            onValueChange = { salarioba = it },
                            label = { Text("Salario bruto anual") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                        Button(onClick = {
                            vista = 3
                            salariobruto = salarioba
                            var salarioba2 = salarioba.toDoubleOrNull() ?: 0.0
                            var npagas2 = npagas.toDoubleOrNull() ?: 0.0
                            irpf = salarioba2 * 15 / 100
                            ssocial = salarioba2 * 6.5 / 100
                            deducciones = irpf + ssocial
                            salarioneto = salarioba2 - irpf - ssocial / npagas2

                        }) {
                            Text("Calcular Salario Neto")
                        }
                    }
                    3 -> {
                        Text("Salario bruto: " + salariobruto, fontSize = 32.sp)
                        Text("Deducciones: " + deducciones, fontSize = 32.sp)
                        Text("Salario neto: " + salarioneto, fontSize = 32.sp)
                    }
                }
            }
        }
    }
}
