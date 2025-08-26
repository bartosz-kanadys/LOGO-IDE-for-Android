package com.example.logointerpreterbeta.domain.visitors

import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoBaseVisitor
import com.example.logointerpreterbeta.domain.interpreter.antlrFIles.logoParser

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