package com.example.futball.presentation.playerdetails

import com.example.futball.domain.models.Player

data class PlayerDetailsState(
    val isLoading: Boolean = false,
    val data: Player? = null,
    val error: String = ""
) {
}
