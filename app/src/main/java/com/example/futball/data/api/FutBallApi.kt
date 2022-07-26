package com.example.futball.data.api

import com.example.futball.core.Constants.PLAYER_DETAILS
import com.example.futball.core.Constants.SEARCH_PLAYER
import com.example.futball.data.dto.PlayersDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FutBallApi {

    @GET(SEARCH_PLAYER)
    suspend fun searchPlayers(@Query("p") p: String): PlayersDto

    @GET(PLAYER_DETAILS)
    suspend fun getPlayersDetails(@Path("id") id: String): PlayersDto

}