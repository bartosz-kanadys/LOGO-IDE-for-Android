package com.example.logointerpreterbeta.domain.usecase.project

import com.example.logointerpreterbeta.domain.models.Project
import com.example.logointerpreterbeta.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProjectUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {
    operator fun invoke(projectName: String): Flow<Project?> {
        return projectRepository.getProject(projectName)
    }
}