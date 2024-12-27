package jp.shirataki707.yamato.feature.map.ui.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data object MapLoadingSectionState : MapContentSectionState

@Composable
internal fun rememberMapLoadingSectionState(): MapLoadingSectionState {
    return remember {
        MapLoadingSectionState
    }
}