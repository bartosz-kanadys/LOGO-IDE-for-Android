package com.example.logointerpreterbeta

import android.graphics.Bitmap

class ImageBitmap {
    var image: Bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
        get() = image
}