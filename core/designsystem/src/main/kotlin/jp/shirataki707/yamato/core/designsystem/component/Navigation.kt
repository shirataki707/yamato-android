package jp.shirataki707.yamato.core.designsystem.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.shirataki707.yamato.core.designsystem.icon.YamatoIcons
import jp.shirataki707.yamato.core.designsystem.theme.YamatoTheme

@Composable
fun RowScope.YamatoNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = YamatoNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = YamatoNavigationDefaults.navigationContentColor(),
            selectedTextColor = YamatoNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = YamatoNavigationDefaults.navigationContentColor(),
            indicatorColor = YamatoNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun YamatoNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = YamatoNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content,
    )
}

@Composable
fun YamatoNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = YamatoNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = YamatoNavigationDefaults.navigationContentColor(),
            selectedTextColor = YamatoNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = YamatoNavigationDefaults.navigationContentColor(),
            indicatorColor = YamatoNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun YamatoNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = YamatoNavigationDefaults.navigationContentColor(),
        header = header,
        content = content,
    )
}

@Composable
fun YamatoNavigationSuiteScaffold(
    navigationSuiteItems: YamatoNavigationSuiteScope.() -> Unit,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    content: @Composable () -> Unit,
) {
    val layoutType = NavigationSuiteScaffoldDefaults
        .calculateFromAdaptiveInfo(windowAdaptiveInfo)
    val navigationSuiteItemColors = NavigationSuiteItemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = YamatoNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = YamatoNavigationDefaults.navigationContentColor(),
            selectedTextColor = YamatoNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = YamatoNavigationDefaults.navigationContentColor(),
            indicatorColor = YamatoNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            selectedIconColor = YamatoNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = YamatoNavigationDefaults.navigationContentColor(),
            selectedTextColor = YamatoNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = YamatoNavigationDefaults.navigationContentColor(),
            indicatorColor = YamatoNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
            selectedIconColor = YamatoNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = YamatoNavigationDefaults.navigationContentColor(),
            selectedTextColor = YamatoNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = YamatoNavigationDefaults.navigationContentColor(),
        ),
    )

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            YamatoNavigationSuiteScope(
                navigationSuiteScope = this,
                navigationSuiteItemColors = navigationSuiteItemColors,
            ).run(navigationSuiteItems)
        },
        layoutType = layoutType,
        containerColor = Color.Transparent,
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContentColor = YamatoNavigationDefaults.navigationContentColor(),
            navigationRailContainerColor = Color.Transparent,
        ),
        modifier = modifier,
    ) {
        content()
    }
}

class YamatoNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors,
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit = icon,
        label: @Composable (() -> Unit)? = null,
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                selectedIcon()
            } else {
                icon()
            }
        },
        label = label,
        colors = navigationSuiteItemColors,
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
fun YamatoNavigationBarPreview() {
    val items = listOf("Home", "Map", "Data")
    val icons = listOf(
        YamatoIcons.Home,
        YamatoIcons.Map,
        YamatoIcons.Book,
    )
    val selectedIcons = listOf(
        YamatoIcons.Home,
        YamatoIcons.Map,
        YamatoIcons.Book,
    )

    YamatoTheme {
        YamatoNavigationBar {
            items.forEachIndexed { index, item ->
                YamatoNavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun YamatoNavigationRailPreview() {
    val items = listOf("Home", "Map", "Data")
    val icons = listOf(
        YamatoIcons.Home,
        YamatoIcons.Map,
        YamatoIcons.Book,
    )
    val selectedIcons = listOf(
        YamatoIcons.Home,
        YamatoIcons.Map,
        YamatoIcons.Book,
    )

    YamatoTheme {
        YamatoNavigationRail {
            items.forEachIndexed { index, item ->
                YamatoNavigationRailItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}

object YamatoNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}
