package jp.shirataki707.yamato.feature.mountain.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jp.shirataki707.yamato.feature.mountain.ui.MountainPageHost

const val MOUNTAIN_ROUTE = "Mountain"

fun NavController.navigateToMountain(navOptions: NavOptions) {
    navigate(route = MOUNTAIN_ROUTE, navOptions = navOptions)
}

fun NavGraphBuilder.mountainPage() {
    composable(route = MOUNTAIN_ROUTE) {
        MountainPageHost()
    }
}
