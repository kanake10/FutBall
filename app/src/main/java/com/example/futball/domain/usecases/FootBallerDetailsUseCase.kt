package com.example.futball.domain.usecases

import com.example.futball.core.Resource
import com.example.futball.data.mapper.toPlayer
import com.example.futball.domain.models.Player
import com.example.futball.domain.repository.FutBallRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FootBallerDetailsUseCase @Inject constructor(private val repository: FutBallRepository) {

    operator fun invoke(id: String): Flow<Resource<List<Player>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getPlayerDetails(id)
            val domainData =
                if (!data.player.isNullOrEmpty()) data.player.map { it -> it.toPlayer() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }


}