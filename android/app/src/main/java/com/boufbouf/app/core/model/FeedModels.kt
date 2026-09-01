package com.boufbouf.app.core.model

data class Recipe(
    val title: String,
    val preparationMinutes: Int,
    val servings: Int,
    val ingredients: List<String>,
    val steps: List<String>,
)

data class FeedVideo(
    val id: String,
    val videoUrl: String,
    val creatorName: String,
    val creatorHandle: String,
    val description: String,
    val hashtags: List<String>,
    val ingredientsPreview: List<String>,
    val likes: Int,
    val comments: Int,
    val recipe: Recipe?,
    val accentColor: Long,
)
