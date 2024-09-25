package medic

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalTime

class MedicationController {

    private val _medications= MutableStateFlow<List<Medication>>(emptyList())

    val medications:StateFlow<List<Medication>> = _medications.asStateFlow()

    fun addMedication(name:String,dosage:String ,time:LocalTime){
        val newMedication = Medication(
            id = _medications.value.size +1,
            name=name,
            dosage=dosage,
            time=time
        )
        _medications.value = _medications.value+newMedication
    }


    fun removeMedication(id: Int) {
        _medications.value = _medications.value.filter { it.id != id }
    }

}