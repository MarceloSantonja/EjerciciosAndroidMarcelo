package com.marcelo.examen2perros

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Base64
import java.io.ByteArrayOutputStream

class ImagenUtilidad {

    companion object {
        var TAKE_PICTURE = 1
        var SELECT_PICTURE = 2
        fun redimensionarImagenMaximo(mBitmap: Bitmap, newWidth: Float, newHeight: Float): Bitmap {
            val width = mBitmap.width
            val height = mBitmap.height
            val scaleWidth = newWidth / width
            val scaleHeight = newHeight / height
            val matrix = Matrix()
            matrix.postScale(scaleWidth, scaleHeight)
            return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false)
        }

        fun convertirStringBitmap(imagen: String?): Bitmap? {
            if (imagen != null) {
                val decodedString = Base64.decode(imagen, Base64.DEFAULT)
                //BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length,)
                return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            }
            return null
        }

        fun convertirRecursoBitmap(recurso: Int, context: Context): Bitmap {
            return BitmapFactory.decodeResource(context.resources, recurso)
        }

        fun ConvertirImagenString(bitmap: Bitmap?): String? {
            val stream = ByteArrayOutputStream()
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
                val byte_arr = stream.toByteArray()
                return Base64.encodeToString(byte_arr, Base64.DEFAULT)
            }
            return null
        }

        fun getBytesFromBitmap(bitmap: Bitmap?): ByteArray? {
            if (bitmap != null) {
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream)
                return stream.toByteArray()
            }
            return null
        }

        fun getFromBitmapBytes(imagenbyte: ByteArray?): Bitmap? {
            return if (imagenbyte != null) {
                BitmapFactory.decodeByteArray(imagenbyte, 0, imagenbyte.size)
            } else null
        }
    }
}