package com.example.logointerpreterbeta

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb

import androidx.core.content.ContextCompat
import com.example.logointerpreterbeta.errors.StopException
import com.example.logointerpreterbeta.errors.SyntaxError
import com.example.logointerpreterbeta.interpreter.logoBaseVisitor
import com.example.logointerpreterbeta.interpreter.logoParser
import com.example.logointerpreterbeta.ui.theme.surfaceDarkMediumContrast
import com.example.logointerpreterbeta.ui.theme.surfaceLightMediumContrast
import kotlin.math.cos
import kotlin.math.sin

class MyLogoVisitor(private val context: Context, private val bgcolor: Int) : logoBaseVisitor<Any>() {
    companion object{
        val image = MyImage
    }
    private var turtleBitmap: Bitmap? = null  // Bitmapa dla żółwia

    private val canvas = Canvas(image)
    private var paint = Paint()

    private var variables: MutableMap<String, Any> = HashMap()
    private var procedures: MutableMap<String, logoParser.ProcedureDeclarationContext> = HashMap()

    init {
        // Ustawienia malowania (kolor, grubość linii)
        paint.color = Turtle.penColor
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        paint.textSize = 50f
        paint.isAntiAlias = true
        //canvas.drawRect(0f, 0f, MyImageWidth.toFloat(), MyImageHeight.toFloat(), paint)

        updateTurtleBitmap()
    }

    override fun visitFd(ctx: logoParser.FdContext?) {
        val distance = visit(ctx!!.expression()).toString().toFloat()
        // Konwersja kąta na radiany
        val angleRadians = Math.toRadians((Turtle.direction - 90).toDouble())

        // Obliczenie końcowych współrzędnych linii do narysowania z poprawką na wypełnianie rogów
        val endXtoDraw =
            (Turtle.Xposition + (distance + paint.strokeWidth / 2) * cos(angleRadians)).toFloat()
        val endYtoDraw =
            (Turtle.Yposition + (distance + paint.strokeWidth / 2) * sin(angleRadians)).toFloat()
        // obliczanie koncowych pozycji dla zolwia
        val endXTurtlePosition = (Turtle.Xposition + distance * cos(angleRadians)).toFloat()
        val endYTurtlePosition = (Turtle.Yposition + distance * sin(angleRadians)).toFloat()

        if (Turtle.isDown) canvas.drawLine(
            Turtle.Xposition,
            Turtle.Yposition,
            endXtoDraw,
            endYtoDraw,
            paint
        )

        Turtle.setAcctualPosition(endXTurtlePosition, endYTurtlePosition)
    }

    override fun visitBk(ctx: logoParser.BkContext?) {
        val distance = visit(ctx!!.expression()).toString().toFloat()
        // Konwersja kąta na radiany
        val angleRadians = Math.toRadians((Turtle.direction - 90 - 180).toDouble())

        // Obliczenie końcowych współrzędnych linii
        val endX = (Turtle.Xposition + distance * cos(angleRadians)).toFloat()
        val endY = (Turtle.Yposition + distance * sin(angleRadians)).toFloat()

        if (Turtle.isDown) canvas.drawLine(Turtle.Xposition, Turtle.Yposition, endX, endY, paint)

        Turtle.setAcctualPosition(endX, endY)
    }

    override fun visitArc(ctx: logoParser.ArcContext?) {
        val angle = visit(ctx!!.expression(0))!!.toString().toFloat()
        val distance = visit(ctx.expression(1)).toString().toFloat()

        val rectF = RectF(
            Turtle.Xposition - distance,
            Turtle.Yposition - distance,
            Turtle.Xposition + distance,
            Turtle.Yposition + distance
        )
        canvas.drawArc(rectF, 270f + Turtle.direction, angle, false, paint)
    }

    override fun visitRt(ctx: logoParser.RtContext?) {
        Turtle.direction += visit(ctx!!.expression()).toString().toFloat()
    }

    override fun visitLt(ctx: logoParser.LtContext?) {
        Turtle.direction -= visit(ctx!!.expression()).toString().toFloat()
    }

    override fun visitCs(ctx: logoParser.CsContext?) {
        canvas.drawColor(Color.WHITE)
    }

    override fun visitPu(ctx: logoParser.PuContext?) {
        Turtle.isDown = false
    }

    override fun visitPd(ctx: logoParser.PdContext?) {
        Turtle.isDown = true
    }

    override fun visitHome(ctx: logoParser.HomeContext?) {
        Turtle.setAcctualPosition(MyImageWidth.toFloat() / 2, MyImageHeight.toFloat() / 2)
    }

    override fun visitSt(ctx: logoParser.StContext?) {
        Turtle.isShowed = true
        val color = paint.color
        paint.color = Color.TRANSPARENT
        canvas.drawPoint(Turtle.Xposition, Turtle.Yposition, paint)
        paint.color = color
    }

