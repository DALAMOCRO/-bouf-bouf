package com.boufbouf.app.feature.feed

import com.boufbouf.app.core.model.FeedVideo
import com.boufbouf.app.feature.feed.data.FeedRepository
import com.boufbouf.app.feature.feed.data.MockFeedData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FeedViewModelTest {
    @get:Rule val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `loads for you videos and toggles social state`() = runTest {
        val viewModel = FeedViewModel(FakeFeedRepository())
        advanceUntilIdle()

        assertEquals(10, viewModel.uiState.value.videos.size)
        assertFalse(viewModel.uiState.value.isLoading)

        viewModel.toggleLike("rfissa")
        viewModel.toggleSave("rfissa")

        assertTrue("rfissa" in viewModel.uiState.value.likedIds)
        assertTrue("rfissa" in viewModel.uiState.value.savedIds)
    }

    @Test
    fun `selecting following loads the following collection`() = runTest {
        val viewModel = FeedViewModel(FakeFeedRepository())
        advanceUntilIdle()

        viewModel.selectTab(FeedTab.FOLLOWING)
        advanceUntilIdle()

        assertEquals(FeedTab.FOLLOWING, viewModel.uiState.value.selectedTab)
        assertEquals(2, viewModel.uiState.value.videos.size)
    }
}

private class FakeFeedRepository : FeedRepository {
    override suspend fun getForYou(): List<FeedVideo> = MockFeedData.videos
    override suspend fun getFollowing(): List<FeedVideo> = MockFeedData.videos.take(2)
}
