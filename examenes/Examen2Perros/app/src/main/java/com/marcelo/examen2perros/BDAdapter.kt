package com.marcelo.examen2perros

import Animal
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDAdapter(context: Context?)
{
    private var animales: BDAnimales
    init {
        animales = BDAnimales(context, "BDAnimales", null, 1)
    }

    fun insertarDatos(listaAnimales: ArrayList<Animal>) {
        var dbClientes =animales.writableDatabase
        if (dbClientes != null) {
            for (animal in listaAnimales) {
                val sentencia = "INSERT INTO Animales ( nombre , edad , raza , descripcion , foto , tipo , seleccionado) VALUES"+
                " ( '${animal.nombre}', ${animal.edad}, '${animal.raza}' , '${animal.descripcion}' , '${animal.foto}' , ${animal.tipo} , ${animal.seleccionado});"
                dbClientes.execSQL(sentencia)
            }
            dbClientes.close()
        }
    }
    fun insertarDatosInicio(listaAnimales: ArrayList<Animal>) {
        var dbClientes =animales.writableDatabase
        if (dbClientes != null) {
            for (animal in listaAnimales) {
                val sentencia = "INSERT INTO Animales (  foto , tipo ) VALUES"+
                        " ( '${animal.foto}' , ${animal.tipo});"
                    dbClientes.execSQL(sentencia)
            }
            dbClientes.close()
        }
    }



    internal inner class BDAnimales(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int) : SQLiteOpenHelper(context, name, factory, version)
    {
        var sentencia =
            "CREATE TABLE Animales (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, edad INTEGER, raza TEXT, descripcion TEXT, foto BLOB, tipo INTEGER, seleccionado INTEGER);"
        override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
            sqLiteDatabase.execSQL(sentencia)
        }
        override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i2: Int){}
    }
}