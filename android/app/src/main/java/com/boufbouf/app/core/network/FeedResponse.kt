package com.boufbouf.app.core.network

data class FeedResponse(
    val videos: List<FeedVideoDto>
)

data class FeedVideoDto(
    val id: String,
    val creatorName: String,
    val creatorHandle: String,
    val description: String,
    val hashtags: List<String>,
    val videoUrl: String,
    val likes: Int,
    val comments: Int,
)