package jp.shirataki707.yamato.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import jp.shirataki707.yamato.feature.catalog.navigation.navigateToCatalog
import jp.shirataki707.yamato.feature.home.main.navigation.navigateToHome
import jp.shirataki707.yamato.feature.map.navigation.navigateToMap
import jp.shirataki707.yamato.navigation.TopLevelDestination
import jp.shirataki707.yamato.navigation.TopLevelDestination.CATALOG
import jp.shirataki707.yamato.navigation.TopLevelDestination.HOME
import jp.shirataki707.yamato.navigation.TopLevelDestination.MAP

@Stable
data class YamatoAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                currentDestination?.hasRoute(route = topLevelDestination.route) == true
            }
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                HOME -> navController.navigateToHome(topLevelNavOptions)
                MAP -> navController.navigateToMap(topLevelNavOptions)
                CATALOG -> navController.navigateToCatalog(topLevelNavOptions)
            }
        }
    }
}

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

