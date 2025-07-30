package com.example.hilalplaytest.ui.home.sort_options

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hilalplaytest.domain.model.MenuItem

class ClearSorter() : SortOption {
    override fun sort(items: List<MenuItem>): List<MenuItem> {
        return items.shuffled()
    }

    override val name: String
        get() = ""
    override val icon: ImageVector
        get() = Icons.Default.Close

}