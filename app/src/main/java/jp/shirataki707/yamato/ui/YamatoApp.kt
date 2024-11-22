package jp.shirataki707.yamato.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import jp.shirataki707.yamato.core.designsystem.component.YamatoBackground
import jp.shirataki707.yamato.core.designsystem.component.YamatoGradientBackground
import jp.shirataki707.yamato.core.designsystem.component.YamatoNavigationSuiteScaffold
import jp.shirataki707.yamato.core.designsystem.theme.LocalGradientColors
import jp.shirataki707.yamato.navigation.TopLevelDestination
import jp.shirataki707.yamato.navigation.YamatoNavHost

@Composable
fun YamatoAppHost(
    appState: YamatoAppState,
    modifier: Modifier = Modifier,
) {
    YamatoBackground(modifier = modifier) {
        YamatoGradientBackground(
            gradientColors = LocalGradientColors.current,
//            gradientColors = if (shouldShowGradientBackground) {
//                LocalGradientColors.current
//            } else {
//                GradientColors()
//            },
        ) {
            YamatoApp(
                appState = appState,
            )
        }

    }
}

@Composable
internal fun YamatoApp(
    appState: YamatoAppState,
) {
    val currentDestination = appState.currentDestination

    YamatoNavigationSuiteScaffold(
        navigationSuiteItems = {
            appState.topLevelDestinations.forEach { destination ->
                val selected = currentDestination
                    .isTopLevelDestinationInHierarchy(destination)
                item(
                    selected = selected,
                    onClick = { appState.navigateToTopLevelDestination(destination) },
                    icon = {
                        Icon(
                            imageVector = destination.unselectedIcon,
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = destination.selectedIcon,
                            contentDescription = null,
                        )
                    },
                    label = { destination.iconText },
                    modifier =
                    Modifier
                        .testTag("YamatoNavItem")
                )
            }

        }
    ) {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                YamatoNavHost(appState = appState)
            }

        }
    }

}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false