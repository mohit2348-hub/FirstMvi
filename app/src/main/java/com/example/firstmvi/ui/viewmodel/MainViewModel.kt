package com.example.firstmvi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstmvi.data.repo.GetUserRepo
import com.example.firstmvi.ui.intent.MainIntent
import com.example.firstmvi.ui.viewstate.MainViewState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.math.MathContext.UNLIMITED

import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val getUserRepo: GetUserRepo) : ViewModel() {
    val mainintent: Channel<MainIntent> = Channel(Channel.UNLIMITED)
    val _state = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val state: StateFlow<MainViewState>
        get() = _state

    init {
        handleIntent()
    }

    fun handleIntent() {
        viewModelScope.launch {
            mainintent.consumeAsFlow().collect()
            {
                when (it) {
                    is MainIntent.GetUsers -> {
                        fetchUser()
                    }

                }
            }
        }
    }

    fun fetchUser() {
        viewModelScope.launch {
            _state.value = MainViewState.Loading
            try {

                _state.value = MainViewState.Success(data = getUserRepo.getPosts())
            } catch (ex: Exception) {
                _state.value = MainViewState.Error(ex.message.toString())
            }
        }
    }

}