package com.marcelo.examen2perros

import Animal
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteAdapter(context: Context?) {
    private var animales: BDAnimales

    init {
        animales = BDAnimales(context, "BDAnimales", null, 1)
    }


    fun insertarDatosInicio(listaAnimales: ArrayList<Animal>) {
        var dbClientes = animales.writableDatabase
        if (dbClientes != null) {
            for (animal in listaAnimales) {
                val sentencia = "INSERT INTO Animales (  foto , tipo ) VALUES" +
                        " ( '${animal.foto}' , ${animal.tipo});"
                dbClientes.execSQL(sentencia)
            }
            dbClientes.close()
        }
    }


    fun leerDatosCodigo(
        where: String?,
        columnas: Array<String?>? = null,
        valores: Array<String?>? = null,
        orderBy: String? = null
    ): Cursor? {
        val dbAnimales = animales.readableDatabase
        if (dbAnimales != null) {
            val cursor: Cursor =
                dbAnimales.query("Animales", columnas, where, valores, null, null, orderBy)
            return cursor
        }
        return null
    }

    fun getAnimalById(
        id: Int
    ): Animal? {
        val dbAnimales = animales.readableDatabase
        val animal = Animal()
        if (dbAnimales != null) {
            val cursor: Cursor =
                dbAnimales.query("Animales", null, "_id=$id", null, null, null, null)

            cursor.moveToFirst()
            if (!cursor.isAfterLast()) {

                animal._id= cursor.getInt(0)
                animal.nombre= cursor.getString(1)
                animal.edad= cursor.getInt(2)
                animal.raza= cursor.getString(3)
                animal.descripcion= cursor.getString(4)
                animal.foto= cursor.getString(5)
                animal.tipo= cursor.getInt(6)
                animal.seleccionado= cursor.getInt(7)
            }

        }
        return animal
    }
    fun updateAnimal(
        animal: Animal
    ) {
        val dbAnimales = animales.writableDatabase
        if (dbAnimales != null) {
            val valores = ContentValues()
               valores.put("nombre", animal.nombre)
               valores.put("edad", animal.edad)
               valores.put("raza", animal.raza)
               valores.put("descripcion", animal.descripcion)
               valores.put("foto", animal.foto)
               valores.put("tipo", animal.tipo)
               valores.put("seleccionado", animal.seleccionado)
            dbAnimales.update("Animales",valores,"_id=${animal._id}",null)

        }
    }


    internal inner class BDAnimales(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
    ) : SQLiteOpenHelper(context, name, factory, version) {
        var sentencia =
            "CREATE TABLE Animales (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, edad INTEGER, raza TEXT, descripcion TEXT, foto BLOB, tipo INTEGER, seleccionado INTEGER);"

        override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
            sqLiteDatabase.execSQL(sentencia)
        }

        override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i2: Int) {}
    }
}