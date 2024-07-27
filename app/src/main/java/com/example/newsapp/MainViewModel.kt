package com.example.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    var splashCondition by mutableStateOf(true)
    var startDestination by mutableStateOf(Route.AppStarNavigation.route)

    init {
        appEntryUseCases.readAppEntry().onEach {shouldStartFromHomeScreen ->
            if (shouldStartFromHomeScreen) {
                startDestination = Route.NewsNavigation.route
            }else{
                startDestination = Route.AppStarNavigation.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}