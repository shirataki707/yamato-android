package jp.shirataki707.yamato.feature.map.ui.section

import androidx.compose.runtime.Composable
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import jp.shirataki707.yamato.feature.map.model.MapResources
import jp.shirataki707.yamato.feature.map.ui.JapanMapConfiguration

internal data class MapLoadedSectionState(
    val mapResources: MapResources,
    val mapViewportState: MapViewportState,
) : MapContentSectionState

@Composable
internal fun rememberMapLoadedSectionState(
    mapResources: MapResources,
): MapLoadedSectionState {
    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            center(JapanMapConfiguration.INITIAL_CENTER)
            zoom(JapanMapConfiguration.INITIAL_ZOOM)
        }
    }
    return MapLoadedSectionState(
        mapResources = mapResources,
        mapViewportState = mapViewportState,
    )
}