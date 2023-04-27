package com.example.myproyectofinal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(proyecto::class), version = 1, exportSchema = false)
abstract class ProyectoRoomDatabase : RoomDatabase() {

    abstract fun ProyectoDao(): ProyectoDao

    private class ProyectoDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var proyectoDao = database.ProyectoDao()

                    // Delete all content here.
                    proyectoDao.deleteAll()

                    // Add sample words.
                    var proyecto = proyecto("Hello")
                    proyectoDao.insert(proyecto)
                    proyecto = proyecto("World!")
                    proyectoDao.insert(proyecto)

                    // TODO: Add your own words!
                    proyecto = proyecto("TODO!")
                    proyectoDao.insert(proyecto)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ProyectoRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ProyectoRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProyectoRoomDatabase::class.java,
                    "word_database"
                )
                    .addCallback(ProyectoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}