@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.hilalplaytest.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hilalplaytest.domain.model.MenuItem
import com.example.hilalplaytest.domain.model.MenuTap
import com.example.hilalplaytest.ui.home.components.AppTopAppBar
import com.example.hilalplaytest.ui.home.components.CategoryTabs
import com.example.hilalplaytest.ui.home.components.MenuItemsCards
import com.example.hilalplaytest.ui.home.components.SortOptionsRow
import com.example.hilalplaytest.ui.home.sort_options.SortByAvailability
import com.example.hilalplaytest.ui.home.sort_options.SortByIsHot
import com.example.hilalplaytest.ui.home.sort_options.SortByPrice
import com.example.hilalplaytest.ui.home.sort_options.SortByRatting
import com.example.hilalplaytest.ui.home.sort_options.SortByVegan
import com.example.hilalplaytest.ui.home.sort_options.SortOption
import kotlin.collections.listOf


@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val state = viewModel.menuState
    val isConnected = viewModel.isConnected.collectAsState()

    RestaurantMenu(
        homeScreenState = state,
        isConnectedState = isConnected,
        onOptionSelected = {
            viewModel.filterBySorter(it)
        },
        onPullToRefresh = {
            viewModel.refreshMenu()
        },
        onFavoriteClick = {
            viewModel.onChangeFavoriteState(it)
        },
        onCategorySelected = {
            viewModel.filterByCategory(it)
        }
    )
}


@Composable
fun RestaurantMenu(
    homeScreenState: HomeScreenState,
    isConnectedState: State<Boolean>,
    onCategorySelected: (MenuTap) -> Unit,
    onOptionSelected: (SortOption) -> Unit,
    onPullToRefresh: () -> Unit,
    onFavoriteClick: (MenuItem) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {


        AppTopAppBar()

        CategoryTabs(
            categories = homeScreenState.data,
            selectedCategory = homeScreenState.selectedTap,
            onCategorySelected = onCategorySelected
        )

        SortOptionsRow(
            sortOptions = listOf(
                SortByPrice(),
                SortByRatting(),
                SortByAvailability(),
                SortByIsHot(),
                SortByVegan(),
            ),
            selectedOption = homeScreenState.selectedSorter,
            onOptionSelected = onOptionSelected
        )
        if (isConnectedState.value.not()) {
            Text(
                "offline data", Modifier
                    .fillMaxWidth()
                    .background(Color.Yellow)
                    .padding(horizontal = 16.dp), style = TextStyle(
                    color = Color.Black
                )
            )
        }
        if (homeScreenState.errorMessage.isNullOrBlank().not()) {
            Text(
                "Error:${homeScreenState.errorMessage}", Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .padding(horizontal = 16.dp), style = TextStyle(
                    color = Color.Black
                )
            )
        }

        MenuItemsCards(homeScreenState, onRefresh = onPullToRefresh, onFavoriteClick = onFavoriteClick)

    }
}


@Preview(showBackground = true)
@Composable
fun RestaurantMenuAppPreview() {
    val isConnected = remember { mutableStateOf(false) }
    MaterialTheme {
        RestaurantMenu(
            homeScreenState = HomeScreenState(
                data = listOf<MenuTap>(
                    MenuTap("Test", emptyList()),
                    MenuTap("Test1", emptyList())
                ),
                selectedTap = MenuTap("Test", emptyList()),
                selectedSorter = SortByPrice(),
                isLoading = false,
                errorMessage = null,
            ),
            isConnectedState = isConnected,
            onOptionSelected = {
            },
            onPullToRefresh = {
            },
            onFavoriteClick = {
            },
            onCategorySelected = {
            }
        )
    }
}