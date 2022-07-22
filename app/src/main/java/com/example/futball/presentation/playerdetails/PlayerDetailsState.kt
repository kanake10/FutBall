package com.example.futball.presentation.playerdetails


import com.example.futball.domain.models.PlayerDetails

data class PlayerDetailsState(
    val isLoading: Boolean = false,
    val data: PlayerDetails? = null,
    val error: String = ""
) {
}
