package com.boufbouf.app.feature.feed.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.boufbouf.app.core.model.FeedVideo
import com.boufbouf.app.feature.feed.FeedTab
import com.boufbouf.app.feature.feed.FeedUiState
import com.boufbouf.app.feature.feed.FeedViewModel
import java.util.Locale

@Composable
fun FeedRoute(
    onRecipeClick: (String) -> Unit,
    onCommentClick: (String) -> Unit,
    onProfileClick: (String) -> Unit,
    viewModel: FeedViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    FeedScreen(
        state = state,
        onTabSelected = viewModel::selectTab,
        onLikeClick = viewModel::toggleLike,
        onSaveClick = viewModel::toggleSave,
        onRecipeClick = onRecipeClick,
        onCommentClick = onCommentClick,
        onProfileClick = onProfileClick,
    )
}

@Composable
fun FeedScreen(
    state: FeedUiState,
    onTabSelected: (FeedTab) -> Unit,
    onLikeClick: (String) -> Unit,
    onSaveClick: (String) -> Unit,
    onRecipeClick: (String) -> Unit,
    onCommentClick: (String) -> Unit,
    onProfileClick: (String) -> Unit,
    videoPlaybackEnabled: Boolean = true,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.videos.isEmpty() -> {
                Text(
                    "Aucune vidéo pour le moment.",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                FeedPager(
                    state = state,
                    onLikeClick = onLikeClick,
                    onSaveClick = onSaveClick,
                    onRecipeClick = onRecipeClick,
                    onCommentClick = onCommentClick,
                    onProfileClick = onProfileClick,
                    videoPlaybackEnabled = videoPlaybackEnabled,
                )
            }
        }

        FeedTabs(
            selected = state.selectedTab,
            onSelected = onTabSelected,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 18.dp)
                .zIndex(10f),
        )
    }
}

@Composable
private fun FeedPager(
    state: FeedUiState,
    onLikeClick: (String) -> Unit,
    onSaveClick: (String) -> Unit,
    onRecipeClick: (String) -> Unit,
    onCommentClick: (String) -> Unit,
    onProfileClick: (String) -> Unit,
    videoPlaybackEnabled: Boolean,
) {
    val pagerState = rememberPagerState(
        pageCount = { state.videos.size }
    )

    VerticalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .testTag("feed_pager"),
        beyondViewportPageCount = 1,
        key = { state.videos[it].id },
    ) { page ->
        val isVisible = pagerState.currentPage == page

        FeedVideoCard(
            video = state.videos[page],
            isVisible = isVisible && videoPlaybackEnabled,
            isLiked = state.videos[page].id in state.likedIds,
            isSaved = state.videos[page].id in state.savedIds,
            onLikeClick = onLikeClick,
            onSaveClick = onSaveClick,
            onRecipeClick = onRecipeClick,
            onCommentClick = onCommentClick,
            onProfileClick = onProfileClick,
        )
    }
}

@Composable
private fun FeedTabs(
    selected: FeedTab,
    onSelected: (FeedTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(Color.Black.copy(alpha = 0.25f)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FeedTab.entries.forEach { tab ->
            val isSelected = tab == selected

            Text(
                text = if (tab == FeedTab.FOR_YOU) {
                    "POUR VOUS"
                } else {
                    "ABONNEMENTS"
                },
                color = if (isSelected) {
                    Color.White
                } else {
                    Color.White.copy(alpha = .62f)
                },
                fontWeight = if (isSelected) {
                    FontWeight.ExtraBold
                } else {
                    FontWeight.Medium
                },
                fontSize = 14.sp,
                modifier = Modifier
                    .testTag("tab_${tab.name}")
                    .padding(
                        horizontal = 12.dp,
                        vertical = 10.dp,
                    )
                    .clickableNoIndication {
                        println("BOUF-BOUF TAB CLICK: $tab")
                        onSelected(tab)
                    },
            )
        }
    }
}

@Composable
private fun FeedVideoCard(
    video: FeedVideo,
    isVisible: Boolean,
    isLiked: Boolean,
    isSaved: Boolean,
    onLikeClick: (String) -> Unit,
    onSaveClick: (String) -> Unit,
    onRecipeClick: (String) -> Unit,
    onCommentClick: (String) -> Unit,
    onProfileClick: (String) -> Unit,
) {
    val accent = Color(video.accentColor)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(accent),
    ) {
        if (isVisible) {
            VideoPlayer(
                videoUrl = video.videoUrl,
                modifier = Modifier.fillMaxSize(),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color(0xE01E1712),
                        )
                    )

                    onDrawBehind {
                        drawRect(gradient)
                    }
                },
        )

        ContentOverlay(
            video = video,
            isLiked = isLiked,
            isSaved = isSaved,
            onLikeClick = { onLikeClick(video.id) },
            onSaveClick = { onSaveClick(video.id) },
            onRecipeClick = { onRecipeClick(video.id) },
            onCommentClick = { onCommentClick(video.id) },
            onProfileClick = { onProfileClick(video.creatorHandle) },
            modifier = Modifier.align(Alignment.BottomStart),
        )
    }
}

