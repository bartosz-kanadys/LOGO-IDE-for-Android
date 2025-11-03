package com.example.logointerpreterbeta.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Library(
    val name: String,
    val author: String,
    val description: String,
    val procedures: List<Procedure>
) : Parcelable

@Parcelize
data class Procedure(
    val name: String,
    val description: String,
    val parameters: List<Parameter>?,
    val code: String,
) : Parcelable

@Parcelize
data class Parameter(
    val name: String,
    val type: String,
    val description: String
) : Parcelable