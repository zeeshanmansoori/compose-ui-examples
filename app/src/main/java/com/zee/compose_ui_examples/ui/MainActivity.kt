package com.zee.compose_ui_examples.ui

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zee.compose_ui_examples.data.DrawerItem
import com.zee.compose_ui_examples.ui.dashboard.DashboardScreen
import com.zee.compose_ui_examples.ui.dashboard.NavigationUi
import com.zee.compose_ui_examples.ui.player.PlayerScreen
import com.zee.compose_ui_examples.ui.statusProgress.StatusProgressScreen
import com.zee.compose_ui_examples.ui.theme.AMusicPlayerTheme
import com.zee.compose_ui_examples.ui.wave.WaveScreen

class MainActivity : ComponentActivity() {

    companion object {
        const val statusBarHeight = 40
        @Composable
        fun gradientColors() =
            listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current

            DisposableEffect(key1 = true) {


                if (context is Activity) {
                    context.window.setFlags(
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                    )
                }

                onDispose {
                    if (context is Activity) {
                        context.window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                    }
                }
            }

            AMusicPlayerTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()


                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {


                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {

                        NavHost(
                            navController,
                            startDestination = DrawerItem.Player.id,
                            modifier = Modifier.fillMaxSize()
                        ) {

                            composable(DrawerItem.DashBoard.id) {
                                DashboardScreen(navController)
                            }

                            composable(DrawerItem.Player.id) {
                                PlayerScreen()
                            }

                            composable(DrawerItem.Wave.id) {
                                WaveScreen()
                            }

                            composable(DrawerItem.ProgressBar.id) {
                                StatusProgressScreen()
                            }
                        }

                        NavigationUi(navController)


                    }

                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AMusicPlayerTheme {
        Greeting("Android")
    }
}