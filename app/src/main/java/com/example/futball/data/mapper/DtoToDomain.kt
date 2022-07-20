package com.example.futball.data.mapper

import com.example.futball.data.dto.PlayerDto
import com.example.futball.domain.models.Player
import com.example.futball.domain.models.Players

fun PlayerDto.toPlayer():Player{
    return Player(
        strNationality,strPlayer,strRender,
        strTeam,strTeam2,strNumber,strSigning,
        strWage,strKit,strAgent,strBirthLocation,strDescriptionEN,
        strGender,strSide,strPosition,
    )
}

fun PlayerDto.toPlayers(): Players {
    return Players(
        idPlayer, strNationality, strPlayer, strRender
    )
}