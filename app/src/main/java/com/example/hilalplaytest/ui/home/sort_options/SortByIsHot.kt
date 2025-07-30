package com.example.hilalplaytest.ui.home.sort_options

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hilalplaytest.domain.model.MenuItem
import com.example.hilalplaytest.ui.icons.Local_fire_department

class SortByIsHot() : SortOption {
    override fun sort(items: List<MenuItem>): List<MenuItem> {
        return items.sortedBy { it.hot }.reversed()
    }

    override val name: String
        get() = "Hot"
    override val icon: ImageVector
        get() = Local_fire_department

}