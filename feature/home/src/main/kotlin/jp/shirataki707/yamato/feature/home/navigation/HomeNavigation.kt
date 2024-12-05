package jp.shirataki707.yamato.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import jp.shirataki707.yamato.core.model.data.Video.VideoBlockInfo
import jp.shirataki707.yamato.feature.home.ui.HomePageHost
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data object HomeBaseRoute

fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(route = HomeRoute, navOptions)
}

fun NavGraphBuilder.homePage(
    onNavigateToDetail: (VideoBlockInfo) -> Unit,
    detailDestination: NavGraphBuilder.() -> Unit,
) {
    navigation<HomeBaseRoute>(startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomePageHost(onNavigateToDetail)
        }
        detailDestination()
    }
}
