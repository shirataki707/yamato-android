package jp.shirataki707.yamato.feature.map.ui.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import jp.shirataki707.yamato.feature.map.model.MapResources

internal data class MapInitialSectionState(
    val mapResources: ParcelableResult<MapResources>?,
    val initialLoadIfNeeded: () -> Unit,
) : MapContentSectionState

@Composable
internal fun rememberMapInitialSectionState(
    mapResources: ParcelableResult<MapResources>?,
    initialLoadIfNeeded: () -> Unit,
): MapInitialSectionState {
    return remember(
        mapResources,
        initialLoadIfNeeded,
    ) {
        MapInitialSectionState(
            mapResources = mapResources,
            initialLoadIfNeeded = initialLoadIfNeeded,
        )
    }
}
