package com.boufbouf.app.feature.feed.data

import com.boufbouf.app.core.model.FeedVideo
import com.boufbouf.app.core.network.RetrofitClient

class ApiFeedRepository : FeedRepository {

    override suspend fun getForYou(): List<FeedVideo> {
        val response = RetrofitClient.api.getFeed()

        return response.videos.map { dto ->
            FeedVideo(
                id = dto.id,
                videoUrl = dto.videoUrl,
                creatorName = dto.creatorName,
                creatorHandle = dto.creatorHandle,
                description = dto.description,
                hashtags = dto.hashtags,
                ingredientsPreview = emptyList(),
                likes = dto.likes,
                comments = dto.comments,
                recipe = null,
                accentColor = 0xFFC66A35,
            )
        }
    }

    override suspend fun getFollowing(): List<FeedVideo> {
        return getForYou()
    }
}