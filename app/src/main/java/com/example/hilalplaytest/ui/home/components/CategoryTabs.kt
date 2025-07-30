package com.example.hilalplaytest.ui.home.components

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hilalplaytest.domain.model.Menu
import com.example.hilalplaytest.domain.model.MenuTap

@Composable
fun CategoryTabs(
    categories: Menu,
    selectedCategory: MenuTap,
    onCategorySelected: (MenuTap) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = categories.indexOf(selectedCategory),
        containerColor = Color.White,
        contentColor = Color.Black,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            val selectedTapPosition = categories.indexOf(selectedCategory)
            if (selectedTapPosition in tabPositions.indices) {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(
                        tabPositions[selectedTapPosition]
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
            }

        }
    ) {
        categories.forEach { category ->
            Tab(
                selected = category.name === selectedCategory.name,
                onClick = {
                    onCategorySelected(category)
                },
                text = {
                    Text(
                        text = category.name,
                        fontSize = 14.sp,
                        fontWeight = if (category == selectedCategory) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryTabsPreview(

) {
    CategoryTabs(
        listOf<MenuTap>(
            MenuTap("Test", emptyList()),
            MenuTap("Test1", emptyList())
        ),
        MenuTap("Test", emptyList()),
    ) {}
}