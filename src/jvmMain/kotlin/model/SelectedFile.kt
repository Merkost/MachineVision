package model


import androidx.compose.ui.graphics.ImageBitmap
data class SelectedFile(
    val path: String,
    val bitmap: ImageBitmap,
    val turnedGray: Boolean = false,
)
