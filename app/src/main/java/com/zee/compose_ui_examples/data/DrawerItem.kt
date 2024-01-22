package com.zee.compose_ui_examples.data

import com.zee.compose_ui_examples.R

sealed class DrawerItem(
    val id: String, val title: String,
    val iconResId:Int,
) {
    data object Player : DrawerItem("player", "Music Player", iconResId = R.drawable.ic_player)
    data object Wave : DrawerItem("wave", "Wave", iconResId = R.drawable.ic_sound_wave)
    data object ProgressBar : DrawerItem("progress", "Google Go progressbar", iconResId = R.drawable.ic_download)
    data object DashBoard : DrawerItem("dashboard", "Dashboard", iconResId = R.drawable.ic_home)
}