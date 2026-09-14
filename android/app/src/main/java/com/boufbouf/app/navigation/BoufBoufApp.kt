package com.boufbouf.app.navigation

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.boufbouf.app.core.ui.BoufBoufOrange
import com.boufbouf.app.feature.auth.AuthViewModelFactory
import com.boufbouf.app.feature.auth.SessionStatus
import com.boufbouf.app.feature.auth.SessionViewModel
import com.boufbouf.app.feature.auth.ui.LoginRoute
import com.boufbouf.app.feature.auth.ui.RegisterRoute
import com.boufbouf.app.feature.comments.ui.CommentsRoute
import com.boufbouf.app.feature.feed.ui.FeedRoute
import com.boufbouf.app.feature.profile.ui.ProfileRoute
import com.boufbouf.app.feature.recipe.ui.RecipeRoute

private object Destinations {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val FEED = "feed"
    const val RECIPE = "recipe/{videoId}"
    const val COMMENTS = "comments/{videoId}"
    const val PROFILE = "profile/{creatorHandle}"

    fun recipe(videoId: String) = "recipe/$videoId"
    fun comments(videoId: String) = "comments/$videoId"
    fun profile(creatorHandle: String) =
        "profile/${creatorHandle.removePrefix("@")}"
}

@Composable
fun BoufBoufApp(
    context: Context,
) {
    val sessionViewModel: SessionViewModel = viewModel(
        factory = AuthViewModelFactory(context)
    )

    val sessionStatus by sessionViewModel.status.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        sessionViewModel.checkSession()
    }

    when (sessionStatus) {
        SessionStatus.CHECKING -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = BoufBoufOrange
                )
            }
        }

        SessionStatus.AUTHENTICATED -> {
            BoufBoufNavHost(
                context = context,
                startDestination = Destinations.FEED,
            )
        }

        SessionStatus.UNAUTHENTICATED -> {
            BoufBoufNavHost(
                context = context,
                startDestination = Destinations.LOGIN,
            )
        }
    }
}

@Composable
private fun BoufBoufNavHost(
    context: Context,
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        composable(Destinations.LOGIN) {
            LoginRoute(
                context = context,
                onLoginSuccess = {
                    navController.navigate(Destinations.FEED) {
                        popUpTo(Destinations.LOGIN) {
                            inclusive = true
                        }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Destinations.REGISTER)
                },
            )
        }

        composable(Destinations.REGISTER) {
            RegisterRoute(
                context = context,
                onRegisterSuccess = {
                    navController.navigate(Destinations.FEED) {
                        popUpTo(Destinations.LOGIN) {
                            inclusive = true
                        }
                    }
                },
                onBackToLogin = {
                    navController.popBackStack()
                },
            )
        }

        composable(Destinations.FEED) {
            FeedRoute(
                onRecipeClick = { videoId ->
                    navController.navigate(
                        Destinations.recipe(videoId)
                    )
                },
                onCommentClick = { videoId ->
                    navController.navigate(
                        Destinations.comments(videoId)
                    )
                },
                onProfileClick = { creatorHandle ->
                    navController.navigate(
                        Destinations.profile(creatorHandle)
                    )
                },
            )
        }

        composable(Destinations.RECIPE) { entry ->
            RecipeRoute(
                videoId = requireNotNull(
                    entry.arguments?.getString("videoId")
                ),
                onBack = navController::popBackStack,
            )
        }

        composable(Destinations.COMMENTS) { entry ->
            CommentsRoute(
                videoId = requireNotNull(
                    entry.arguments?.getString("videoId")
                ),
                onBack = navController::popBackStack,
            )
        }

        composable(Destinations.PROFILE) { entry ->
            ProfileRoute(
                creatorHandle = "@${requireNotNull(
                    entry.arguments?.getString("creatorHandle")
                )}",
                onBack = navController::popBackStack,
            )
        }
    }
}