package com.example.hilalplaytest.ui.home.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hilalplaytest.domain.model.MenuItem
import com.example.hilalplaytest.ui.home.HomeScreenState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.MenuItemsCards(homeScreenState: HomeScreenState, onRefresh: () -> Unit, onFavoriteClick: (MenuItem) -> Unit) {
    PullToRefreshBox(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 16.dp),
        isRefreshing = homeScreenState.isLoading,
        onRefresh = {
            onRefresh()
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(homeScreenState.selectedTap.items.size) { item ->
                MenuItemCard(
                    item = homeScreenState.selectedTap.items[item],
                    onClick = { /* Handle item click */ },
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}