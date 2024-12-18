package jp.shirataki707.yamato.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import jp.shirataki707.yamato.feature.catalog.navigation.catalogPage
import jp.shirataki707.yamato.feature.home.navigation.HomeBaseRoute
import jp.shirataki707.yamato.feature.home.navigation.homePage
import jp.shirataki707.yamato.feature.map.navigation.mapPage
import jp.shirataki707.yamato.feature.video.navigation.navigateToVideo
import jp.shirataki707.yamato.feature.video.navigation.videoPage
import jp.shirataki707.yamato.ui.YamatoAppState

@Composable
fun YamatoNavHost(
    appState: YamatoAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HomeBaseRoute,
        modifier = modifier,
    ) {
        homePage(onNavigateToDetail = navController::navigateToVideo) {
            videoPage(
                onBackClick = navController::popBackStack,
            )
        }
        mapPage()
        catalogPage()
    }
}
