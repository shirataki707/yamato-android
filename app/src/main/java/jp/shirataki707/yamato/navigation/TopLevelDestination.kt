package jp.shirataki707.yamato.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import jp.shirataki707.yamato.core.designsystem.icon.YamatoIcons
import jp.shirataki707.yamato.feature.catalog.navigation.CatalogRoute
import jp.shirataki707.yamato.feature.home.navigation.HomeBaseRoute
import jp.shirataki707.yamato.feature.home.navigation.HomeRoute
import jp.shirataki707.yamato.feature.map.navigation.MapRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconText: String,
    val titleText: String,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    HOME(
        selectedIcon = YamatoIcons.Home,
        unselectedIcon = YamatoIcons.HomeBorder,
        iconText = "Home",
        titleText = "Home",
        route = HomeRoute::class,
        baseRoute = HomeBaseRoute::class,
    ),
    MAP(
        selectedIcon = YamatoIcons.Map,
        unselectedIcon = YamatoIcons.MapBorder,
        iconText = "Map",
        titleText = "Map",
        route = MapRoute::class,
    ),
    CATALOG(
        selectedIcon = YamatoIcons.Book,
        unselectedIcon = YamatoIcons.BookBorder,
        iconText = "Catalog",
        titleText = "Catalog",
        route = CatalogRoute::class,
    ),
}
