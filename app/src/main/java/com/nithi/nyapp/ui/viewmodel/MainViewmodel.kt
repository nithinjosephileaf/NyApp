package com.nithi.nyapp.ui.viewmodel

import ResponseState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithi.nyapp.services.Repository
import com.nithi.nyapp.model.NytResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(val  repository: Repository):ViewModel() {

    private val _newsResponce=MutableStateFlow<ResponseState<NytResponse>?>(null)
    val newsResponse:StateFlow<ResponseState<NytResponse>?> =_newsResponce;

    fun  getNews()=viewModelScope.launch {

        repository.getNews().collect{
            _newsResponce.value=it
        }
    }
}