package jp.shirataki707.yamato.feature.home.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import jp.shirataki707.yamato.core.model.data.VideoResources
import jp.shirataki707.yamato.core.model.data.VideoSummary

internal data class HomeLoadedSectionState(
    val videoResources: VideoResources,
    val onVideoClick: (videoId: String) -> Unit,
    val onMoreButtonClick: () -> Unit,
) : HomeContentSectionState

@Composable
internal fun rememberHomeLoadedSectionState(
    videoResources: VideoResources,
    onVideoClick: (videoId: String) -> Unit,
    onMoreButtonClick: () -> Unit,
): HomeLoadedSectionState {
    return remember(
        videoResources,
        onVideoClick,
        onMoreButtonClick,
    ) {
        HomeLoadedSectionState(
            videoResources = videoResources,
            onVideoClick = onVideoClick,
            onMoreButtonClick = onMoreButtonClick,
        )
    }
}
