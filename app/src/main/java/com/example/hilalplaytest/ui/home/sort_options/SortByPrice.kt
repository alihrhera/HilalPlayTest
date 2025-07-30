package com.example.hilalplaytest.ui.home.sort_options

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hilalplaytest.domain.model.MenuItem
import com.example.hilalplaytest.ui.icons.Price_change

class SortByPrice() : SortOption {
    override fun sort(items: List<MenuItem>): List<MenuItem> {
        return items.sortedBy { it.price }
    }

    override val name: String
        get() = "Price"
    override val icon: ImageVector
        get() = Price_change

}