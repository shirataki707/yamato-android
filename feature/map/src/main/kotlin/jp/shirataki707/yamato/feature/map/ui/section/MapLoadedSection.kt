package jp.shirataki707.yamato.feature.map.ui.section

import androidx.compose.runtime.Composable
import jp.shirataki707.yamato.feature.map.ui.component.JapanMap

@Composable
internal fun MapLoadedSection(
    sectionState: MapLoadedSectionState,
) {
    JapanMap(mapViewportState = sectionState.mapViewportState)
}