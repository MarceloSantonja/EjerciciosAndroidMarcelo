
import android.graphics.Bitmap
import com.marcelo.examen2perros.ImagenUtilidad

class Animal() {

        var _id:Int=0
        var nombre:String?=null
        var edad:Int=0
        var raza:String?=null
        var descripcion:String?=null
        var usuario:String?=null
        var tipo:Int=0
        var foto: String?=null
        var seleccionado:Int=0

    constructor(foto:Bitmap):this()
    {
       this.foto= ImagenUtilidad.ConvertirImagenString(foto)
    }

    constructor(animal:Animal):this() {
        this.nombre=animal.nombre
        this.edad=animal.edad
        this.descripcion=animal.descripcion
        this.raza=animal.raza
        this.foto = animal.foto
        this.tipo = animal.tipo
        this._id=animal._id
        this.seleccionado=animal.seleccionado
    }

    constructor(
        nombre: String,
        edad: Int,
        raza:String,
        descripcion: String,
        foto: String,
        tipo: Int
    ):this() {
        this.nombre=nombre
        this.edad=edad
        this.raza=raza
        this.descripcion=descripcion
        this.foto = foto
        this.tipo = tipo
    }
    constructor(
        nombre: String,
        edad: Int,
        raza:String,
        descripcion: String,
        foto: Bitmap?,
        tipo: Int, seleccionado:Int
    ) :this(){
        this.nombre=nombre
        this.edad=edad
        this.raza=raza
        this.descripcion=descripcion
        this.foto = ImagenUtilidad.ConvertirImagenString(foto)
        this.tipo = tipo
        this.seleccionado=seleccionado
    }
    constructor(nombre: String):this()
    {
        this.nombre=nombre

    }

}
