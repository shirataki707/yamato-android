package jp.shirataki707.yamato.feature.map.ui

import com.mapbox.geojson.Point
import com.mapbox.maps.CameraBoundsOptions
import com.mapbox.maps.CoordinateBounds

internal object JapanMapConfiguration {
    val INITIAL_CENTER: Point = Point.fromLngLat(137.0, 40.0)
    const val INITIAL_ZOOM = 4.0
    const val STYLE_URL = "mapbox://styles/shirataki/clyrd9sja002301r07h738h8k"
    val CAMERA_BOUNDS_OPTIONS: CameraBoundsOptions = CameraBoundsOptions.Builder()
        .bounds(
            CoordinateBounds(
                Point.fromLngLat(125.0, 20.0),
                Point.fromLngLat(150.0, 55.0),
            ),
        )
        .minZoom(4.0)
        .build()
}