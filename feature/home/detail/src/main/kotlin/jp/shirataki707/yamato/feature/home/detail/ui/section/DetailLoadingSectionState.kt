package jp.shirataki707.yamato.feature.home.detail.ui.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data object DetailLoadingSectionState : DetailContentSectionState

@Composable
internal fun rememberDetailLoadingSectionState(): DetailLoadingSectionState {
    return remember {
        DetailLoadingSectionState
    }
}
