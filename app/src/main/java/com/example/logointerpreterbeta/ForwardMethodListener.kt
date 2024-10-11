package com.example.logointerpreterbeta

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.logointerpreterbeta.interpreter.logoBaseListener
import com.example.logointerpreterbeta.interpreter.logoParser
import kotlin.math.cos
import kotlin.math.sin

class ForwardMethodListener() : logoBaseListener() {
    var log = ""
    val image: Bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(image)
    var paint = Paint()

    override fun enterProg(ctx: logoParser.ProgContext?) {
       //canvas.drawColor(Color.WHITE)
       // paint.setStrokeWidth(4f)
    }

    override fun enterFd(ctx: logoParser.FdContext?) {
        val exp = ctx!!.expression()
        val text = exp!!.text
        val parameter = text!!.toInt()

        // Konwersja kąta na radiany
        val angleRadians = Math.toRadians((Turtle.direction - 90).toDouble())

        // Obliczenie końcowych współrzędnych linii
        val endX = (Turtle.Xposition + parameter * cos(angleRadians)).toFloat()
        val endY = (Turtle.Yposition + parameter * sin(angleRadians)).toFloat()
        if (Turtle.isDown) {
//            g2d.setStroke(BasicStroke(2))
//            g2d.setColor(Color.BLUE)
            canvas.drawLine(Turtle.Xposition, Turtle.Yposition,endX, endY, paint)
           // g2d.drawLine(Turtle.Xposition, Turtle.Yposition, endX, endY)
        }
        Turtle.setAcctualPosition(endX, endY)

//        g2d.dispose();
//        image.createGraphics();
    }

    override fun enterBk(ctx: logoParser.BkContext?) {
        val exp = ctx!!.expression()
        val text = exp!!.text
        val parameter = text!!.toInt()

        // Konwersja kąta na radiany
        val angleRadians = Math.toRadians((Turtle.direction - 90 - 180).toDouble())
        println(angleRadians)

        // Obliczenie końcowych współrzędnych linii
        val endX = (Turtle.Xposition + parameter * cos(angleRadians)).toFloat()
        val endY = (Turtle.Yposition + parameter * sin(angleRadians)).toFloat()
       // g2d.drawLine(Turtle.Xposition, Turtle.Yposition, endX, endY)
        Turtle.setAcctualPosition(endX, endY)
    }

    override fun enterRt(ctx: logoParser.RtContext?) {
        val exp = ctx!!.expression()
        val text = exp!!.text
        val parameter = text!!.toInt()
        Turtle.direction += parameter
    }

    override fun enterLt(ctx: logoParser.LtContext?) {
        val exp = ctx!!.expression()
        val text = exp!!.text
        val parameter = text!!.toInt()
        Turtle.direction -= parameter
    }

    override fun enterCs(ctx: logoParser.CsContext?) {
        canvas.drawColor(Color.WHITE)
    }
//
//    override fun enterPu(ctx: PuContext?) {
//        Turtle.isDown = false
//    }
//
//    override fun enterPd(ctx: PdContext?) {
//        Turtle.isDown = true
//    }
//
//    override fun enterHt(ctx: HtContext?) {
//        super.enterHt(ctx)
//    }
//
//    override fun enterSt(ctx: StContext?) {
//        super.enterSt(ctx)
//    }

    override fun exitProg(ctx: logoParser.ProgContext?) {
//
//            val fileOutputStream = FileOutputStream("img.png")
//            image.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
//            fileOutputStream.close()


    }
}
