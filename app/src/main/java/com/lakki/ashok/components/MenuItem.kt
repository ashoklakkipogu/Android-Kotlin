package com.lakki.ashok.components

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: String,
    val name: String,
    val icon: ImageVector,
    val contentDesc: String
)
