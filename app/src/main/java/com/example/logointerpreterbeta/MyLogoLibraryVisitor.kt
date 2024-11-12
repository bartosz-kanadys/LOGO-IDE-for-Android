package com.example.logointerpreterbeta

import com.example.logointerpreterbeta.interpreter.logoBaseVisitor
import com.example.logointerpreterbeta.interpreter.logoParser

class MyLogoLibraryVisitor : logoBaseVisitor<Any>() {
    private val procedures: MutableMap<String, logoParser.ProcedureDeclarationContext> = HashMap()

    fun getProcedures(): MutableMap<String, logoParser.ProcedureDeclarationContext> {
        return procedures
    }


    override fun visitProcedureDeclaration(ctx: logoParser.ProcedureDeclarationContext?): MutableMap<String, logoParser.ProcedureDeclarationContext> {
        val procedureName = ctx!!.name().text
        procedures[procedureName] = ctx
        return procedures
    }
}