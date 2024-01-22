package com.zee.compose_ui_examples.ui.custom

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zee.compose_ui_examples.ui.theme.iconColor

@Composable
fun MIconButton(
    modifier: Modifier = Modifier, onClick: () -> Unit = {},
    @DrawableRes iconResId: Int,
    iconTint: Color = iconColor,

) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = iconTint
        )
    }
}

@Composable
fun MIcon(
    @DrawableRes iconResId: Int,
    iconTint: Color = iconColor,
    modifier: Modifier = Modifier, onClick: (() -> Unit)? = null,
) {
    var newModifier = modifier
    if (onClick != null) {
        newModifier = modifier.clickable { onClick.invoke() }
    }
    Icon(
        modifier = newModifier.padding(10.dp),
        painter = painterResource(id = iconResId),
        contentDescription = null,
        tint = iconTint
    )
}