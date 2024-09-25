package practicesone

import android.text.Layout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

class uno {

    val fruits = listOf("Aple","banana")

    fun aver(){
        println(fruits[0])

        for(fruit in fruits){
            println(fruit)
        }
    }

}

class dos(){
    val mutableFruit= mutableListOf("Apple","Banna")

    fun f1(){
        mutableFruit.add("orane")

        for (fruit in mutableFruit){
            println(fruit)
        }
        mutableFruit.remove("Banna")
        println(mutableFruit)
    }



}
class Product(val name:String,val price:Double){



    fun uno(products:List<Product>){
        for (product in products){
            println("${product.name } price ${product.price}")
        }
    }

}

class SobreCarCons {
    var name: String = ""

    // Constructor secundario que asigna el nombre
    constructor(name: String) : this() {
        this.name = name
    }

    // Constructor por defecto
    constructor() {
        // Puedes inicializar 'name' aquí si es necesario
        println("Constructor por defecto llamado")
    }


}

@Composable
fun MutableFruitList() {
    // Estado para mantener la lista de frutas
    val fruits = remember { mutableStateListOf("Apple", "Banana") }

    Column {
        // Mostrar los elementos
        for (fruit in fruits) {
            Text(text = fruit)
        }

        // Botón para agregar una nueva fruta
        Button(onClick = { fruits.add("Orange") }) {
            Text("Add Orange")
        }

        // Botón para eliminar el último elemento
        Button(onClick = {
            if (fruits.isNotEmpty()) fruits.removeAt(fruits.size - 1)
        }) {
            Text("Remove Last Fruit")
        }
    }
}

@Preview
@Composable
fun PreviewMutableFruitList() {
    MutableFruitList()
}


@Composable
fun FruitListLazy(fruits : List<String>){
    LazyColumn {
        items(fruits){  fruit->
            Text(text = fruit)
        }
    }
}

@Preview
@Composable
fun PrewFruit(){
    val fruits= listOf("A","b","c")
    FruitListLazy(fruits)
}


fun main(){
    val a=uno()
   // a.aver()

    val doss=dos()
    //doss.f1()

    val product = listOf(
        Product("asf",100.0),
        Product("asf",100.0),
        Product("asf",100.0),

        )
    val pr=Product("",23.4)
    //pr.uno(product)
    val sobre = SobreCarCons()
    //sobre.setName("adfsa")
    sobre.name = "adfsa"
    println(sobre.name)

    // Usando el constructor que asigna el nombre
    val sobre2 = SobreCarCons("Joh")
    println(sobre2.name)



}