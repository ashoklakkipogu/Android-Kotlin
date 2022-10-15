package com.lakki.ashok.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val router: String, val title: String, val icon: ImageVector) {
    object Home : Screens(
        router = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Settings :
        Screens(
            router = "settings",
            title = "Settings",
            icon = Icons.Default.Settings
        )

    object Help : Screens(
        router = "help",
        title = "Help",
        icon = Icons.Default.Info
    )
}
