package com.example.futball.domain.repository

import com.example.futball.data.dto.PlayersDto

interface FutBallRepository {
    suspend fun getSearchPlayers(p:String): PlayersDto
    suspend fun getPlayerDetails(id:String): PlayersDto
}