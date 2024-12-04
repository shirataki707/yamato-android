package jp.shirataki707.yamato.feature.video.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import jp.shirataki707.yamato.core.model.data.Video.VideoBlockInfo
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import jp.shirataki707.yamato.feature.video.ui.section.VideoContentSectionState
import jp.shirataki707.yamato.feature.video.ui.section.rememberVideoInitialSectionState
import jp.shirataki707.yamato.feature.video.ui.section.rememberVideoLoadedSectionState
import jp.shirataki707.yamato.feature.video.ui.section.rememberVideoLoadingSectionState

internal class VideoPageState(
    val contentSectionState: VideoContentSectionState,
    val videoBlockInfo: VideoBlockInfo,
    val onBackClick: () -> Unit,
)

@Composable
internal fun rememberVideoPageState(
    videoPageViewModel: VideoPageViewModel,
    videoBlockInfo: VideoBlockInfo,
    onBackClick: () -> Unit,
): VideoPageState {
    val isInitialLoading = videoPageViewModel.isLoading
    val videoResources = videoPageViewModel.videoResources

    val contentSectionState = when {
        isInitialLoading -> rememberVideoLoadingSectionState()
        videoResources is ParcelableResult.Success -> {
            rememberVideoLoadedSectionState(
                videoResources = videoResources.result,
            )
        }

        else -> {
            rememberVideoInitialSectionState(
                videoResources = videoResources,
                initialLoadIfNeeded = { videoPageViewModel.initialLoadIfNeeded(videoBlockInfo) },
            )
        }
    }

    return remember(contentSectionState, onBackClick, videoBlockInfo) {
        VideoPageState(
            contentSectionState = contentSectionState,
            videoBlockInfo = videoBlockInfo,
            onBackClick = onBackClick,
        )
    }
}
