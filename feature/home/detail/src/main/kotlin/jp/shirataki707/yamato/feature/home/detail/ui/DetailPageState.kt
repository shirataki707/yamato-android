package jp.shirataki707.yamato.feature.home.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal class DetailPageState()

@Composable
internal fun rememberDetailPageState(
    detailPageViewModel: DetailPageViewModel,
): DetailPageState {
    return remember {
        DetailPageState()
    }
}
