package jp.shirataki707.yamato.feature.home.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jp.shirataki707.yamato.feature.home.main.ui.HomePageHost
import jp.shirataki707.yamato.feature.home.main.ui.HomePageState

const val HOME_ROUTE = "Home"

fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(route = HOME_ROUTE, navOptions = navOptions)
}

fun NavGraphBuilder.homePage() {
    composable(route = HOME_ROUTE) {
        HomePageHost()
    }
}