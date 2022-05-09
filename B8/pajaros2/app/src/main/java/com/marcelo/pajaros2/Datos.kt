package com.marcelo.pajaros2

import android.os.Parcel
import android.os.Parcelable

class Datos(var id: Int,var idImagen: Int,var nombre: String?, var descripcion: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        id =parcel.readInt(),
        idImagen = parcel.readInt(),
        nombre = parcel.readString(),
        descripcion = parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(idImagen)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
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
