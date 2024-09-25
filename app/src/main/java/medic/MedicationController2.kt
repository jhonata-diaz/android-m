package medic

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.time.LocalTime

class MedicationController2 :ViewModel(){

    private val medicationList = mutableStateListOf<Medication>()

    val medications:List<Medication> get() =medicationList

    fun addMedication(name:String,dosage:String,time:LocalTime){
        val newMedication=Medication(
            id=medicationList.size+1,
            name=name,
            dosage=dosage,
            time=time
        )
        medicationList.add(newMedication)
    }

    fun removeMedication(id:Int){
        medicationList.removeAll{it.id==id}
    }
}