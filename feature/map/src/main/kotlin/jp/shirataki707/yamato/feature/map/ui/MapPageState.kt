package jp.shirataki707.yamato.feature.map.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import jp.shirataki707.yamato.feature.map.ui.section.MapContentSectionState
import jp.shirataki707.yamato.feature.map.ui.section.rememberMapInitialSectionState
import jp.shirataki707.yamato.feature.map.ui.section.rememberMapLoadedSectionState
import jp.shirataki707.yamato.feature.map.ui.section.rememberMapLoadingSectionState

internal data class MapPageState(
    val contentSectionState: MapContentSectionState,
)

@Composable
internal fun rememberMapPageState(
    mapPageViewModel: MapPageViewModel,
): MapPageState {
    val isInitialLoading = mapPageViewModel.isLoading
    val mapResources = mapPageViewModel.mapResources

    val contentSectionState = when {
        isInitialLoading -> rememberMapLoadingSectionState()
        mapResources is ParcelableResult.Success -> {
            rememberMapLoadedSectionState(
                mapResources = mapResources.result,
            )
        }

        else -> {
            rememberMapInitialSectionState(
                mapResources = mapResources,
                initialLoadIfNeeded = mapPageViewModel::initialLoadIfNeeded,
            )
        }
    }

    return remember(contentSectionState) {
        MapPageState(
            contentSectionState = contentSectionState,
        )
    }
}
