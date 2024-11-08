package com.example.logointerpreterbeta.Projects

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Project(
    val name: String,
    val pathToFolder: String,
    var files: List<ProjectFile>
) : Parcelable

@Parcelize
data class ProjectFile(
    val name: String,
    val pathToFile: String,
    val fileType: String
) : Parcelable
