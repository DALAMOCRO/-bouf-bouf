package com.boufbouf.app.feature.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.boufbouf.app.core.model.FeedVideo

@Composable
fun ProfileRoute(
    creatorHandle: String,
    onBack: () -> Unit,
) {
    val videos = com.boufbouf.app.feature.feed.data.MockFeedData.videos
        .filter { it.creatorHandle == creatorHandle }

    val video = videos.firstOrNull()

    ProfileScreen(
        creatorName = video?.creatorName ?: creatorHandle,
        creatorHandle = creatorHandle,
        videoCount = videos.size,
        onBack = onBack,
    )
}

@Composable
fun ProfileScreen(
    creatorName: String,
    creatorHandle: String,
    videoCount: Int,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
            }

            Text(
                "PROFIL",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .size(88.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    creatorName.take(1).uppercase(),
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                )
            }

            Text(
                creatorName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
            )

            Text(
                creatorHandle,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                "Créateur cuisine sur Bouf-Bouf",
                color = Color.White.copy(alpha = 0.75f),
            )

            Text(
                "$videoCount vidéo${if (videoCount > 1) "s" else ""}",
                fontWeight = FontWeight.Bold,
            )

            Button(onClick = { }) {
                Text("SUIVRE")
            }
        }

        Spacer(Modifier.size(8.dp))
    }
}