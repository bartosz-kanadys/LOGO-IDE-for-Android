package com.example.logointerpreterbeta.uiTest

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.ui.navigation.AppNavHost
import com.example.logointerpreterbeta.ui.navigation.MainActivity
import com.example.logointerpreterbeta.ui.navigation.Projects
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.ui.viewModels.ProjectViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ProjectUITest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var projectViewModel: ProjectViewModel


    @Before
    fun setUp() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            projectViewModel = hiltViewModel()

            val navController = rememberNavController()

            LogoInterpreterBetaTheme {
                AppNavHost(navController, Projects)
            }
        }
        composeTestRule.waitForIdle() // Upewnij się, że Compose zakończył renderowanie

    }

    @Test
    fun testCreateNewProject() {
        // Kliknij przycisk "Nowy projekt"
        composeTestRule.onNodeWithText("Nowy projekt").performClick()

        // Wprowadź nazwę projektu
        composeTestRule.onNodeWithTag("PodajNazwe").performTextInput("TestowyProjekt")

        // Kliknij przycisk do utworzenia projektu
        composeTestRule.onNodeWithTag("AddProjectButton").performClick()

        // Sprawdź, czy projekt pojawia się na liście
        composeTestRule.onNodeWithText("TestowyProjekt").assertIsDisplayed()
    }

    @Test
    fun testDeleteProject() {
        // Utwórz projekt do testów
        projectViewModel.createNewProject("ProjektDoUsunięcia")

        // Odśwież UI
        composeTestRule.waitForIdle()

        // Kliknij ikonę usuwania dla projektu
        composeTestRule.onAllNodesWithText("ProjektDoUsunięcia")
            .onFirst()
            .assertExists() // Sprawdzamy, czy projekt istnieje
            .onChildAt(0) // Znajdujemy odpowiedni element w hierarchii (np. przycisk)
            .performClick() // Klikamy przycisk "Usuń"

        // Potwierdź w oknie dialogowym
        composeTestRule.onNodeWithText("Usuń").performClick()

        // Sprawdź, czy projekt nie jest już widoczny
        composeTestRule.onNodeWithText("ProjektDoUsunięcia").assertDoesNotExist()
    }

    @Test
    fun testOpenProjectNavigatesToInterpreter() {
        // Utwórz projekt do testów
        projectViewModel.createNewProject("ProjektDoOtwarcia")

        // Odśwież UI
        composeTestRule.waitForIdle()

        // Kliknij projekt
        composeTestRule.onNodeWithText("ProjektDoOtwarcia").performClick()
        composeTestRule.waitForIdle()
        // Sprawdź, czy nawigacja do interpretatora się powiodła
        composeTestRule.onNodeWithText("ProjektDoOtwarcia").assertIsDisplayed()
    }

    @Test
    fun testErrorOnDuplicateProjectName() {
        // Utwórz projekt o nazwie "Duplikat"
        projectViewModel.createNewProject("Duplikat")

        // Kliknij przycisk "Nowy projekt"
        composeTestRule.onAllNodesWithText("Nowy projekt").onFirst().performClick()

        composeTestRule.waitForIdle()
        // Wprowadź nazwę projektu "Duplikat"
        composeTestRule.onNodeWithTag("PodajNazwe").performTextInput("Duplikat")

        // Kliknij przycisk do utworzenia projektu
        composeTestRule.onNodeWithTag("AddProjectButton").performClick()
        composeTestRule.waitForIdle()

        // Sprawdź, czy komunikat błędu jest widoczny
        composeTestRule.onNodeWithTag("duplicateError")
            .assertIsDisplayed()
    }
}