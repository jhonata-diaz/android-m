package fruit

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class Fruit(val name:String)

class FruitViewModel : ViewModel(){

    private val _fruits= mutableStateListOf<Fruit>()
    val fruits:List<Fruit> get() = _fruits

    init {
        _fruits.addAll(listOf(Fruit("afsf"),Fruit("fasdf")))
    }

    fun addFruit(name:String){
        viewModelScope.launch {
            _fruits.add(Fruit(name))
        }
    }

}