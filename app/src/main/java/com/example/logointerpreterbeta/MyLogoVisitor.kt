package com.example.logointerpreterbeta

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import com.example.logointerpreterbeta.interpreter.logoBaseVisitor
import com.example.logointerpreterbeta.interpreter.logoParser
import kotlin.math.cos
import kotlin.math.sin

class MyLogoVisitor : logoBaseVisitor<Any>() {
    val image: Bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
    private val canvas = Canvas(image)
    private var paint = Paint()

    private var variables: MutableMap<String, Any> = HashMap()
    private var procedures: MutableMap<String, logoParser.ProcedureDeclarationContext> = HashMap()

    init {
        // Ustawienia malowania (kolor, grubość linii)
        paint.color = Color.BLACK
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        paint.textSize = 50f
        paint.isAntiAlias = true
    }

    override fun visitFd(ctx: logoParser.FdContext?): Int {
        val distance = visit(ctx!!.expression()).toString().toFloat()
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
        val distance = visit(ctx!!.expression()).toString().toFloat()
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
        val angle = visit(ctx!!.expression(0))!!.toString().toFloat()
        val distance = visit(ctx.expression(1)).toString().toFloat()

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
        val angle =visit(ctx!!.expression()).toString().toFloat()
        Turtle.direction += angle
        return 0
    }

    override fun visitLt(ctx: logoParser.LtContext?): Int{
        val angle = visit(ctx!!.expression()).toString().toFloat()
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
        var x = ctx!!.expression(0).text.toFloat()
        var y = ctx.expression(1).text.toFloat()
        y = if (y<0) 500-y else 500+y
        x = if (x<0) 500+x else 500-x
        Turtle.setAcctualPosition(x,y)
        return 0
    }

    override fun visitRepeat_(ctx: logoParser.Repeat_Context?): Int {
        val repeatCount = ctx!!.number().text.toFloat().toInt()
        val commandsBlock = ctx.block().children
        try {
            for (i in 1..repeatCount) {
                for (command in commandsBlock) {
                    visit(command)
                }
            }
        }catch (e: StopException) {return 0}

        return 0
    }

    override fun visitSetpencolor(ctx: logoParser.SetpencolorContext?): Int {
        val intColor = visit(ctx!!.expression()).toString().toFloat().toInt()
        val color = penColors[intColor]
        paint.setColor(color)
        Turtle.penColor = color
        return 0
    }

    override fun visitSetpensize(ctx: logoParser.SetpensizeContext?): Int {
        val size = visit(ctx!!.expression()).toString().toFloat().toInt()
        paint.strokeWidth = size.toFloat()
        Turtle.penSize = size
        return 0
    }

    override fun visitSetbg(ctx: logoParser.SetbgContext?): Int {
        val intColor = visit(ctx!!.expression()).toString().toFloat().toInt()
        val color = penColors[intColor]
        canvas.drawColor(color)
        return 0
    }

    override fun visitExpression(ctx: logoParser.ExpressionContext?): Float {
        //liczba po lewej
        var result = visit(ctx!!.multiplyingExpression(0)).toString().toFloat()

        // Jeśli jest więcej operatorów + lub -, iteruj przez nie
        for (i in 1 until ctx.multiplyingExpression().size) {
            val right = visit(ctx.multiplyingExpression(i)).toString().toFloat()

            if (ctx.getChild(2 * i - 1).text == "+") {
                result += right
            } else if (ctx.getChild(2 * i - 1).text == "-") {
                result -= right
            }
        }

        return result
    }

    override fun visitMultiplyingExpression(ctx: logoParser.MultiplyingExpressionContext?): Float {
        var result = visit(ctx!!.signExpression(0)).toString().toFloat()

        // Jeśli jest więcej operatorów * lub /, iteruj przez nie
        for (i in 1 until ctx.signExpression().size) {
            val right = visit(ctx.signExpression(i)).toString().toFloat()

            if (ctx.getChild(2 * i - 1).text == "*") {
                result *= right
            } else if (ctx.getChild(2 * i - 1).text == "/") {
                result /= right
            }
        }

        return result
    }


    override fun visitSignExpression(ctx: logoParser.SignExpressionContext?): Float {
        // Sprawdź, czy jest operator - lub + (np. -5)
        var sign = 1
        for (operator in ctx!!.children) {
            if (operator.text == "-") {
                sign *= -1
            }
        }

        // Jeśli wyrażenie jest liczbą
        if (ctx.number() != null) {
            return sign * ctx.number().text.toFloat()
        }

        // Jeśli wyrażenie jest funkcją (np. random)
        if (ctx.func_() != null) {
            return sign * visit(ctx.func_()).toString().toFloat()
        }

        // Jeśli jest to zmienna
        if (ctx.deref() != null) {
            return sign * visit(ctx.deref()).toString().toFloat()
        }
        return 0f
    }

