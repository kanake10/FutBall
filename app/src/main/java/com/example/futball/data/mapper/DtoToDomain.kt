package com.example.futball.data.mapper

import com.example.futball.data.dto.PlayerDetailsDto
import com.example.futball.domain.models.PlayerDetails
import com.example.futball.domain.models.Players


fun PlayerDetailsDto.toPlayer(): PlayerDetails {
    return PlayerDetails(
        strNationality = strNationality,
        strPlayer = strPlayer,
        strAgent = strAgent,
        strBirthLocation = strBirthLocation,
        strDescriptionEN = strDescriptionEN,
        strGender = strGender,
        strKit = strKit,
        strNumber = strNumber,
        strPosition = strPosition,
        strRender = strRender.toString(),
        strSide = strSide,
        strSigning = strSigning,
        strTeam = strTeam,
        strTeam2 = strTeam2,
        strWage = strWage
    )
}




fun PlayerDetailsDto.toPlayers(): Players {
    return Players(
        strRender = strRender.toString(),
        idPlayer = idPlayer,
        strNationality = strNationality,
        strPlayer = strPlayer
    )
}