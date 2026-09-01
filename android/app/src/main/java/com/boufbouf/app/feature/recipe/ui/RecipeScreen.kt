package com.boufbouf.app.feature.recipe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.sp
import com.boufbouf.app.core.model.FeedVideo
import com.boufbouf.app.feature.feed.data.MockFeedData

@Composable
fun RecipeRoute(videoId: String, onBack: () -> Unit) {
    val video = MockFeedData.videos.first { it.id == videoId }
    RecipeScreen(video = video, onBack = onBack)
}

@Composable
fun RecipeScreen(video: FeedVideo, onBack: () -> Unit) {
    val recipe = requireNotNull(video.recipe)
    LazyColumn(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Retour") }
                Text("RECETTE", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            }
            Column(modifier = Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
                Text(recipe.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold)
                Text("${recipe.preparationMinutes} min  ·  ${recipe.servings} portions", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
                RecipeSection("INGRÉDIENTS", recipe.ingredients.map { "• $it" })
                RecipeSection("ÉTAPES", recipe.steps.mapIndexed { index, step -> "${index + 1}. $step" })
                Spacer(Modifier.size(12.dp))
            }
        }
    }
}

@Composable
private fun RecipeSection(title: String, lines: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        lines.forEach { Text(it, color = Color.White, lineHeight = 22.sp) }
    }
}
