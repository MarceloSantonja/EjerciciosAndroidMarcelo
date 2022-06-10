package com.marcelo.examen25112020

import android.os.Parcel
import android.os.Parcelable

class Alumno() : Parcelable {
    var nombre: String?
    var apellidos: String?
    var descripcion: String?
    var haFaltado: Boolean

    constructor(
        nombre: String,
        apellidos: String,
        descripcion: String,
        haFaltado: Boolean
    ) : this() {
        this.nombre = nombre
        this.apellidos = apellidos
        this.descripcion = descripcion
        this.haFaltado = haFaltado
    }

    init {
        nombre = ""
        apellidos = ""
        descripcion = ""
        haFaltado = false
    }

    override fun toString(): String {
        val sbAlumno: StringBuilder = java.lang.StringBuilder("$nombre $apellidos -->")
        if (haFaltado)
            sbAlumno.append("ha faltado")
        sbAlumno.append("\n$descripcion")
        return sbAlumno.toString()
    }

    constructor(parcel: Parcel) : this() {
        nombre = parcel.readString()
        apellidos = parcel.readString()
        descripcion = parcel.readString()
        haFaltado = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(apellidos)
        parcel.writeString(descripcion)
        parcel.writeByte(if (haFaltado) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Alumno> {
        override fun createFromParcel(parcel: Parcel): Alumno {
            return Alumno(parcel)
        }

        override fun newArray(size: Int): Array<Alumno?> {
            return arrayOfNulls(size)
        }
    }


}