package jp.shirataki707.yamato.feature.map.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jp.shirataki707.yamato.feature.map.ui.MapPageHost
import kotlinx.serialization.Serializable

@Serializable data object MapRoute

fun NavController.navigateToMap(navOptions: NavOptions) =
    navigate(route = MapRoute, navOptions)

fun NavGraphBuilder.mapPage() {
    composable<MapRoute> {
        MapPageHost()
    }
}
