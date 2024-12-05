package jp.shirataki707.yamato.feature.video.ui.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data object VideoLoadingSectionState : VideoContentSectionState

@Composable
internal fun rememberVideoLoadingSectionState(): VideoLoadingSectionState {
    return remember {
        VideoLoadingSectionState
    }
}
