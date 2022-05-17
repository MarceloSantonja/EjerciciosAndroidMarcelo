package com.marcelo.ejercicioresueltobd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDAdapter(context: Context?) {

    private var clientes: BDClientes

    init{
        clientes = BDClientes(context, "BDCLientes", null,1)
    }

    fun insertarDatos() {

        var dbClientes = clientes.writableDatabase

        if (dbClientes != null){
            for (i in 0..9){
                val sentencia = "INSERT INTO Clientes (dni, nombre , apellidos) VALUEs "+
                "('$i','nombre$i','apellidos$i');"

                dbClientes.execSQL(sentencia)
            }
            dbClientes.close()
        }
    }

    fun updateContentValues(nombre:String,dni:String, apellidos: String, where:String){
        val dbClientes = clientes.writableDatabase
        val valores = ContentValues()
        valores.put("nombre","$nombre")
        valores.put("dni","$dni")
        valores.put("apellidos","$apellidos")
        dbClientes.update("clientes",valores,"$where",null)
        dbClientes.close()
    }

    fun insertContentValues(cliente:Cliente){
        val dbClientes = clientes.writableDatabase
        if(dbClientes!= null){
            val valores = ContentValues()
            valores.put("nombre",cliente.nombre)
            valores.put("dni",cliente.dni)
            valores.put("apellidos",cliente.apellidos)
            dbClientes.insert("clientes",null,valores)
            dbClientes.close()
        }
    }
    fun BorrandoContentValues(){
        val dbClientes = clientes.writableDatabase
        val arg = arrayOf("1")
        dbClientes.delete("clientes","dni=?",arg)
        dbClientes.close()
    }
    fun UpdateConArgumentos(){
        val dbClientes = clientes.writableDatabase
        val valores = ContentValues()
        valores.put("nombre","Carla")
        val arg =arrayOf("6","7")
        dbClientes.update("clientes",valores,"dni=? OR dni=?",arg)
        dbClientes.close()

    }
    fun seleccionarDatosSelect(sentencia: String?): Boolean {
        val listaCliente: ArrayList<Cliente>?
        val dbClientes = clientes.readableDatabase
        if (dbClientes != null) {
            val cursor: Cursor = dbClientes.rawQuery(sentencia, null)
            listaCliente = getClientes(cursor)
            dbClientes.close()
            return if (listaCliente == null) false else true
        }
        return false
    }
    fun getClientes(cursor: Cursor): ArrayList<Cliente>? {
        val clientes: ArrayList<Cliente>
        var cliente: Cliente
        cursor.moveToFirst()
        if (!cursor.isAfterLast()) {
            clientes = ArrayList()
            while (!cursor.isAfterLast()) {
                cliente =
                    Cliente(cursor.getString(0), cursor.getString(1), cursor.getString(2))
                            clientes.add(cliente)
                            cursor.moveToNext()
            }
            return clientes
        }
        return null
    }
    fun seleccionarDatosCodigo(
        columnas: Array<String?>?,
        where: String?,
        valores: Array<String?>?,
        orderBy: String?): ArrayList<Cliente>?
    {
        var listaCliente: ArrayList<Cliente>? = ArrayList()
        val dbClientes = clientes.readableDatabase
        if (dbClientes != null) {
            val cursor: Cursor =
                dbClientes.query("clientes", columnas, where, valores, null, null,orderBy)
                    listaCliente = getClientes(cursor)
                            dbClientes.close()
                    return listaCliente
        }
        return null
    }

    internal inner class BDClientes(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version:Int): SQLiteOpenHelper(context,name,factory,version){
        var sentencia = "create table if not exists Clientes"+
                "(dni VARCHAR PRIMARY KEY NOT NULL, nombre TEXT, apellidos TEXT);"

        override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
            sqLiteDatabase?.execSQL(sentencia)
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            TODO("Not yet implemented")
        }


    }


}