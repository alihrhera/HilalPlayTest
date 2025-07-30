package com.example.hilalplaytest.ui.home.sort_options

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.hilalplaytest.domain.model.MenuItem

interface SortOption {
    fun sort(items: List<MenuItem>): List<MenuItem>
    val  name: String
    val  icon:ImageVector
}