@Composable
private fun ContentOverlay(
    video: FeedVideo,
    isLiked: Boolean,
    isSaved: Boolean,
    onLikeClick: () -> Unit,
    onSaveClick: () -> Unit,
    onRecipeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(
            start = 20.dp,
            end = 12.dp,
            bottom = 26.dp,
        ),
        verticalAlignment = Alignment.Bottom,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickableNoIndication(onProfileClick),
            ) {
                Avatar(
                    video.creatorName,
                    Color(video.accentColor),
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    video.creatorHandle,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }

            Text(
                video.description,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                video.hashtags.joinToString(" "),
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                video.ingredientsPreview.joinToString(" · "),
                color = Color.White.copy(alpha = .86f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            if (video.recipe != null) {
                Button(
                    onClick = onRecipeClick,
                    modifier = Modifier.testTag("recipe_${video.id}"),
                ) {
                    Text("VOIR LA RECETTE")
                }
            }
        }

        ActionColumn(
            video = video,
            isLiked = isLiked,
            isSaved = isSaved,
            onLikeClick = onLikeClick,
            onSaveClick = onSaveClick,
            onCommentClick = onCommentClick,
            onProfileClick = onProfileClick,
        )
    }
}

@Composable
private fun ActionColumn(
    video: FeedVideo,
    isLiked: Boolean,
    isSaved: Boolean,
    onLikeClick: () -> Unit,
    onSaveClick: () -> Unit,
    onCommentClick: () -> Unit,
    onProfileClick: () -> Unit,
) {
    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        ActionButton(
            icon = if (isLiked) {
                Icons.Default.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            description = if (isLiked) {
                "Retirer le j’aime"
            } else {
                "J’aime"
            },
            onClick = onLikeClick,
            tint = if (isLiked) {
                MaterialTheme.colorScheme.primary
            } else {
                Color.White
            },
        )

        Text(
            compactNumber(video.likes),
            color = Color.White,
            fontSize = 12.sp,
        )

        ActionButton(
            icon = Icons.Default.ChatBubbleOutline,
            description = "Commentaires",
            onClick = onCommentClick,
            tint = Color.White,
        )

        Text(
            compactNumber(video.comments),
            color = Color.White,
            fontSize = 12.sp,
        )

        ActionButton(
            icon = if (isSaved) {
                Icons.Default.Bookmark
            } else {
                Icons.Default.BookmarkBorder
            },
            description = if (isSaved) {
                "Retirer des sauvegardes"
            } else {
                "Sauvegarder"
            },
            onClick = onSaveClick,
            tint = Color.White,
        )

        ActionButton(
            icon = Icons.Default.IosShare,
            description = "Partager",
            onClick = { shareVideo(context, video) },
            tint = Color.White,
        )
    }
}

@Composable
private fun ActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    description: String,
    onClick: () -> Unit,
    tint: Color,
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp),
    ) {
        Icon(
            icon,
            contentDescription = description,
            tint = tint,
            modifier = Modifier.size(27.dp),
        )
    }
}

@Composable
private fun Avatar(
    name: String,
    color: Color,
) {
    Box(
        modifier = Modifier
            .size(34.dp)
            .background(color, CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            name.take(1).uppercase(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}

private fun compactNumber(value: Int): String =
    if (value >= 1000) {
        String.format(
            Locale.FRANCE,
            "%.1fk",
            value / 1000f,
        ).replace(",", ".")
    } else {
        value.toString()
    }

private fun Modifier.clickableNoIndication(
    onClick: () -> Unit,
): Modifier =
    this.clickable(onClick = onClick)

private fun shareVideo(
    context: Context,
    video: FeedVideo,
) {
    context.startActivity(
        Intent.createChooser(
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "${video.description} — Bouf-Bouf",
                )
            },
            "Partager une idée cuisine",
        ),
    )
}