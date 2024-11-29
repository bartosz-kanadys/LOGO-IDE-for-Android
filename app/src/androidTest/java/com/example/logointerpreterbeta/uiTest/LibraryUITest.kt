package com.example.logointerpreterbeta.uiTest

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.logointerpreterbeta.navigation.AppNavHost
import com.example.logointerpreterbeta.navigation.Libraries
import com.example.logointerpreterbeta.navigation.MainActivity
import com.example.logointerpreterbeta.ui.theme.LogoInterpreterBetaTheme
import com.example.logointerpreterbeta.viewModels.LibraryViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LibraryUITest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var libraryViewModel: LibraryViewModel

    @Before
    fun setUp() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            libraryViewModel = hiltViewModel()

            val navController = rememberNavController()

            LogoInterpreterBetaTheme {
                AppNavHost(navController, Libraries)
            }
        }
        composeTestRule.waitForIdle()

    }
    @Test
    fun jakiesTestyBibliotek() {

    }

}