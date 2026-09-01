package com.boufbouf.app.feature.feed

import com.boufbouf.app.feature.feed.data.MockFeedRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MockFeedRepositoryTest {
    @Test
    fun `mock feed exposes ten varied culinary videos`() = runTest {
        val videos = MockFeedRepository().getForYou()

        assertEquals(10, videos.size)
        assertTrue(videos.any { "#cuisinemarocaine" in it.hashtags })
        assertTrue(videos.any { "#ramadan" in it.hashtags })
        assertTrue(videos.any { it.recipe == null })
        assertTrue(videos.any { it.recipe != null })
    }
}
