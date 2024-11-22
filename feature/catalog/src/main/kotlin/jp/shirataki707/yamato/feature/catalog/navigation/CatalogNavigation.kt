package jp.shirataki707.yamato.feature.catalog.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import jp.shirataki707.yamato.feature.catalog.ui.CatalogPageHost

const val CATALOG_ROUTE = "Catalog"

fun NavController.navigateToCatalog(navOptions: NavOptions) {
    navigate(route = CATALOG_ROUTE, navOptions = navOptions)
}

fun NavGraphBuilder.catalogPage() {
    composable(route = CATALOG_ROUTE) {
        CatalogPageHost()
    }
}