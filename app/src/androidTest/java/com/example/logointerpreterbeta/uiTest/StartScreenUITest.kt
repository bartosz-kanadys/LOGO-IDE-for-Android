package com.example.logointerpreterbeta.uiTest

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.ui.navigation.AppNavHost
import com.example.logointerpreterbeta.ui.navigation.MainActivity
import com.example.logointerpreterbeta.ui.navigation.StartScreen
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.ui.screens.projects.ProjectViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class StartScreenUITest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var projectViewModel: ProjectViewModel


    @Before
    fun setUp() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            val navController = rememberNavController()

            LogoInterpreterBetaTheme {
                AppNavHost(navController, StartScreen)
            }
        }
        composeTestRule.waitForIdle() // Upewnij się, że Compose zakończył renderowanie

    }

    @Test
    fun testStartScreenNavigatesToProjectPanel(){

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Projekty").assertIsDisplayed()
        composeTestRule.onNodeWithText("Projekty").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Projekty").assertIsDisplayed()

    }


    @Test
    fun testStartScreenNavigatesToProjectPanelThenGoesBack(){
        //Odczekanie na zakończenie renderowania ekranu
        composeTestRule.waitForIdle()
        //Sprawdzenie czy wyświeltony został przycisk projekty i kliknięcie go
        composeTestRule.onNodeWithText("Projekty").assertIsDisplayed()
        composeTestRule.onNodeWithText("Projekty").performClick()

        //Odczekanie na zakończenie renderowania ekranu
        composeTestRule.waitForIdle()
        //Sprawdzenie czy wyświetlony został przycisk "nowy projekt"
        composeTestRule.onNodeWithText("Nowy projekt").assertIsDisplayed()
        //Kliknięcie przycisku z opisem "Return" co skutkuje powrotem do ekranu startowego
        composeTestRule.onNodeWithContentDescription("Return").performClick()

        //Odczekanie na zakończenie renderowania ekranu
        composeTestRule.waitForIdle()
        //Sprawdzenie czy wyświetlony został inny przycisk na ekranie startowym
        composeTestRule.onNodeWithText("Poradniki").assertIsDisplayed()

    }
}