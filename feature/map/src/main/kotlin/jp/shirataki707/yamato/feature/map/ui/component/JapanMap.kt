package jp.shirataki707.yamato.feature.map.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapState
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.CircleAnnotationGroup
import com.mapbox.maps.extension.compose.annotation.generated.withCircleColor
import com.mapbox.maps.extension.compose.style.MapStyle
import com.mapbox.maps.plugin.annotation.generated.CircleAnnotationOptions
import jp.shirataki707.yamato.core.model.data.Mountain
import jp.shirataki707.yamato.feature.map.ui.JapanMapConfiguration

@Composable
internal fun JapanMap(
    mountains: List<Mountain>,
    mapState: MapState,
    mapViewportState: MapViewportState,
    modifier: Modifier = Modifier,
    mapConfig: JapanMapConfiguration = JapanMapConfiguration,
) {
    MapboxMap(
        mapState = mapState,
        mapViewportState = mapViewportState,
        style = { MapStyle(style = mapConfig.STYLE_URL) },
        compass = { Compass() },
        modifier = modifier.fillMaxSize(),
    ) {
        MapEffect(Unit) { mapView ->
            mapView.apply {
                mapboxMap.setBounds(mapConfig.CAMERA_BOUNDS_OPTIONS)
            }
        }

        CircleAnnotationGroup(
            annotations = mountains.map { mountain ->
                CircleAnnotationOptions()
                    .withPoint(
                        Point.fromLngLat(
                            mountain.longitude,
                            mountain.latitude,
                        ),
                    )
                    .withCircleRadius(10.0)
                    .withCircleColor(Color.Red)
            },
        )
    }
}
