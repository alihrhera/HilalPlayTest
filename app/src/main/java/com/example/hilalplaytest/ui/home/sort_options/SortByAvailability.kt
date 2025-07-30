package com.example.hilalplaytest.ui.home.sort_options

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hilalplaytest.domain.model.MenuItem

class SortByAvailability() : SortOption {
    override fun sort(items: List<MenuItem>): List<MenuItem> {
        return items.sortedBy { it.isAvailable }.reversed()
    }

    override val name: String
        get() = "Availability"
    override val icon: ImageVector
        get() = Icons.Default.CheckCircle

}