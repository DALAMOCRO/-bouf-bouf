package com.boufbouf.app.feature.comments.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class MockComment(
    val author: String,
    val text: String,
)

@Composable
fun CommentsRoute(
    videoId: String,
    onBack: () -> Unit,
) {
    val video = com.boufbouf.app.feature.feed.data.MockFeedData.videos
        .firstOrNull { it.id == videoId }

    val comments = listOf(
        MockComment("Sarah", "Ça a l'air délicieux ! 😍"),
        MockComment("Yasmine", "Merci pour la recette ❤️"),
        MockComment("Amine", "Je vais tester ça ce soir."),
        MockComment("Lina", "Super idée !"),
    )

    CommentsScreen(
        creatorName = video?.creatorName ?: "Créateur",
        comments = comments,
        onBack = onBack,
    )
}

@Composable
fun CommentsScreen(
    creatorName: String,
    comments: List<MockComment>,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Retour",
                )
            }

            Text(
                "COMMENTAIRES",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
        }

        Text(
            "Commentaires sur la vidéo de $creatorName",
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
            fontWeight = FontWeight.Bold,
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            items(comments) { comment ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        comment.author,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        comment.text,
                        color = Color.White.copy(alpha = 0.85f),
                    )
                }
            }
        }
    }
}