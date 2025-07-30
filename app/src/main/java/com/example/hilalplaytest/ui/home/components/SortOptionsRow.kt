package com.example.hilalplaytest.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hilalplaytest.ui.home.sort_options.ClearSorter
import com.example.hilalplaytest.ui.home.sort_options.SortByRatting
import com.example.hilalplaytest.ui.home.sort_options.SortOption

@Composable
fun SortOptionsRow(
    sortOptions: List<SortOption>,
    selectedOption: SortOption?,
    onOptionSelected: (SortOption) -> Unit
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .background(Color.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        sortOptions.forEach { option ->
            SortChip(
                option = option,
                isSelected = option.name == selectedOption?.name,
                onClick = { onOptionSelected(option) }
            )
        }

        val addClearTap = !selectedOption?.name.isNullOrBlank()
        if (addClearTap) {
            val clearSorter = ClearSorter()
            SortChip(
                option = clearSorter,
                isSelected = false,
                onClick = { onOptionSelected(clearSorter) }
            )
        }
    }
}

@Composable
fun SortChip(
    option: SortOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.White
        ),
        border = if (!isSelected) CardDefaults.outlinedCardBorder() else null,
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = option.icon,
                contentDescription = option.name,
                modifier = Modifier.size(16.dp),
                tint = if (isSelected) Color.White else Color.Gray
            )
            if (option.name.isNotBlank()) {
                Text(
                    text = option.name,
                    fontSize = 12.sp,
                    color = if (isSelected) Color.White else Color.Black
                )
            }
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    modifier = Modifier.size(14.dp),
                    tint = Color.White
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SortOptionsRowPreview(
) {
    SortOptionsRow(
        sortOptions = listOf<SortOption>(SortByRatting()),
        selectedOption = SortByRatting(),
    ) {}
}