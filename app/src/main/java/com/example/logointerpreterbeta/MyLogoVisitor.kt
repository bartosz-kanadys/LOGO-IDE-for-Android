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

    //mapa na zmienne
    private val variables: MutableMap<String, Any> = HashMap()

    init {
        // Ustawienia malowania (kolor, grubość linii)
        paint.color = Color.BLACK
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        paint.textSize = 50f
        paint.isAntiAlias = true
    }

    override fun visitFd(ctx: logoParser.FdContext?): Int {
        val distance = visit(ctx!!.expression())
        // Konwersja kąta na radiany
        val angleRadians = Math.toRadians((Turtle.direction - 90).toDouble())

        // Obliczenie końcowych współrzędnych linii do narysowania z poprawką na wypełnianie rogów
        val endXtoDraw = (Turtle.Xposition + (distance+paint.strokeWidth/2) * cos(angleRadians)).toFloat()
        val endYtoDraw = (Turtle.Yposition + (distance+paint.strokeWidth/2) * sin(angleRadians)).toFloat()
        // obliczanie koncowych pozycji dla zolwia
        val endXTurtlePosition = (Turtle.Xposition + distance * cos(angleRadians)).toFloat()
        val endYTurtlePosition = (Turtle.Yposition + distance * sin(angleRadians)).toFloat()
        if (Turtle.isDown) {
            canvas.drawLine(Turtle.Xposition, Turtle.Yposition,endXtoDraw, endYtoDraw, paint)
        }
        Turtle.setAcctualPosition(endXTurtlePosition, endYTurtlePosition)

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
        //liczba po lewej
        var result = visit(ctx!!.multiplyingExpression(0))

        // Jeśli jest więcej operatorów + lub -, iteruj przez nie
        for (i in 1 until ctx.multiplyingExpression().size) {
            val right = visit(ctx.multiplyingExpression(i))

            if (ctx.getChild(2 * i - 1).text == "+") {
                result += right
            } else if (ctx.getChild(2 * i - 1).text == "-") {
                result -= right
            }
        }

        return result
    }

    override fun visitMultiplyingExpression(ctx: logoParser.MultiplyingExpressionContext?): Int {
        var result = visit(ctx!!.signExpression(0))

        // Jeśli jest więcej operatorów * lub /, iteruj przez nie
        for (i in 1 until ctx.signExpression().size) {
            val right = visit(ctx.signExpression(i))

            if (ctx.getChild(2 * i - 1).text == "*") {
                result *= right
            } else if (ctx.getChild(2 * i - 1).text == "/") {
                result /= right
            }
        }

        return result
    }


    override fun visitSignExpression(ctx: logoParser.SignExpressionContext?): Int {
        // Sprawdź, czy jest operator - lub + (np. -5)
        var sign = 1
        for (operator in ctx!!.children) {
            if (operator.text == "-") {
                sign *= -1
            }
        }

        // Jeśli wyrażenie jest liczbą
        if (ctx.number() != null) {
            return sign * ctx.number().text.toInt()
        }

        // Jeśli wyrażenie jest funkcją (np. random)
        if (ctx.func_() != null) {
            return sign * visit(ctx.func_())
        }

        // Jeśli jest to zmienna
        if (ctx.deref() != null) {
            return sign * visit(ctx.deref())
        }

        return 0
    }

    override fun visitRandom(ctx: logoParser.RandomContext?): Int {
        val maxValue = visit(ctx!!.expression())!!
        return (0..maxValue-1).random()
    }

    override fun visitMake(ctx: logoParser.MakeContext?): Int {
        val variableName = ctx!!.STRINGLITERAL().text.substring(1)
        val value = visit(ctx.value())

        variables[variableName] = value

        return 0
    }

    override fun visitDeref(ctx: logoParser.DerefContext?): Int {
        val variableName = ctx!!.name().text

        val value = variables[variableName]

        if (value is Int) {
            return value
        }

        // Możesz dodać tutaj obsługę innych typów, np. stringów
        throw RuntimeException("Nieprawidłowa zmienna lub typ: $variableName")
    }

    override fun visitLabel(ctx: logoParser.LabelContext?): Int {
        val text = ctx!!.quotedstring().text
        val labelText = if (text.startsWith("[")) {
            text.substring(1, text.length - 1)
        } else {
            text.substring(1, text.length)
        }

        canvas.drawText(labelText, Turtle.Xposition, Turtle.Yposition, paint)

        return 0
    }

    override fun visitSettextsize(ctx: logoParser.SettextsizeContext?): Int {
        val size = ctx!!.expression().text.toFloat()
        paint.textSize = size
        return 0
    }



}