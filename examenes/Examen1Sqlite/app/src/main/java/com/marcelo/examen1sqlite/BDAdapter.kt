package com.marcelo.examen1sqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDAdapter(context: Context?) {


    private val datos: BDDatos = BDDatos(context, "BDdatos", null, 1)


    fun anyadirDatos(datosArray: ArrayList<DatosSql>) {

        var dbDatos = datos.writableDatabase
        if (dbDatos != null) {
            for (dato in datosArray) {
                dbDatos.execSQL("INSERT INTO datos(id, pistauno ,pistados ,pistatres ,solucion ,foto,tipo) VALUES(${dato.id}, '${dato.pistauno}' ,'${dato.pistados}' ,'${dato.pistatres}' ,'${dato.solucion}' ,'${dato.foto}',${dato.tipo})")
            }
            dbDatos.close()
        }
    }

    fun cursorDatos(
        columnas: Array<String?>?,
        where: String?,
        valores: Array<String?>?,
        orderBy: String?): Cursor?
    {
        var listaDatos: ArrayList<DatosSql> = ArrayList()
        val dbDatosSql = datos.readableDatabase
        val cursor:Cursor
        if (dbDatosSql != null) {
            val cursor: Cursor =
                dbDatosSql.query("datos", columnas, where, valores, null, null,orderBy)
            return cursor
        }
        return null
    }

    fun seleccionarDatosCodigo(
        columnas: Array<String?>?,
        where: String?,
        valores: Array<String?>?,
        orderBy: String?): ArrayList<DatosSql>
    {
        var listaDatos: ArrayList<DatosSql> = ArrayList()
        val dbDatosSql = datos.readableDatabase
        if (dbDatosSql != null) {
            val cursor: Cursor =
                dbDatosSql.query("datos", columnas, where, valores, null, null,orderBy)
                    listaDatos = getDatos(cursor)
                            dbDatosSql.close()

        }

        return listaDatos

    }






    fun getDatos(cursor: Cursor):ArrayList<DatosSql>{
        var datosArray = ArrayList<DatosSql>()
        var dato : DatosSql
        cursor.moveToFirst()
        while (!cursor.isAfterLast()){
            dato= DatosSql(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getLong(6))
            datosArray.add(dato)
            cursor.moveToNext()
        }
        return datosArray

    }


    internal inner class BDDatos(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
    ) : SQLiteOpenHelper(context, name, factory, version) {
        var sentencia =
            "create table if not exists datos" +
                    "(id INTEGER PRIMARY KEY NOT NULL, pistauno TEXT,pistados TEXT,pistatres TEXT,solucion TEXT,foto TEXT,tipo INTEGER);"

        override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
            sqLiteDatabase.execSQL(sentencia)
        }

        override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i2: Int) {}
    }
}