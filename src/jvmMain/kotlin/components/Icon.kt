package components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun Icon(icon: ImageVector, color: Color = Color.Unspecified) {
    androidx.compose.material.Icon(icon, icon.name, tint = color)
}