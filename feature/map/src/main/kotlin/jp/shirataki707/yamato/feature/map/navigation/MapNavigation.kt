package jp.shirataki707.yamato.feature.map.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jp.shirataki707.yamato.feature.map.ui.MapPageHost

const val MAP_ROUTE = "Map"

fun NavController.navigateToMap(navOptions: NavOptions) {
    navigate(route = MAP_ROUTE, navOptions = navOptions)
}

fun NavGraphBuilder.mapPage() {
    composable(route = MAP_ROUTE) {
        MapPageHost()
    }
}