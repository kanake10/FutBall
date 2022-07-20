package com.example.futball.data.repository

import com.example.futball.data.api.FutBallApi
import com.example.futball.data.dto.PlayersDto
import com.example.futball.domain.repository.FutBallRepository
import javax.inject.Inject

class FutBallRepositoryImpl @Inject constructor(
    private val futBallApi: FutBallApi
): FutBallRepository {
    override suspend fun getSearchPlayers(p: String): PlayersDto {
        return futBallApi.searchPlayers(p)
    }
    override suspend fun getPlayerDetails(id: String): PlayersDto {
        return futBallApi.getPlayersDetails(id)
    }

}