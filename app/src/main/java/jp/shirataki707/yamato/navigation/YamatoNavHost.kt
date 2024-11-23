package jp.shirataki707.yamato.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import jp.shirataki707.yamato.feature.catalog.navigation.catalogPage
import jp.shirataki707.yamato.feature.home.detail.navigation.detailPage
import jp.shirataki707.yamato.feature.home.detail.navigation.navigateToDetail
import jp.shirataki707.yamato.feature.home.main.navigation.HOME_ROUTE
import jp.shirataki707.yamato.feature.home.main.navigation.homePage
import jp.shirataki707.yamato.feature.home.main.navigation.navigateToHome
import jp.shirataki707.yamato.feature.map.navigation.mapPage
import jp.shirataki707.yamato.feature.map.navigation.navigateToMap
import jp.shirataki707.yamato.ui.YamatoAppState

@Composable
fun YamatoNavHost(
    appState: YamatoAppState,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homePage()
        mapPage()
        catalogPage()
    }

}