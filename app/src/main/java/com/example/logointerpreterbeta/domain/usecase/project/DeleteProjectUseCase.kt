package com.example.logointerpreterbeta.domain.usecase.project

import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import com.example.logointerpreterbeta.domain.repository.ProjectRepository
import javax.inject.Inject

class DeleteProjectUseCase @Inject constructor(
    private val projectRepository: ProjectRepository,
    private val configRepository: ConfigRepository
) {
    suspend operator fun invoke(projectToDelete: String, currentProjectName: String) {
        projectRepository.deleteProject(projectToDelete)

        if (projectToDelete == currentProjectName) {
            configRepository.updateLastProject("")
        }
    }
}