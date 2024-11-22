package jp.shirataki707.yamato.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import jp.shirataki707.yamato.feature.home.navigation.HOME_ROUTE
import jp.shirataki707.yamato.feature.home.navigation.navigateToHome
import jp.shirataki707.yamato.feature.mountain.navigation.MOUNTAIN_ROUTE
import jp.shirataki707.yamato.feature.mountain.navigation.navigateToMountain
import jp.shirataki707.yamato.navigation.TopLevelDestination

@Stable
data class YamatoAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HOME_ROUTE -> TopLevelDestination.HOME
            MOUNTAIN_ROUTE -> TopLevelDestination.BOOK
//            INTERESTS_ROUTE -> INTERESTS
            else -> null
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
                TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
                TopLevelDestination.BOOK -> navController.navigateToMountain(topLevelNavOptions)
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

