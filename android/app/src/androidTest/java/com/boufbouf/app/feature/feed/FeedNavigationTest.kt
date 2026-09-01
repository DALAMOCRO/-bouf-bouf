package com.boufbouf.app.feature.feed

import androidx.compose.ui.test.assertExists
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.activity.ComponentActivity
import com.boufbouf.app.feature.feed.ui.FeedScreen
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FeedNavigationTest {
    @get:Rule val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun recipe_callToAction_navigatesWithTheSelectedVideoId() {
        var selectedVideoId: String? = null
        composeRule.setContent {
            FeedScreen(
                state = FeedUiState(videos = com.boufbouf.app.feature.feed.data.MockFeedData.videos, isLoading = false),
                onTabSelected = {},
                onLikeClick = {},
                onSaveClick = {},
                onRecipeClick = { selectedVideoId = it },
                videoPlaybackEnabled = false,
            )
        }

        composeRule.onNodeWithTag("feed_pager").assertExists()
        composeRule.onNodeWithTag("recipe_rfissa").performClick()
        assertEquals("rfissa", selectedVideoId)
    }
}
