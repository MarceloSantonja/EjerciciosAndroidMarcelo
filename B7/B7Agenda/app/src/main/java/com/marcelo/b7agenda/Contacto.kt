package com.marcelo.b7agenda

import android.graphics.Bitmap

class Contacto {
    var nombre: String
    var apellido: String
    var telefono: String? = null
    var email: String? = null
    var foto: Bitmap? = null
    var id = 0
    var familiar: Boolean? = null
    var amigos: Boolean? = null
    var trabajo: Boolean? = null

    constructor() {
        nombre = ""
        apellido = ""
        telefono = ""
        email = ""
        foto = null
        familiar = false
        amigos = false
        trabajo = false
    }

    constructor(
        nombre: String,
        apellido: String,
        telefono: String?,
        email: String?,
        foto: Bitmap?,
        f: Boolean?,
        a: Boolean?,
        t: Boolean?
    ) {
        this.nombre = nombre
        this.apellido = apellido
        this.telefono = telefono
        this.email = email
        this.foto = foto
        familiar = f
        amigos = a
        trabajo = t
    }

    constructor(nombre: String, apellido: String, id: Int) {
        this.id = id
        this.nombre = nombre
        this.apellido = apellido
    }

    constructor(
        id: Int,
        nombre: String,
        apellido: String,
        telefono: String?,
        email: String?,
        foto: Bitmap?
    ) {
        this.nombre = nombre
        this.apellido = apellido
        this.telefono = telefono
        this.email = email
        this.foto = foto
        this.id = id
    }


}
