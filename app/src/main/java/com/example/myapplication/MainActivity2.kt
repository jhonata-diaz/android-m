package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
class MainActivity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Recupera los datos enviados por el Intent
            val name = intent.getStringExtra("name") ?: "No Name"
            val dosage = intent.getStringExtra("dosage") ?: "No Dosage"
            val time = intent.getStringExtra("time") ?: "No Time"

            // Muestra los datos en una pantalla
            MedicationDetailsScreen(name, dosage, time)
        }
    }
}

@Composable
fun MedicationDetailsScreen(name: String, dosage: String, time: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Medication Details", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Name: $name", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Dosage: $dosage", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Time: $time", style = MaterialTheme.typography.bodyLarge)
    }
}
