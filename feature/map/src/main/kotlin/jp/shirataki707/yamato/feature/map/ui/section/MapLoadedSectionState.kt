package jp.shirataki707.yamato.feature.map.ui.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.mapbox.maps.extension.compose.MapState
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.rememberMapState
import com.mapbox.maps.extension.style.expressions.dsl.generated.pitch
import jp.shirataki707.yamato.feature.map.model.MapResources
import jp.shirataki707.yamato.feature.map.ui.JapanMapConfiguration

internal data class MapLoadedSectionState(
    val mapState: MapState,
    val mapViewportState: MapViewportState,
    val mapResources: MapResources,
) : MapContentSectionState

@Composable
internal fun rememberMapLoadedSectionState(
    mapResources: MapResources,
): MapLoadedSectionState {
    val mapState = rememberMapState().apply {
        gesturesSettings = gesturesSettings.toBuilder().setRotateEnabled(false).build()
    }
    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            center(JapanMapConfiguration.INITIAL_CENTER)
            zoom(JapanMapConfiguration.INITIAL_ZOOM)
        }
    }

    return remember(
        mapState,
        mapViewportState,
        mapResources,
    ) {
        MapLoadedSectionState(
            mapState = mapState,
            mapViewportState = mapViewportState,
            mapResources = mapResources,
        )
    }
}