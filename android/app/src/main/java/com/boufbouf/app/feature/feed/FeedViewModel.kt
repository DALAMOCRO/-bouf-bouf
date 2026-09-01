package com.boufbouf.app.feature.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boufbouf.app.core.model.FeedVideo
import com.boufbouf.app.feature.feed.data.FeedRepository
import com.boufbouf.app.feature.feed.data.MockFeedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class FeedTab { FOR_YOU, FOLLOWING }

data class FeedUiState(
    val selectedTab: FeedTab = FeedTab.FOR_YOU,
    val videos: List<FeedVideo> = emptyList(),
    val likedIds: Set<String> = emptySet(),
    val savedIds: Set<String> = emptySet(),
    val isLoading: Boolean = true,
)

class FeedViewModel(private val repository: FeedRepository = MockFeedRepository()) : ViewModel() {
    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()

    init { load(FeedTab.FOR_YOU) }

    fun selectTab(tab: FeedTab) {
        if (tab != _uiState.value.selectedTab) load(tab)
    }

    fun toggleLike(id: String) = updateIds(id, isLike = true)
    fun toggleSave(id: String) = updateIds(id, isLike = false)

    private fun load(tab: FeedTab) = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(selectedTab = tab, isLoading = true)
        val videos = if (tab == FeedTab.FOR_YOU) repository.getForYou() else repository.getFollowing()
        _uiState.value = _uiState.value.copy(videos = videos, isLoading = false)
    }

    private fun updateIds(id: String, isLike: Boolean) {
        _uiState.value = _uiState.value.let { state ->
            val target = if (isLike) state.likedIds else state.savedIds
            val updated = if (id in target) target - id else target + id
            if (isLike) state.copy(likedIds = updated) else state.copy(savedIds = updated)
        }
    }
}
