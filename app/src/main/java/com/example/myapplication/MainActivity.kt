package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import data.Task
import data.TaskController
import fruit.FruitViewModel
import medic.Medication
import medic.MedicationController
import medic.MedicationController2
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Create MedicationController instance
        val medicationController = MedicationController()

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Collect the state of medications as a Compose state
                    val medications = medicationController.medications.collectAsState()

                    // Pass the collected medications to MedicationScreen2
                    MedicationScreen2(
                        viewModel = MedicationController2(),
                        context = this // Pass context from the Activity
                    )
                }
            }
        }
    }
}




//version 1.2 medical-------------------------------------------


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MedicationScreen2(viewModel: MedicationController2, context: Context) {
    var name by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }
    var time by remember { mutableStateOf(LocalTime.now()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Medications Reminder",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Input field for medication name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Medication Name") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Input field for dosage
        OutlinedTextField(
            value = dosage,
            onValueChange = { dosage = it },
            label = { Text("Dosage") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Button to add medication
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (name.isNotBlank() && dosage.isNotBlank()) {
                    viewModel.addMedication(name, dosage, time)
                    name = ""
                    dosage = ""
                    time = LocalTime.now()
                }
            }
        ) {
            Text("Add Medication")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of medications
        LazyColumn {
            items(viewModel.medications) { medication ->
                MedicationItem(medication, viewModel, context) // Pass context to MedicationItem
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MedicationItem(medication: Medication, viewModel: MedicationController2, context: Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                // Cuando se haga clic, navega al segundo Activity y pasa los datos del medicamento
                openSecondActivity(context, medication)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = medication.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Dosage: ${medication.dosage}", style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = "Time: ${medication.time.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(onClick = { viewModel.removeMedication(medication.id) }) {
                Icon(Icons.Default.Delete, contentDescription = "Remove Medication")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun openSecondActivity(context: Context, medication: Medication) {
    val intent = Intent(context, MainActivity2::class.java)
    // Usar putExtra para pasar los datos al segundo Activity
    intent.putExtra("name", medication.name)
    intent.putExtra("dosage", medication.dosage)
    intent.putExtra("time", medication.time.format(DateTimeFormatter.ofPattern("HH:mm")))
    context.startActivity(intent)
}




//version 1.1 de medical ------------------------------------------------------------------------------------------------------------------------------------------
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MedicationScreen22(viewModel: MedicationController2, context: Context) {
    var name by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }
    var time by remember { mutableStateOf(LocalTime.now()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Medication Reminder", style = MaterialTheme.typography.headlineMedium)

        // Formulario de agregar medicamento
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Medication Name") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = dosage,
            onValueChange = { dosage = it },
            label = { Text("Dosage") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (name.isNotBlank() && dosage.isNotBlank()) {
                    viewModel.addMedication(name, dosage, time)
                    name = ""
                    dosage = ""
                    time = LocalTime.now()
                }
            }
        ) {
            Text("Add Medication")
        }

        // Lista de medicamentos
        LazyColumn {
            items(viewModel.medications) { medication ->
                MedicationItem(medication, viewModel, context)
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MedicationItem2(medication: Medication, viewModel: MedicationController2, context: Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                // Al hacer clic en el Card, se redirige a la segunda actividad
                val intent = Intent(context, MainActivity2::class.java).apply {
                    putExtra("name", medication.name)
                    putExtra("dosage", medication.dosage)
                    putExtra("time", medication.time.format(DateTimeFormatter.ofPattern("HH:mm")))
                }
                context.startActivity(intent)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = medication.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Dosage: ${medication.dosage}", style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = "Time: ${medication.time.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(onClick = { viewModel.removeMedication(medication.id) }) {
                Icon(Icons.Default.Delete, contentDescription = "Remove Medication")
            }
        }
    }
}









//-------------------------------------------------------task



@Composable
fun TaskListScreen(controller: TaskController, context: Context) {
    var newTaskName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        for (task in controller.tasks) {
            TaskItem(task, controller, context)
        }

        TextField(
            modifier = Modifier.padding(5.dp).fillMaxWidth(),
            value = newTaskName,
            onValueChange = { newTaskName = it },
            label = { Text("Task Name") }
        )

        Button(onClick = {
            if (newTaskName.isNotEmpty()) {
                controller.addTask(newTaskName)
                newTaskName = ""
            }
        }) {
            Text("Add New Task")
        }
    }
}

fun openSecondActivity(context: Context, task: Task) {
    val intent = Intent(context, MainActivity2::class.java).apply {
        putExtra("taskId", task.id)
        putExtra("taskName", task.name)
    }
    context.startActivity(intent)
}

@Composable
fun TaskItem(task: Task, controller: TaskController, context: Context) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(text = task.name, modifier = Modifier.weight(1f))
        Button(onClick = {
            controller.removeTask(task.id)
        }) {
            Text("Remove")
        }
        Button(onClick = {
            openSecondActivity(context, task)  // Abre la segunda actividad con la tarea
        }) {
            Text("Open")
        }
    }
}


///--------------------------------------fr
@Composable
fun FruitListScreen(viewModel:FruitViewModel){
    Column (modifier = Modifier.padding(16.dp)){
        for(fruit in viewModel.fruits){
            Text(text = fruit.name)
        }

        Button(onClick = {viewModel.addFruit("Orange")}) {
            Text("ADD ORANGE")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val vi=FruitViewModel()
    val a=MedicationController2()
    MyApplicationTheme {
        //MedicationScreen2(a)
    }
}