package com.example.hilalplaytest.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Vegan: ImageVector
    get() {
        if (_Vegan != null) return _Vegan!!
        
        _Vegan = ImageVector.Builder(
            name = "Vegan",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(2f, 2f)
                arcToRelative(26.6f, 26.6f, 0f, false, true, 10f, 20f)
                curveToRelative(0.9f, -6.82f, 1.5f, -9.5f, 4f, -14f)
                moveToRelative(0f, 0f)
                quadToRelative(6f, 0f, 6f, -6f)
                quadToRelative(-6f, 0f, -6f, 6f)
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(17.41f, 3.6f)
                arcToRelative(10f, 10f, 0f, true, false, 3f, 3f)
            }
        }.build()
        
        return _Vegan!!
    }

private var _Vegan: ImageVector? = null

