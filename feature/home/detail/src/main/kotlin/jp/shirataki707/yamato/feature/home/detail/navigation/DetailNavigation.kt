package jp.shirataki707.yamato.feature.home.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import jp.shirataki707.yamato.core.common.navigation.CustomNavType
import jp.shirataki707.yamato.core.model.data.video.DetailPageConfig
import jp.shirataki707.yamato.feature.home.detail.ui.DetailPageHost
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class DetailRoute(val detailPageConfig: DetailPageConfig)

fun NavController.navigateToDetail(
    detailPageConfig: DetailPageConfig,
    navOptions: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(route = DetailRoute(detailPageConfig)) {
        navOptions()
    }
}

fun NavGraphBuilder.detailPage(
    onBackClick: () -> Unit,
) {
    composable<DetailRoute>(
        typeMap = mapOf(
            typeOf<DetailPageConfig>() to CustomNavType.nonNullableNavType<DetailPageConfig>(),
        ),
    ) { backStackEntry ->
        val detailPageConfig = backStackEntry.toRoute<DetailRoute>().detailPageConfig
        DetailPageHost(
            detailPageConfig = detailPageConfig,
            onBackClick = onBackClick,
        )
    }
}
