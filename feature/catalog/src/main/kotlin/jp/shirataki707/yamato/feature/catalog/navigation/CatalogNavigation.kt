package jp.shirataki707.yamato.feature.catalog.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jp.shirataki707.yamato.feature.catalog.ui.CatalogPageHost
import kotlinx.serialization.Serializable

@Serializable data object CatalogRoute

fun NavController.navigateToCatalog(navOptions: NavOptions) {
    navigate(route = CatalogRoute, navOptions = navOptions)
}

fun NavGraphBuilder.catalogPage() {
    composable<CatalogRoute> {
        CatalogPageHost()
    }
}
