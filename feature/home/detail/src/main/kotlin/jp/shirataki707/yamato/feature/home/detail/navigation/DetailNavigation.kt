package jp.shirataki707.yamato.feature.home.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import jp.shirataki707.yamato.feature.home.detail.ui.DetailPageHost
import kotlinx.serialization.Serializable

@Serializable
data class DetailRoute(val keyword: String)

fun NavController.navigateToDetail(keyword: String, navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = DetailRoute(keyword)) {
        navOptions()
    }
}

fun NavGraphBuilder.detailPage() {
    composable<DetailRoute> {
        DetailPageHost()
    }
}
