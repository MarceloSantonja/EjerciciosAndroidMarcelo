package com.marcelo.examencumpleanyos

import android.os.Parcel
import android.os.Parcelable
import org.w3c.dom.NamedNodeMap

class Cumple()  {
    var id: Int
    var nombre: String
    var edad: Int
    var contacto: String
    var telefono: Int
    var eMail: String
    var fecha: String

    constructor(id: Int,
        nombre: String,
        edad: Int,
        contacto: String,
        telefono: Int,
        eMail: String,
        fecha: String
    ) : this() {
        this.id = id
        this.nombre = nombre
        this.edad = edad
        this.contacto = contacto
        this.telefono = telefono
        this.eMail = eMail
        this.fecha = fecha
    }
    constructor(
                nombre: String,
                edad: Int,
                contacto: String,
                telefono: Int,
                eMail: String,
                fecha: String
    ) : this() {

        this.nombre = nombre
        this.edad = edad
        this.contacto = contacto
        this.telefono = telefono
        this.eMail = eMail
        this.fecha = fecha
    }

    init {
        id=0
        nombre = ""
        edad = 0
        contacto = ""
        telefono = 0
        eMail = ""
        fecha = ""
    }





}