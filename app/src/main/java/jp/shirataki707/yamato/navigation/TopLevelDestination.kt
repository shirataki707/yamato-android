package jp.shirataki707.yamato.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import jp.shirataki707.yamato.core.designsystem.icon.YamatoIcons

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconText: String,
    val titleText: String,
//    val iconTextId: Int,
//    val titleTextId: Int,
) {
    HOME(
        selectedIcon = YamatoIcons.Home,
        unselectedIcon = YamatoIcons.HomeBorder,
        iconText = "Home",
        titleText = "Home",
    ),
//    MAP(
//        selectedIcon = YamatoIcons.Map,
//        unselectedIcon = YamatoIcons.MapBorder,
//        iconText = "Map",
//        titleText = "Map",
//    ),
    BOOK(
        selectedIcon = YamatoIcons.Book,
        unselectedIcon = YamatoIcons.BookBorder,
        iconText = "Book",
        titleText = "Book",
    ),
}
