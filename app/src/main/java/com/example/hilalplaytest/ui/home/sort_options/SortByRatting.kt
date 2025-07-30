package com.example.hilalplaytest.ui.home.sort_options

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hilalplaytest.domain.model.MenuItem

class SortByRatting() : SortOption {
    override fun sort(items: List<MenuItem>): List<MenuItem> {
        return items.sortedBy { it.rating }.reversed()
    }

    override val name: String
        get() = "Ratting"
    override val icon: ImageVector
        get() = Icons.Outlined.Star

}