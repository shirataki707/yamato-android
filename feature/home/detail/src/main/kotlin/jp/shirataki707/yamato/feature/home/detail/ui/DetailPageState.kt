package jp.shirataki707.yamato.feature.home.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal class DetailPageState(
    val keyword: String,
)

@Composable
internal fun rememberDetailPageState(
    detailPageViewModel: DetailPageViewModel,
    keyword: String,
): DetailPageState {
    return remember(keyword) {
        DetailPageState(
            keyword = keyword,
        )
    }
}