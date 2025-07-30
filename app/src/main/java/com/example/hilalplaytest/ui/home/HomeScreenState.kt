package com.example.hilalplaytest.ui.home

import com.example.hilalplaytest.domain.model.Menu
import com.example.hilalplaytest.domain.model.MenuTap
import com.example.hilalplaytest.ui.home.sort_options.ClearSorter
import com.example.hilalplaytest.ui.home.sort_options.SortOption

data class HomeScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val hintMessage: String? = null,
    val data: Menu = emptyList<MenuTap>(),
    val selectedTap: MenuTap = MenuTap("",emptyList()),
    val selectedSorter: SortOption = ClearSorter()
)