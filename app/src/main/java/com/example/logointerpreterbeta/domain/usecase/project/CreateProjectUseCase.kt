package com.example.logointerpreterbeta.domain.usecase.project

import com.example.logointerpreterbeta.domain.repository.ProjectRepository
import jakarta.inject.Inject

sealed class CreateProjectResult {
    object Success : CreateProjectResult()
    object NameAlreadyExists : CreateProjectResult()
    object EmptyName : CreateProjectResult()
    object TooLongName : CreateProjectResult()
}

class CreateProjectUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {
    suspend operator fun invoke(projectName: String): CreateProjectResult {
        if (projectName.isBlank()) {
            return CreateProjectResult.EmptyName
        }
        if (projectName.length > 20) {
            return CreateProjectResult.TooLongName
        }

        val projectCreated = projectRepository.createNewProject(projectName)

        return if (projectCreated) {
            CreateProjectResult.Success
        } else {
            CreateProjectResult.NameAlreadyExists
        }
    }
}