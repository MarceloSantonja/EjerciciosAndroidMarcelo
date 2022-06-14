package com.marcelo.examen1sqlite

class DatosSql(
                val id: Long,
                val pistauno: String,
                val pistados: String,
                val pistatres: String,
                val solucion: String,
                val foto: String,
                val tipo: Long,
                var pistaunoUsada:Boolean = false,
                var pistadosUsada:Boolean = false,
                var pistatresUsada:Boolean = false
) {
}