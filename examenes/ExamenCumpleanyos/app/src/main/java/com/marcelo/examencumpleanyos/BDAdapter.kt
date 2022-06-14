package com.marcelo.examencumpleanyos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDAdapter(context: Context) {

    private var cumpleanyos: BDCumpleanyos = BDCumpleanyos(context, "BDCumpleanyos", null, 1)

    fun insertarDatos(cumple: Cumple) {
        var dbCumpleanyos = cumpleanyos.writableDatabase
        if (dbCumpleanyos != null) {
            val valores = ContentValues()
            valores.put("nombreNinyo", cumple.nombre)
            valores.put("edad", cumple.edad)
            valores.put("nombrePadre", cumple.contacto)
            valores.put("telefono", cumple.telefono)
            valores.put("email", cumple.eMail)
            valores.put("fecha", cumple.fecha)
            valores.put("iniciado", 0)

            dbCumpleanyos.insert("cumpleanyos", null, valores)
            dbCumpleanyos.close()
        }
    }

    fun cursordatos(): Cursor? {
        return cumpleanyos.readableDatabase?.rawQuery("SELECT * FROM cumpleanyos", null)
    }
    fun valorIniciado(id: Int,nuevoValor:Int){

        var dbCumpleanyos = cumpleanyos.writableDatabase
        if (dbCumpleanyos != null) {
            val valores = ContentValues()
            valores.put("iniciado",nuevoValor)
            dbCumpleanyos.update("cumpleanyos",valores,"ID=$id",null)
            dbCumpleanyos.close()
        }
    }
    fun buscarCumplesIniciados(): ArrayList<Cumple>?{
        var dbCumpleanyos = cumpleanyos.writableDatabase
        if (dbCumpleanyos != null) {
            var cursor = cumpleanyos.readableDatabase?.rawQuery("SELECT * FROM cumpleanyos WHERE iniciado LIKE '1'", null)
            if (cursor != null)
                return getCumples(cursor)
        }
        return null
    }
    fun getCumples(cursor: Cursor): ArrayList<Cumple>? {
        var cumples: ArrayList<Cumple>
        var cumple: Cumple
        cursor.moveToFirst()
        if (!cursor.isAfterLast()) {
            cumples = ArrayList()
            while (!cursor.isAfterLast()) {
                cumple =
                    Cumple(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6))

                            cumples.add(cumple)
                            cursor.moveToNext()
            }
            return cumples
        }
        return null
    }

    fun borrar(id:Int){
        var dbCumpleanyos = cumpleanyos.writableDatabase
        if (dbCumpleanyos != null) {
            dbCumpleanyos.delete("cumpleanyos","ID=$id",null)
            dbCumpleanyos.close()
        }


    }



    internal inner class BDCumpleanyos(
        context: Context,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
    ) : SQLiteOpenHelper(context, name, factory, version) {

        override fun onCreate(sqLiteDatabase: SQLiteDatabase) {

            var sentencia =
                "create table if not exists cumpleanyos" +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombreNinyo TEXT," +
                        " edad INTEGER," +
                        " nombrePadre TEXT," +
                        " telefono INTEGER," +
                        " email TEXT," +
                        " fecha TEXT," +
                        " iniciado INTEGER);"

            sqLiteDatabase.execSQL(sentencia)
        }


        override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i2: Int) {}
    }


}