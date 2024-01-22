package com.zee.compose_ui_examples.ui.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zee.compose_ui_examples.data.DrawerItem
import com.zee.compose_ui_examples.ui.MainActivity
import kotlin.math.roundToInt


val drawerData = listOf(
    DrawerItem.DashBoard, DrawerItem.Player, DrawerItem.Wave, DrawerItem.ProgressBar
)

@Composable
fun NavigationUi(navController: NavController) {

    val uiOffset = remember {
        mutableStateOf(Offset.Unspecified)
    }


    val controllerVisibilityState = remember {
        mutableStateOf(false)
    }


    val selectedDrawer = remember {
        mutableStateOf<DrawerItem>(DrawerItem.DashBoard)
    }

    Column(modifier = Modifier
        .layout { measurable, constraints ->
            onLayout(measurable, constraints, uiOffset)
        }

        .padding(start = 20.dp)
        .fillMaxWidth(.15f)
        .pointerInput(true) {
            detectDragGestures( onDrag = { _, offset ->
                if (uiOffset.value == Offset.Unspecified) return@detectDragGestures
                uiOffset.value = uiOffset.value + offset
            })
        }
    ) {

        NavigationUiController {
            controllerVisibilityState.value = !controllerVisibilityState.value
        }

        NavigationUiItems(
            controllerVisibilityState = controllerVisibilityState,
            selectedDrawer = selectedDrawer,
            navController = navController
        )


    }

}

fun MeasureScope.onLayout(
    measurable: Measurable,
    constraints: Constraints,
    positionState: MutableState<Offset>
): MeasureResult {
    val placeable = measurable.measure(constraints)

    val height = constraints.maxHeight

    val buttonsHeight = drawerData.size * MainActivity.statusBarHeight.dp.toPx()
        .roundToInt()

    return layout(placeable.width, placeable.height) {

        if (positionState.value == Offset.Unspecified) {
            positionState.value = Offset(0f, (height - buttonsHeight) / 2f)
        }

        val position = positionState.value

        placeable.place(
            position.x.roundToInt(), position.y.roundToInt()
        )
    }
}


@Composable
fun NavigationUiController(onClick: () -> Unit) {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
        .fillMaxWidth()
        .height(15.dp)
        .background(MaterialTheme.colorScheme.secondary.copy(.4f))
        .clickable {
            onClick.invoke()
        })
}

@Composable
fun NavigationUiItems(
    controllerVisibilityState: State<Boolean>,
    selectedDrawer: MutableState<DrawerItem>,
    navController: NavController
) {
    AnimatedVisibility(visible = controllerVisibilityState.value) {
        LazyColumn(
            modifier = Modifier
                .shadow(
                    3.dp, shape = RoundedCornerShape(
                        bottomStart = 10.dp, bottomEnd = 10.dp
                    )
                )
                .clip(
                    RoundedCornerShape(
                        bottomStart = 10.dp, bottomEnd = 10.dp
                    )
                )
                .background(Color.White)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            items(drawerData) { drawerItem ->
                IconButton(onClick = {
                    if (selectedDrawer.value == drawerItem) return@IconButton
                    selectedDrawer.value = drawerItem
                    navController.navigate(selectedDrawer.value.id)
                }) {
                    val color =
                        if (selectedDrawer.value == drawerItem) MaterialTheme.colorScheme.primary else Color.Black
                    Icon(
                        painter = painterResource(id = drawerItem.iconResId),
                        contentDescription = null,
                        tint = color
                    )
                }
            }
        }
    }
}