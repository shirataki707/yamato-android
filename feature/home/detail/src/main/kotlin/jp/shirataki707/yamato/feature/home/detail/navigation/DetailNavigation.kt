package jp.shirataki707.yamato.feature.home.detail.navigation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jp.shirataki707.yamato.feature.home.detail.ui.DetailPageHost
import jp.shirataki707.yamato.feature.home.detail.ui.DetailPageState

@VisibleForTesting
internal const val DETAIL_KEYWORD_ARG = "keyword"
const val DETAIL_ROUTE = "detail_route"

internal class DetailArgs(val keyword: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle.get<String>(DETAIL_ROUTE)))
}

fun NavController.navigateToDetail(keyword: String, navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(keyword) {
        navOptions()
    }
}

fun NavGraphBuilder.detailPage(
    keyword: String,
) {
    composable(
        route = DETAIL_ROUTE,
//        arguments = listOf(
//            navArgument(DETAIL_KEYWORD_ARG) { type = NavType.StringType },
//        ),
    ) {
        DetailPageHost(
            keyword = keyword,
        )
    }
}