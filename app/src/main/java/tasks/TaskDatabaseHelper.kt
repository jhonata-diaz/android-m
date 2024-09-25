package tasks


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Definición de la base de datos
private const val DATABASE_NAME = "tasks.db"
private const val DATABASE_VERSION = 1

class TaskDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Definición de la tabla y columnas
    companion object {
        const val TABLE_TASKS = "tasks"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
    }

    // SQL para crear la tabla
    private val SQL_CREATE_TABLE = """
        CREATE TABLE $TABLE_TASKS (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAME TEXT NOT NULL
        )
    """

    // SQL para borrar la tabla (para actualizar la base de datos)
    private val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_TASKS"

    override fun onCreate(db: SQLiteDatabase) {
        // Crear la tabla en la base de datos
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Actualizar la base de datos si es necesario
        db.execSQL(SQL_DROP_TABLE)
        onCreate(db)
    }
}
