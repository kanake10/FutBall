package com.example.futball.presentation.playerdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futball.core.Resource
import com.example.futball.domain.usecases.FootBallerDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlayerDetailsViewModel @Inject constructor(private val footBallerDetailsUseCase: FootBallerDetailsUseCase) :
    ViewModel() {


    private val _mealDetails = MutableStateFlow<PlayerDetailsState>(PlayerDetailsState())
    val mealDetails: StateFlow<PlayerDetailsState> = _mealDetails


    fun getFutBallDetails(id: String) {
        footBallerDetailsUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealDetails.value = PlayerDetailsState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealDetails.value = PlayerDetailsState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _mealDetails.value = PlayerDetailsState(data = it.data?.get(0))
                }
            }
        }.launchIn(viewModelScope)
    }


}