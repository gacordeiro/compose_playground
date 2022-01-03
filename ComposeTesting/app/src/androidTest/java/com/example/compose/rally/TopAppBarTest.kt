package com.example.compose.rally

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.theme.RallyTheme
import java.util.Locale
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest_currentTabSelected() {
        setTopAppBar(startingIn = RallyScreen.Accounts)

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertIsSelected()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists_merged() {
        setTopAppBar(startingIn = RallyScreen.Bills)

        composeTestRule
            .onRoot()
            .printToLog("currentLabelExists")

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Bills.name)
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists_unmerged() {
        setTopAppBar(startingIn = RallyScreen.Overview)

        composeTestRule
            .onRoot(useUnmergedTree = true)
            .printToLog("currentLabelExists")

        composeTestRule
            .onNode(
                hasText(RallyScreen.Overview.name.uppercase(Locale.getDefault())) and
                        hasParent(hasContentDescription(RallyScreen.Overview.name)),
                useUnmergedTree = true,
            )
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_clickChangesSelection() {
        setTopAppBar(startingIn = RallyScreen.Accounts)

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Bills.name)
            .assertIsNotSelected()
            .performClick()
            .assertIsSelected()
    }

    private fun setTopAppBar(startingIn: RallyScreen) {
        composeTestRule.setContent {
            val allScreens = RallyScreen.values().toList()
            var currentScreen by rememberSaveable { mutableStateOf(startingIn) }
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = { currentScreen = it },
                    currentScreen = currentScreen,
                )
            }
        }
    }
}