    override fun visitHt(ctx: logoParser.HtContext?) {
        Turtle.isShowed = false
        val color = paint.color
        paint.color = Color.TRANSPARENT
        canvas.drawPoint(Turtle.Xposition, Turtle.Yposition, paint)
        paint.color = color
    }

    override fun visitSetxy(ctx: logoParser.SetxyContext?) {
        var x = ctx!!.expression(0).text.toFloat()
        var y = ctx.expression(1).text.toFloat()
        y = if (y < 0) MyImageHeight / 2 - y else MyImageHeight / 2 + y
        x = if (x < 0) MyImageWidth / 2 + x else MyImageWidth / 2 - x
        Turtle.setAcctualPosition(x, y)
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
        } catch (e: StopException) {
            return 0
        }

        return 0
    }

    override fun visitSetpencolor(ctx: logoParser.SetpencolorContext?) {
        if (ctx!!.expression() != null) {
            val intColor = visit(ctx.expression()).toString().toFloat().toInt()
            val color = penColors[intColor]
            paint.setColor(color)
            Turtle.penColor = color
        }

        if (ctx.number(0) != null && ctx.number(1) != null && ctx.number(2) != null) {
            val red = ctx.number(0).text.toInt()
            val green = ctx.number(1).text.toInt()
            val blue = ctx.number(2).text.toInt()
            val color = Color.rgb(red, green, blue).toInt()
            paint.setColor(color)
            Turtle.penColor = color
        }
    }

    override fun visitSetpensize(ctx: logoParser.SetpensizeContext?) {
        val size = visit(ctx!!.expression()).toString().toFloat().toInt()
        paint.strokeWidth = size.toFloat()
        Turtle.penSize = size
    }

    override fun visitSetbg(ctx: logoParser.SetbgContext?) {
        val intColor = visit(ctx!!.expression()).toString().toFloat().toInt()
        canvas.drawColor(penColors[intColor])
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

    override fun visitNumber(ctx: logoParser.NumberContext?): Float {
        return if (ctx!!.FLOAT() != null) {
            ctx.FLOAT().text.toFloat()
        } else {
            ctx.NUMBER().text.toFloat()
        }
    }

    override fun visitRandom(ctx: logoParser.RandomContext?): Int {
        val maxValue = visit(ctx!!.expression())!!.toString().toFloat().toInt()
        return (0..<maxValue).random()
    }

    override fun visitMake(ctx: logoParser.MakeContext?) {
        val variableName = ctx!!.STRINGLITERAL().text.toString().substring(1)
        variables[variableName] = visit(ctx.value())
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
        val value = variables[ctx!!.name().text]
        return value!!
    }

    override fun visitLabel(ctx: logoParser.LabelContext?) {
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
    }

    override fun visitSettextsize(ctx: logoParser.SettextsizeContext?) {
        paint.textSize = ctx!!.expression().text.toFloat() //size
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
                variables = previousVariables //Przywróć poprzednie zmienne po zakończeniu procedury
            }
        } else {
            SyntaxError.errors.add("Nieznana procedura: $procedureName")
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

        if (ctx!!.expression(0) != null) leftValue = visit(ctx.expression(0)).toString().toFloat()
        if (ctx.expression(1) != null) rightValue = visit(ctx.expression(1)).toString().toFloat()

        return when (val operator = ctx.comparisonOperator().text!!) {
            "<" -> if (leftValue < rightValue) 1 else 0
            ">" -> if (leftValue > rightValue) 1 else 0
            "=" -> if (leftValue == rightValue) 1 else 0
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

    private fun getBitmapFromImage(context: Context, drawable: Int): Bitmap {
        val db = ContextCompat.getDrawable(context, drawable)
        val bit = Bitmap.createBitmap(
            db!!.intrinsicWidth / 2, db.intrinsicHeight / 2, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bit)
        db.setBounds(0, 0, canvas.width, canvas.height)
        db.draw(canvas)

        return bit
    }

    private fun updateTurtleBitmap() {
        if (Turtle.isShowed) {
            // Pobieranie bitmapy żółwia i rotacja
            val arrow = getBitmapFromImage(context, R.drawable.turtle_simple_green)
            val matrix = Matrix()
            matrix.postRotate(Turtle.direction, arrow.width / 2f, arrow.height / 2f)
            turtleBitmap = Bitmap.createBitmap(arrow, 0, 0, arrow.width, arrow.height, matrix, true)
            canvas.drawBitmap(
                turtleBitmap!!,
                Turtle.Xposition - turtleBitmap!!.width / 2,
                Turtle.Yposition - turtleBitmap!!.height / 2,
                paint
            )

        } else {
            turtleBitmap = null
        }
    }

    override fun visitProg(ctx: logoParser.ProgContext?): Int {
        paint.setColor(Turtle.penColor)
        canvas.drawColor(bgcolor) //czyszczenie obrazka przed startem programu

        super.visitProg(ctx)
        updateTurtleBitmap()
        return 0
    }
}