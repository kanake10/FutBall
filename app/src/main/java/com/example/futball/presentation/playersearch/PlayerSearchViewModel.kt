package com.example.futball.presentation.playersearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futball.core.Resource
import com.example.futball.domain.usecases.FootBallerSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlayerSearchViewModel @Inject constructor(
    private val footBallerSearchUseCase: FootBallerSearchUseCase
) : ViewModel() {


    private val _mealSearchList = MutableStateFlow<PlayerSearchState>(PlayerSearchState())
    val mealSearchList: StateFlow<PlayerSearchState> = _mealSearchList


    fun getPlayersSearch(s: String) {
        footBallerSearchUseCase(s).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealSearchList.value = PlayerSearchState(isLoading = true)
                }
                is Resource.Success -> {
                    _mealSearchList.value = PlayerSearchState(data = it.data)
                }
                is Resource.Error -> {
                    _mealSearchList.value = PlayerSearchState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }


}