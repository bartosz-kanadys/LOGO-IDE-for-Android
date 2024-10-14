package com.example.logointerpreterbeta

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
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
        paint.style = Paint.Style.STROKE
    }

    override fun visitFd(ctx: logoParser.FdContext?): Int {
        val distance = visit(ctx!!.expression())
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
        val distance = visit(ctx!!.expression())
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

    override fun visitArc(ctx: logoParser.ArcContext?): Int {
        val angle = visit(ctx!!.expression(0))!!.toFloat()
        val distance = visit(ctx.expression(1))

        val rectF = RectF(
            Turtle.Xposition-distance,
            Turtle.Yposition-distance,
            Turtle.Xposition+distance,
            Turtle.Yposition+distance
        )

        canvas.drawArc(rectF,270f+Turtle.direction, angle,false,paint)
        return 0
    }

    override fun visitRt(ctx: logoParser.RtContext?): Int{
        val angle =visit(ctx!!.expression())
        Turtle.direction += angle
        return 0
    }

    override fun visitLt(ctx: logoParser.LtContext?): Int{
        val angle = visit(ctx!!.expression())
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

    override fun visitHome(ctx: logoParser.HomeContext?): Int {
        Turtle.setAcctualPosition(500F, 500F)
        return 0
    }

    override fun visitSetxy(ctx: logoParser.SetxyContext?): Int {
        val x = ctx!!.expression(0).text.toFloat()
        val y = ctx.expression(1).text.toFloat()
        Turtle.setAcctualPosition(x,y)
        return 0
    }

    override fun visitRepeat_(ctx: logoParser.Repeat_Context?): Int {
        val repeatCount = ctx!!.number().text.toInt()
        val commandsBlock = ctx.block().children

        for (i in 1..repeatCount) {
            for (command in commandsBlock) {
                visit(command)
            }
        }
        return 0
    }

    override fun visitSetpencolor(ctx: logoParser.SetpencolorContext?): Int {
        val intColor = visit(ctx!!.expression())
        val color = penColors[intColor]
        paint.setColor(color)
        Turtle.penColor = color
        return 0
    }

    override fun visitSetpensize(ctx: logoParser.SetpensizeContext?): Int {
        val size = visit(ctx!!.expression())
        paint.strokeWidth = size.toFloat()
        Turtle.penSize = size
        return 0
    }

    override fun visitSetscreencolor(ctx: logoParser.SetscreencolorContext?): Int {
        val intColor = visit(ctx!!.expression())
        val color = penColors[intColor]
        canvas.drawColor(color)
        return 0
    }

    override fun visitExpression(ctx: logoParser.ExpressionContext?): Int {
        return visitChildren(ctx) // Przekazuje do dzieci, żeby w pełni odwiedzić drzewo wyrażeń
    }

    override fun visitSignExpression(ctx: logoParser.SignExpressionContext?): Int {
        if (ctx!!.func_() != null) {
            return visit(ctx.func_())
        }

        if (ctx.number() != null) {
            return ctx.number().text.toInt() // Zwraca liczbę
        }

        if (ctx.deref() != null) {
            return visit(ctx.deref()) // Zwraca wartość zmiennej
        }

        return 0 // Domyślna wartość, gdy nic nie pasuje
    }

    override fun visitRandom(ctx: logoParser.RandomContext?): Int {
        val maxValue = visit(ctx!!.expression())!!
        return (0..maxValue-1).random()
    }
   
}