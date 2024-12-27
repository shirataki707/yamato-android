package jp.shirataki707.yamato.feature.map.ui.section

import androidx.compose.runtime.Composable
import jp.shirataki707.yamato.feature.map.model.MapResources

internal data class MapLoadedSectionState(
    val mapResources: MapResources,
) : MapContentSectionState

@Composable
internal fun rememberMapLoadedSectionState(
    mapResources: MapResources,
): MapLoadedSectionState {
    return MapLoadedSectionState(
        mapResources = mapResources,
    )
}