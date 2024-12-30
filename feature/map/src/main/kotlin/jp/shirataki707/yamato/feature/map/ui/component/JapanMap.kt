package jp.shirataki707.yamato.feature.map.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.style.MapStyle
import com.mapbox.maps.plugin.gestures.gestures
import jp.shirataki707.yamato.feature.map.ui.JapanMapConfiguration

@Composable
internal fun JapanMap(
    mapViewportState: MapViewportState,
    modifier: Modifier = Modifier,
) {
    MapboxMap(
        mapViewportState = mapViewportState,
        style = { MapStyle(style = JapanMapConfiguration.STYLE_URL) },
        modifier = modifier.fillMaxSize(),
    ) {
        MapEffect(Unit) { mapView ->
            mapView.apply {
                mapboxMap.setBounds(JapanMapConfiguration.CAMERA_BOUNDS_OPTIONS)
                gestures.rotateEnabled = false
            }
        }
    }
}
