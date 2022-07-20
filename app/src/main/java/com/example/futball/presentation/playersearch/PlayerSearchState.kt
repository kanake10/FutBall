package com.example.futball.presentation.playersearch

import com.example.futball.domain.models.Players

data class PlayerSearchState(
    val isLoading: Boolean = false,
    val data: List<Players>? = null,
    val error: String = ""
)