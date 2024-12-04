package jp.shirataki707.yamato.feature.video.ui.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import jp.shirataki707.yamato.feature.video.model.VideoResources

internal data class VideoInitialSectionState(
    val videoResources: ParcelableResult<VideoResources>?,
    val initialLoadIfNeeded: () -> Unit,
) : VideoContentSectionState

@Composable
internal fun rememberVideoInitialSectionState(
    videoResources: ParcelableResult<VideoResources>?,
    initialLoadIfNeeded: () -> Unit,
): VideoInitialSectionState {
    return remember(
        videoResources,
        initialLoadIfNeeded,
    ) {
        VideoInitialSectionState(
            videoResources = videoResources,
            initialLoadIfNeeded = initialLoadIfNeeded,
        )
    }
}
