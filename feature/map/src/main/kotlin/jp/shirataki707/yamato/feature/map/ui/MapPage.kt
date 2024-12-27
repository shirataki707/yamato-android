package jp.shirataki707.yamato.feature.map.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import jp.shirataki707.yamato.feature.map.ui.section.MapInitialSection
import jp.shirataki707.yamato.feature.map.ui.section.MapInitialSectionState
import jp.shirataki707.yamato.feature.map.ui.section.MapLoadedSection
import jp.shirataki707.yamato.feature.map.ui.section.MapLoadedSectionState
import jp.shirataki707.yamato.feature.map.ui.section.MapLoadingSection
import jp.shirataki707.yamato.feature.map.ui.section.MapLoadingSectionState

@Composable
internal fun MapPageHost(
    mapPageViewModel: MapPageViewModel = hiltViewModel(),
) {
    val mapPageState = rememberMapPageState(
        mapPageViewModel = mapPageViewModel,
    )

    LaunchedEffect(mapPageViewModel) {
        mapPageViewModel.initialLoadIfNeeded()
    }

    MapPage(
        mapPageState = mapPageState,
    )
}

@Composable
private fun MapPage(
    mapPageState: MapPageState,
) {
    when (val sectionState = mapPageState.contentSectionState) {
        is MapInitialSectionState -> {
            MapInitialSection(
                sectionState = sectionState,
            )
        }

        is MapLoadingSectionState -> {
            MapLoadingSection(
                sectionState = sectionState,
            )
        }

        is MapLoadedSectionState -> {
            MapLoadedSection(
                sectionState = sectionState,
            )
        }
    }
}
