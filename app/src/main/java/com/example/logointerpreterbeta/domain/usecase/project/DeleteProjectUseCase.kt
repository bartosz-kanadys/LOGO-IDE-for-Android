package com.example.logointerpreterbeta.domain.usecase.project

import com.example.logointerpreterbeta.domain.repository.ConfigRepository
import com.example.logointerpreterbeta.domain.repository.ProjectRepository
import com.example.logointerpreterbeta.domain.repository.SessionRepository
import javax.inject.Inject

class DeleteProjectUseCase @Inject constructor(
    private val projectRepository: ProjectRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(projectToDelete: String, currentProjectName: String) {
        projectRepository.deleteProject(projectToDelete)

        if (projectToDelete == currentProjectName) {
            sessionRepository.saveLastOpenedProject("")
        }
    }
}