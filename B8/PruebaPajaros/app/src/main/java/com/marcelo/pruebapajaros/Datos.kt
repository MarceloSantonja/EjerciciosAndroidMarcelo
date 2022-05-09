package com.marcelo.pruebapajaros

import android.os.Parcel
import android.os.Parcelable

class Datos(val posicion: Int, val idImagen: Int, val nombre: String, val descripcion: String) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        posicion = parcel.readInt(),
        idImagen = parcel.readInt(),
        nombre = parcel.readString()!!,
        descripcion = parcel.readString()!!
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(posicion)
        parcel.writeInt(idImagen)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
    }

    companion object CREATOR : Parcelable.Creator<Datos> {
        override fun createFromParcel(parcel: Parcel): Datos {
            return Datos(parcel)
        }

        override fun newArray(size: Int): Array<Datos?> {
            return arrayOfNulls(size)
        }
    }
}