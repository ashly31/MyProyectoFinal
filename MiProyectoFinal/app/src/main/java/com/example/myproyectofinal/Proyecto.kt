package com.example.myproyectofinal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proyecto_table")
class proyecto(@PrimaryKey @ColumnInfo(name = "proyecto") val proyecto: String)