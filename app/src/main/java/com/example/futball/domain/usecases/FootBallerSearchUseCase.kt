package com.example.futball.domain.usecases

import com.example.futball.core.Resource
import com.example.futball.data.mapper.toPlayers
import com.example.futball.domain.models.Players
import com.example.futball.domain.repository.FutBallRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FootBallerSearchUseCase @Inject constructor(private val repository: FutBallRepository) {


    operator fun invoke(p: String): Flow<Resource<List<Players>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getSearchPlayers(p)
            val domainData =
                if (data.player != null) data.player.map { it -> it.toPlayers() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }


}