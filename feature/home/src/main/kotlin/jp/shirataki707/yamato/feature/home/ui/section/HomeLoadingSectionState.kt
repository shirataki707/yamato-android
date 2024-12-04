package jp.shirataki707.yamato.feature.home.ui.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data object HomeLoadingSectionState : HomeContentSectionState

@Composable
internal fun rememberHomeLoadingSectionState(): HomeLoadingSectionState {
    return remember {
        HomeLoadingSectionState
    }
}
