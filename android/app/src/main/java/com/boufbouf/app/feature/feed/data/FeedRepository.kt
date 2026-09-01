package com.boufbouf.app.feature.feed.data

import com.boufbouf.app.core.model.FeedVideo

/** Source interchangeable with the future GET /api/v1/feed implementation. */
interface FeedRepository {
    suspend fun getForYou(): List<FeedVideo>
    suspend fun getFollowing(): List<FeedVideo>
}
