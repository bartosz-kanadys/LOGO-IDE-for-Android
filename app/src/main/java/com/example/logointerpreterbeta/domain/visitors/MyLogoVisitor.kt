package com.example.logointerpreterbeta.domain.visitors

import com.example.logointerpreterbeta.domain.interpreter.LogoInterpreter
import com.example.logointerpreterbeta.domain.interpreter.errors.StopException
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoBaseVisitor
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoParser
import com.example.logointerpreterbeta.domain.drawing.DrawingDelegate
import com.example.logointerpreterbeta.domain.models.drawing.DrawingResult
import com.example.logointerpreterbeta.domain.models.drawing.PenState
import com.example.logointerpreterbeta.domain.models.drawing.TurtleState
import com.example.logointerpreterbeta.domain.repository.LibraryRepository
import kotlin.math.cos
import kotlin.math.sin

open class MyLogoVisitor(
    protected val drawingDelegate: DrawingDelegate,
    private val libraryRepository: LibraryRepository?,
    protected val isDarkMode: Boolean = false
) : logoBaseVisitor<Any>() {

    protected open val errors = mutableListOf<String>()
    protected lateinit var turtleState: TurtleState
    protected var variables: MutableMap<String, Any> = HashMap()
    protected var procedures: MutableMap<String, logoParser.ProcedureDeclarationContext> = HashMap()

    fun resetTurtleState() {
        turtleState = TurtleState(
            x = drawingDelegate.getCanvasWidth() / 2,
            y = drawingDelegate.getCanvasHeight() / 2,
            penState = PenState()
        )
    }

    override fun visitFd(ctx: logoParser.FdContext?) {
        val distance = visit(ctx!!.expression()).toString().toFloat()
        // Konwersja kąta na radiany
        val angleRadians = Math.toRadians((turtleState.direction - 90).toDouble())

        val startX = turtleState.x
        val startY = turtleState.y
        val endX = (startX + distance * cos(angleRadians)).toFloat()
        val endY = (startY + distance * sin(angleRadians)).toFloat()

        turtleState = turtleState.copy(x = endX, y = endY)

        if (turtleState.isPenDown) {
            // The visitor tells the delegate what to draw.
            drawingDelegate.drawLine(startX, startY, endX, endY, turtleState.penState)

        }
        drawingDelegate.updateTurtleBitmap(turtleState)
    }

    override fun visitBk(ctx: logoParser.BkContext?) {
        val distance = visit(ctx!!.expression()).toString().toFloat()
        // Konwersja kąta na radiany
        val angleRadians = Math.toRadians((turtleState.direction- 90 - 180).toDouble())

        val startX = turtleState.x
        val startY = turtleState.y
        val endX = (startX + distance * cos(angleRadians)).toFloat()
        val endY = (startY + distance * sin(angleRadians)).toFloat()

        if (turtleState.isPenDown) {
            drawingDelegate.drawLine(startX, startY, endX, endY, turtleState.penState)
            drawingDelegate.updateTurtleBitmap(turtleState)
        }

        turtleState = turtleState.copy(x = endX, y = endY)
    }

    override fun visitArc(ctx: logoParser.ArcContext?) {
        val angle = visit(ctx!!.expression(0))!!.toString().toFloat()
        val distance = visit(ctx.expression(1)).toString().toFloat()

        // In Logo, ARC moves the turtle along the circumference. The center of the arc is to the turtle's right.
        val angleToCenterRad = Math.toRadians((turtleState.direction).toDouble())
        val centerX = turtleState.x + distance * cos(angleToCenterRad)
        val centerY = turtleState.y + distance * sin(angleToCenterRad)

        // The start angle for drawing needs to be calculated relative to the center.
        val startAngle = turtleState.direction + 90

        if (turtleState.isPenDown) {
            drawingDelegate.drawArc(centerX, centerY, distance, startAngle, angle, turtleState.penState)
            drawingDelegate.updateTurtleBitmap(turtleState)
        }
    }

    override fun visitRt(ctx: logoParser.RtContext?) {
        val angle = visit(ctx!!.expression()).toString().toFloat()
        turtleState = turtleState.copy(direction = (turtleState.direction + angle) % 360)
        drawingDelegate.updateTurtleBitmap(turtleState)
    }

    override fun visitLt(ctx: logoParser.LtContext?) {
        val angle = visit(ctx!!.expression()).toString().toFloat()
        turtleState = turtleState.copy(direction = (turtleState.direction - angle + 360) % 360)
        drawingDelegate.updateTurtleBitmap(turtleState)
    }

    override fun visitCs(ctx: logoParser.CsContext?) {

        drawingDelegate.clearScreen(isDarkMode, color = null)
        drawingDelegate.updateTurtleBitmap(turtleState)
        // CS also homes the turtle
        visitHome(null)
    }

    override fun visitPu(ctx: logoParser.PuContext?) {
//        TurtleUI.isDown = false
        turtleState = turtleState.copy(isPenDown = false)
    }

    override fun visitPd(ctx: logoParser.PdContext?) {
//        TurtleUI.isDown = true
        turtleState = turtleState.copy(isPenDown = true)
    }

    override fun visitHome(ctx: logoParser.HomeContext?) {
        turtleState = turtleState.copy(
            x = drawingDelegate.getCanvasWidth() / 2,
            y = drawingDelegate.getCanvasHeight() / 2
        )
        drawingDelegate.updateTurtleBitmap(turtleState)
    }

    override fun visitSt(ctx: logoParser.StContext?) {
        turtleState = turtleState.copy(isVisible = true)

    }

    override fun visitHt(ctx: logoParser.HtContext?) {
        turtleState = turtleState.copy(isVisible = false)

    }

    override fun visitSetxy(ctx: logoParser.SetxyContext?) {
        var x = ctx!!.expression(0).text.toFloat()
        var y = ctx.expression(1).text.toFloat()

        val canvasX = drawingDelegate.getCanvasWidth() / 2 + x
        val canvasY = drawingDelegate.getCanvasHeight() / 2 - y // Y is inverted in graphics canvases

        turtleState = turtleState.copy(x = canvasX, y = canvasY)
        drawingDelegate.updateTurtleBitmap(turtleState)
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
        val newColor = if (ctx!!.expression() != null) {
            val intColor = visit(ctx.expression()).toString().toFloat().toInt()
            penColors[intColor]

        } else if (ctx.number(0) != null && ctx.number(1) != null && ctx.number(2) != null) {
            val red = ctx.number(0).text.toInt()
            val green = ctx.number(1).text.toInt()
            val blue = ctx.number(2).text.toInt()
            (0xFF shl 24) or (red shl 16) or (green shl 8) or blue // Create ARGB integer
        } else {
            0xFF000000.toInt() // domyślnie czarny
        }
        val newPenState = turtleState.penState.copy(color = newColor)
        turtleState = turtleState.copy(penState = newPenState)
    }

    override fun visitSetpensize(ctx: logoParser.SetpensizeContext?) {
        val size = visit(ctx!!.expression()).toString().toFloat()
        val newPenState = turtleState.penState.copy(size = size)
        turtleState = turtleState.copy(penState = newPenState)
    }

    override fun visitSetbg(ctx: logoParser.SetbgContext?) {
        val color = visit(ctx!!.expression()).toString().toFloat().toInt()
        drawingDelegate.clearScreen(isDarkMode, color)
        return Unit
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
//        canvas.drawText(text, TurtleUI.Xposition, TurtleUI.Yposition, paint)
        drawingDelegate.drawText(text, turtleState.x, turtleState.y, turtleState.penState)

    }

    override fun visitSettextsize(ctx: logoParser.SettextsizeContext?) {
        val textSize = ctx!!.expression().text.toFloat() //size
        val newPenState = turtleState.penState.copy(textSize = textSize)
        turtleState = turtleState.copy(penState = newPenState)
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
            errors.add("Nieznana procedura: $procedureName w linii ${ctx.start.line}")
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




    override fun visitProg(ctx: logoParser.ProgContext?): Int {
        resetTurtleState()
        drawingDelegate.clearScreen(isDarkMode, null)

        try {
            for (line in ctx!!.line()) {
                visit(line)
            }
        } catch (e: StopException) {
            return 0
        }
        drawingDelegate.updateTurtleBitmap(turtleState)
        return 0
    }

    override fun visitUse(ctx: logoParser.UseContext?): Int {
        val libraryName = ctx!!.name().text

        val logo = LogoInterpreter(
            drawingDelegate = drawingDelegate,
            libraryRepository = libraryRepository!!,
            isDarkMode
        )
        val libraries = libraryRepository.loadLibraries()
        val library = libraries.find { it.name == libraryName }
        val procedureList = library!!.procedures
        for (procedure in procedureList) {
            logo.processProcedure(procedure.code + "\n")
        }

        val newProceduresCtx = logo.getProceduresFromLibrary()
        procedures.putAll(newProceduresCtx)
        return 0
    }

    fun getImage(): DrawingResult {
        return drawingDelegate.getDrawing()
    }

    fun getArrowImage(): DrawingResult {
        return drawingDelegate.getArrowDrawing()
    }
}