package com.example.logointerpreterbeta.domain.usecase.project

import com.example.logointerpreterbeta.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProjectsMapUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {
    operator fun invoke(): Flow<Map<String, String>> {
        return projectRepository.getProjectsMap()
    }
}