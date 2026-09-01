package com.boufbouf.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.boufbouf.app.feature.comments.ui.CommentsRoute
import com.boufbouf.app.feature.feed.ui.FeedRoute
import com.boufbouf.app.feature.profile.ui.ProfileRoute
import com.boufbouf.app.feature.recipe.ui.RecipeRoute

private object Destinations {
    const val FEED = "feed"
    const val RECIPE = "recipe/{videoId}"
    const val COMMENTS = "comments/{videoId}"
    const val PROFILE = "profile/{creatorHandle}"

    fun recipe(videoId: String) = "recipe/$videoId"
    fun comments(videoId: String) = "comments/$videoId"
    fun profile(creatorHandle: String) = "profile/${creatorHandle.removePrefix("@")}"
}

@Composable
fun BoufBoufApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.FEED,
    ) {
        composable(Destinations.FEED) {
            FeedRoute(
                onRecipeClick = { videoId ->
                    navController.navigate(Destinations.recipe(videoId))
                },
                onCommentClick = { videoId ->
                    navController.navigate(Destinations.comments(videoId))
                },
                onProfileClick = { creatorHandle ->
                    navController.navigate(Destinations.profile(creatorHandle))
                },
            )
        }

        composable(Destinations.RECIPE) { entry ->
            RecipeRoute(
                videoId = requireNotNull(entry.arguments?.getString("videoId")),
                onBack = navController::popBackStack,
            )
        }

        composable(Destinations.COMMENTS) { entry ->
            CommentsRoute(
                videoId = requireNotNull(entry.arguments?.getString("videoId")),
                onBack = navController::popBackStack,
            )
        }

        composable(Destinations.PROFILE) { entry ->
            ProfileRoute(
                creatorHandle = "@${requireNotNull(entry.arguments?.getString("creatorHandle"))}",
                onBack = navController::popBackStack,
            )
        }
    }
}