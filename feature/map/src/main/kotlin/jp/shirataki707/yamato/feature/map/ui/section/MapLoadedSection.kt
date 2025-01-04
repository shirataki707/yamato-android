package jp.shirataki707.yamato.feature.map.ui.section

import androidx.compose.runtime.Composable
import jp.shirataki707.yamato.feature.map.ui.component.JapanMountainMap

@Composable
internal fun MapLoadedSection(
    sectionState: MapLoadedSectionState,
) {
    JapanMountainMap(
        mapState = sectionState.mapState,
        mapViewportState = sectionState.mapViewportState,
        mountains = sectionState.mapResources.mountains,
        mountainIcon = sectionState.mountainIcon,
    )
}