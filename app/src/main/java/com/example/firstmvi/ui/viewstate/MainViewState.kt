package com.example.firstmvi.ui.viewstate

import com.example.firstmvi.data.model.FakeDTO

sealed class MainViewState {

     object Idle:MainViewState()
     object Loading:MainViewState()
     class Error(val message: String) : MainViewState()
     class Success(val data:List<FakeDTO>):MainViewState()


}