package jp.shirataki707.yamato.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Stable
data class YamatoAppState(
    val navController: NavHostController,
)

@Composable
fun rememberYamatoAppState(
    navController: NavHostController = rememberNavController(),
): YamatoAppState {
    return remember(
        navController,
    ) {
        YamatoAppState(
            navController = navController,
        )
    }
}