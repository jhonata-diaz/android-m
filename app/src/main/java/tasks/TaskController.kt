package data

import androidx.compose.runtime.mutableStateListOf

class Task(val id:Int,val name:String)

class TaskController {

    private val _task = mutableStateListOf<Task>()

    val tasks : List<Task> get() =_task

    init {
        _task.addAll(listOf(Task(1,"ta1"),Task(2,"Ta2")))
    }

    fun addTask(name:String){
        val newId = if(_task.isEmpty()) 1 else _task.last().id+1
        _task.add(Task(newId,name))
    }

    fun removeTask(taskId:Int){
        _task.removeIf{it.id==taskId}
    }

}