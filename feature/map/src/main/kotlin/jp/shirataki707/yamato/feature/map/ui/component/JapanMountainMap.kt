package jp.shirataki707.yamato.feature.map.ui.component

import android.graphics.Bitmap
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapState
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotationGroup
import com.mapbox.maps.extension.compose.style.MapStyle
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import jp.shirataki707.yamato.core.model.data.Mountain
import jp.shirataki707.yamato.feature.map.ui.JapanMapConfiguration

@Composable
internal fun JapanMountainMap(
    mapState: MapState,
    mapViewportState: MapViewportState,
    mountains: List<Mountain>,
    mountainIcon: Bitmap,
    modifier: Modifier = Modifier,
    mapConfig: JapanMapConfiguration = JapanMapConfiguration,
) {
    MapboxMap(
        mapState = mapState,
        mapViewportState = mapViewportState,
        style = { MapStyle(style = mapConfig.STYLE_URL) },
        modifier = modifier.fillMaxSize(),
    ) {
        MapEffect(Unit) { mapView ->
            mapView.apply {
                mapboxMap.setBounds(mapConfig.CAMERA_BOUNDS_OPTIONS)
            }
        }

        PointAnnotationGroup(
            annotations = mountains.map { mountain ->
                PointAnnotationOptions()
                    .withPoint(
                        Point.fromLngLat(
                            mountain.longitude,
                            mountain.latitude,
                        ),
                    )
                    .withIconImage(mountainIcon)
                    .withTextField(mountain.name)
                    .withTextOffset(listOf(0.0, 1.5))
                    .withTextSize(15.0)
            },
        )
    }
}
