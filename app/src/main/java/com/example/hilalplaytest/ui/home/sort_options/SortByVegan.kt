package com.example.hilalplaytest.ui.home.sort_options

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hilalplaytest.domain.model.MenuItem
import com.example.hilalplaytest.ui.icons.Price_change
import com.example.hilalplaytest.ui.icons.Vegan

class SortByVegan() : SortOption {
    override fun sort(items: List<MenuItem>): List<MenuItem> {
        return items.sortedBy { it.vegan }.reversed()
    }

    override val name: String
        get() = "Vegan"
    override val icon: ImageVector
        get() = Vegan

}