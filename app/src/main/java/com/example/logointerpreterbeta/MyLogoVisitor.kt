package com.example.logointerpreterbeta

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.logointerpreterbeta.interpreter.logoBaseVisitor
import com.example.logointerpreterbeta.interpreter.logoParser
import kotlin.math.cos
import kotlin.math.sin

class MyLogoVisitor : logoBaseVisitor<Int>() {
    val image: Bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
    private val canvas = Canvas(image)
    private var paint = Paint()

    init {
        // Ustawienia malowania (kolor, grubość linii)
        paint.color = Color.BLACK
        paint.strokeWidth = 5f
    }

    override fun visitFd(ctx: logoParser.FdContext?): Int {
        val distance = ctx!!.expression().text.toInt()
        // Konwersja kąta na radiany
        val angleRadians = Math.toRadians((Turtle.direction - 90).toDouble())

        // Obliczenie końcowych współrzędnych linii
        val endX = (Turtle.Xposition + distance * cos(angleRadians)).toFloat()
        val endY = (Turtle.Yposition + distance * sin(angleRadians)).toFloat()
        if (Turtle.isDown) {
            canvas.drawLine(Turtle.Xposition, Turtle.Yposition,endX, endY, paint)
        }
        Turtle.setAcctualPosition(endX, endY)

        return 0
    }

    override fun visitBk(ctx: logoParser.BkContext?): Int {
        val distance = ctx!!.expression().text.toInt()
        // Konwersja kąta na radiany
        val angleRadians = Math.toRadians((Turtle.direction - 90 - 180).toDouble())

        // Obliczenie końcowych współrzędnych linii
        val endX = (Turtle.Xposition + distance * cos(angleRadians)).toFloat()
        val endY = (Turtle.Yposition + distance * sin(angleRadians)).toFloat()
        if (Turtle.isDown) {
            canvas.drawLine(Turtle.Xposition, Turtle.Yposition,endX, endY, paint)
        }
        Turtle.setAcctualPosition(endX, endY)

        return 0
    }

    override fun visitRt(ctx: logoParser.RtContext?): Int{
        val exp = ctx!!.expression()
        val angle = exp!!.text.toInt()
        Turtle.direction += angle
        return 0
    }

    override fun visitLt(ctx: logoParser.LtContext?): Int{
        val exp = ctx!!.expression()
        val angle = exp!!.text.toInt()
        Turtle.direction -= angle
        return 0
    }

    override fun visitCs(ctx: logoParser.CsContext?): Int {
        canvas.drawColor(Color.WHITE)
        return 0
    }

   override fun visitPu(ctx: logoParser.PuContext?): Int {
       Turtle.isDown = false
       return 0
   }

   override fun visitPd(ctx: logoParser.PdContext?): Int {
       Turtle.isDown = true
       return 0
    }
}