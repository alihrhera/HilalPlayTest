package com.example.hilalplaytest.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilalplaytest.domain.model.Menu
import com.example.hilalplaytest.domain.model.MenuItem
import com.example.hilalplaytest.domain.model.MenuTap
import com.example.hilalplaytest.domain.usecase.GetMenuUsecase
import com.example.hilalplaytest.domain.usecase.RefreshMenuUsecase
import com.example.hilalplaytest.ui.home.sort_options.SortOption
import com.example.hilalplaytest.core.BaseResponse
import com.example.hilalplaytest.domain.usecase.UpdateItemUsecase
import com.example.util.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getMenuUsecase: GetMenuUsecase,
    private val refreshMenuUsecase: RefreshMenuUsecase,
    private val updateItemUsecase: UpdateItemUsecase,
    networkMonitor: NetworkMonitor
) : ViewModel() {

    var menuState by mutableStateOf<HomeScreenState>(HomeScreenState())
        private set

    val isConnected: StateFlow<Boolean> = networkMonitor.networkStatus

    private fun getMenu() {
        viewModelScope.launch {
            getMenuUsecase().collect { response ->
                updateState(
                    when (response) {
                        is BaseResponse.Error -> HomeScreenState(errorMessage = response.throwable.message)
                        is BaseResponse.Loading -> HomeScreenState(isLoading = true)
                        is BaseResponse.Success<Menu> -> HomeScreenState(
                            data = response.data,
                            selectedTap = response.data.firstOrNull() ?: MenuTap("", emptyList())
                        )
                    }
                )
            }
        }
    }

    init {
        getMenu()
    }

    fun filterByCategory(selectedTap: MenuTap) = if (menuState.selectedSorter.name.isNotBlank()) {
        val items = selectedTap.items
        viewModelScope.launch(Dispatchers.Default) {
            updateState(
                menuState.copy(
                    selectedTap = selectedTap.copy(
                        items = sortBy(menuState.selectedSorter, items)
                    )
                )
            )
        }
    } else
        updateState(
            menuState.copy(selectedTap = selectedTap, isLoading = false)
        )

    fun filterBySorter(sortOption: SortOption) {
        val sortedList = mutableListOf<MenuItem>()
        menuState.selectedTap.items.let { sortedList.addAll(it) }
        viewModelScope.launch(Dispatchers.Default) {
            updateState(
                menuState.copy(
                    selectedSorter = sortOption,
                    selectedTap = menuState.selectedTap.copy(
                        items = sortBy(sortOption, menuState.selectedTap.items)
                    ),
                )
            )
        }
    }


    private suspend fun sortBy(sortOption: SortOption, items: List<MenuItem>): List<MenuItem> {
        return viewModelScope.async {
            sortOption.sort(items)
        }.await()
    }


    fun refreshMenu() {
        viewModelScope.launch {
            refreshMenuUsecase().collect { response ->
                updateState(
                    when (response) {
                        is BaseResponse.Error -> HomeScreenState(errorMessage = response.throwable.message)
                        is BaseResponse.Loading -> HomeScreenState(isLoading = true)
                        is BaseResponse.Success<Menu> -> HomeScreenState(
                            data = response.data,
                            selectedTap = response.data.firstOrNull() ?: MenuTap("", emptyList())
                        )
                    }
                )
            }
        }
    }
    fun onChangeFavoriteState(item: MenuItem) {
        val toUpdate = item.copy(isFavorite = item.isFavorite.not())
        viewModelScope.launch {
            updateItemUsecase(toUpdate).collect { response ->
                updateState(
                    when (response) {
                        is BaseResponse.Error -> menuState.copy(errorMessage = response.throwable.message)
                        is BaseResponse.Loading -> menuState.copy(isLoading = true)
                        is BaseResponse.Success<MenuItem> -> {
                            val tapItems = menuState.selectedTap.copy(
                                items = menuState.selectedTap.items.map {
                                    if (it.id == response.data.id) response.data else it
                                }
                            ).items

                            menuState.copy(
                                isLoading = false,
                                selectedTap = menuState.selectedTap.copy(items=tapItems),
                                data = menuState.data.map {
                                    if (it.name == menuState.selectedTap.name) it.copy(items = tapItems) else it
                                }
                            )
                        }
                    }
                )
            }
        }
    }

    fun updateState(newState: HomeScreenState) {
        menuState = newState
    }


}