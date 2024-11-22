package jp.shirataki707.yamato.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import jp.shirataki707.yamato.feature.catalog.navigation.catalogPage
import jp.shirataki707.yamato.feature.home.navigation.HOME_ROUTE
import jp.shirataki707.yamato.feature.home.navigation.homePage
import jp.shirataki707.yamato.feature.home.ui.HomePageHost
import jp.shirataki707.yamato.feature.map.navigation.mapPage
import jp.shirataki707.yamato.feature.mountain.navigation.MOUNTAIN_ROUTE
import jp.shirataki707.yamato.feature.mountain.navigation.mountainPage
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