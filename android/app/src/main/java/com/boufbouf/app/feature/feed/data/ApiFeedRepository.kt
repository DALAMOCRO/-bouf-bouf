package com.boufbouf.app.feature.feed.data

import com.boufbouf.app.core.model.FeedVideo
import com.boufbouf.app.core.network.RetrofitClient

class ApiFeedRepository : FeedRepository {

    override suspend fun getForYou(): List<FeedVideo> {
        val response = RetrofitClient.api.getFeed()

        return response.videos.map { dto ->
            FeedVideo(
                id = dto.id,
                videoUrl = "https://storage.googleapis.com/exoplayer-test-media-1/gen-3/screens/dash-vod-single-segment/video-avc-baseline-480.mp4",
                creatorName = dto.creatorName,
                creatorHandle = dto.creatorHandle,
                description = dto.description,
                hashtags = dto.hashtags,
                ingredientsPreview = emptyList(),
                likes = 0,
                comments = 0,
                recipe = null,
                accentColor = 0xFFC66A35,
            )
        }
    }

    override suspend fun getFollowing(): List<FeedVideo> {
        return getForYou()
    }
}