package jp.shirataki707.yamato.feature.home.ui.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import jp.shirataki707.yamato.feature.home.model.VideoResources

internal data class HomeInitialSectionState(
    val videoResources: ParcelableResult<VideoResources>?,
    val initialLoadIfNeeded: () -> Unit,
) : HomeContentSectionState

@Composable
internal fun rememberHomeInitialSectionState(
    videoResources: ParcelableResult<VideoResources>?,
    initialLoadIfNeeded: () -> Unit,
): HomeInitialSectionState {
    return remember(
        videoResources,
        initialLoadIfNeeded,
    ) {
        HomeInitialSectionState(
            videoResources = videoResources,
            initialLoadIfNeeded = initialLoadIfNeeded,
        )
    }
}
