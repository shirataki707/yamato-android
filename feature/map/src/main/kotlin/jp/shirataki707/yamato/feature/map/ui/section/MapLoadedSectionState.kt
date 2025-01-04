package jp.shirataki707.yamato.feature.map.ui.section

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.mapbox.maps.extension.compose.MapState
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.rememberMapState
import jp.shirataki707.feature.map.R
import jp.shirataki707.yamato.core.common.utils.ResourceUtils
import jp.shirataki707.yamato.feature.map.model.MapResources
import jp.shirataki707.yamato.feature.map.ui.JapanMapConfiguration

internal data class MapLoadedSectionState(
    val mapState: MapState,
    val mapViewportState: MapViewportState,
    val mapResources: MapResources,
    val mountainIcon: Bitmap,
) : MapContentSectionState

@Composable
internal fun rememberMapLoadedSectionState(
    mapResources: MapResources,
): MapLoadedSectionState {
    val context = LocalContext.current

    val mapState = rememberMapState().apply {
        gesturesSettings = gesturesSettings.toBuilder().setRotateEnabled(false).build()
    }

    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            center(JapanMapConfiguration.INITIAL_CENTER)
            zoom(JapanMapConfiguration.INITIAL_ZOOM)
        }
    }

    val mountainIcon = remember {
        ResourceUtils.vectorToBitmap(
            context = context,
            vectorResId = R.drawable.ic_mountain,
        )
    }

    return remember(
        mapState,
        mapViewportState,
        mapResources,
        mountainIcon,
    ) {
        MapLoadedSectionState(
            mapState = mapState,
            mapViewportState = mapViewportState,
            mapResources = mapResources,
            mountainIcon = mountainIcon,
        )
    }
}