    override fun visitNumber(ctx: logoParser.NumberContext?): Float{
        return if (ctx!!.FLOAT() != null) {
            ctx.FLOAT().text.toFloat()
        } else {
            ctx.NUMBER().text.toFloat()
        }
    }

    override fun visitRandom(ctx: logoParser.RandomContext?): Int{
        val maxValue = visit(ctx!!.expression())!!.toString().toFloat().toInt()
        return (0..maxValue-1).random()
    }

    override fun visitMake(ctx: logoParser.MakeContext?): Int {
        val variableName = ctx!!.STRINGLITERAL().text.toString().substring(1)
        var value = visit(ctx.value())

        variables[variableName] = value

        return 0
    }

    override fun visitValue(ctx: logoParser.ValueContext?): Any {
        if (ctx!!.STRINGLITERAL() != null) {
            return ctx.STRINGLITERAL().text.toString().substring(1)
        }
        if (ctx.expression() != null) {
            return visit(ctx.expression())
        }
        if (ctx.deref() != null) {
            return visit(ctx.deref())
        }

        return 0
    }

    override fun visitDeref(ctx: logoParser.DerefContext?): Any {
        val variableName = ctx!!.name().text

        val value = variables[variableName]
        Log.i("gg",variables.toString())
        return value!!
    }

    override fun visitLabel(ctx: logoParser.LabelContext?): Int {
        var text = ""
        if (ctx!!.deref() != null) {
            val value = visit(ctx.deref())
            text = value.toString()
        }
        if (ctx.STRINGLITERAL() != null) {
            text = ctx.STRINGLITERAL().text
            text = text.substring(1, text.length)
        }
        canvas.drawText(text, Turtle.Xposition, Turtle.Yposition, paint)
        return 0
    }

    override fun visitSettextsize(ctx: logoParser.SettextsizeContext?): Int {
        val size = ctx!!.expression().text.toFloat()
        paint.textSize = size
        return 0
    }

    //Procedury
    override fun visitProcedureDeclaration(ctx: logoParser.ProcedureDeclarationContext?): Int {
        val procedureName = ctx!!.name().text
        // przechowuj procedure w mapie
        procedures[procedureName] = ctx
        return 0
    }

    override fun visitProcedureInvocation(ctx: logoParser.ProcedureInvocationContext?): Int {
        val procedureName = ctx!!.name().text

        // Sprawdz czy procedura istnieje
        if (procedures.containsKey(procedureName)) {
            val procedureCtx = procedures[procedureName]

            // Obsluga parametrow procedury
            val parameterDeclarations = procedureCtx!!.parameterDeclarations()
            val arguments = ctx.expression()

            if (parameterDeclarations.size != arguments.size) {
                throw RuntimeException("Liczba argumentów nie pasuje do liczby parametrów.")
            }

            // Przypisz argumenty do zmiennych lokalnych
            val previousVariables = HashMap(variables) // Zapisz poprzednie zmienne
            for (i in parameterDeclarations.indices) {
                val paramName = parameterDeclarations[i].name().text
                val argumentValue = visit(arguments[i])
                variables[paramName] = argumentValue
            }

            try {
                // Wykonaj każdą linię ciała procedury
                for (line in procedureCtx.line()) {
                    visit(line)
                }
            } catch (e: StopException) {
                return 0
            } finally {
                variables = previousVariables // Przywróć poprzednie zmienne po zakończeniu procedury
            }
        } else {
            throw RuntimeException("Nieznana procedura: $procedureName")
        }

        return 0
    }



    //IF
    override fun visitIfe(ctx: logoParser.IfeContext?): Int {
        // sprawdź porownanie
        val comparisonResult = visit(ctx!!.comparison())

        // jesli wynik porownania jest prawdziwy wykonaj blok
        if (comparisonResult != 0) {
            visit(ctx.block())
        }

        return 0
    }

    override fun visitComparison(ctx: logoParser.ComparisonContext?): Int {
        var leftValue = 0f
        var rightValue = 0f

        if (ctx!!.expression(0) != null)  leftValue = visit(ctx.expression(0)).toString().toFloat()
        if (ctx.expression(1) != null) rightValue = visit(ctx.expression(1)).toString().toFloat()

        return when (val operator = ctx.comparisonOperator().text!!) {
            "<"  -> if (leftValue < rightValue) 1 else 0
            ">"  -> if (leftValue > rightValue) 1 else 0
            "="  -> if (leftValue == rightValue) 1 else 0
            "<=" -> if (leftValue <= rightValue) 1 else 0
            ">=" -> if (leftValue >= rightValue) 1 else 0
            "<>" -> if (leftValue != rightValue) 1 else 0
            else -> throw RuntimeException("Nieznany operator porównania: $operator")
        }
    }


    //STOP
    override fun visitStop(ctx: logoParser.StopContext?): Int {
        throw StopException("STOP")
    }





}