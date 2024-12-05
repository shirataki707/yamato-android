package jp.shirataki707.yamato.feature.home.ui.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import jp.shirataki707.yamato.core.common.utils.VideoUtils.openYoutubeVideo
import jp.shirataki707.yamato.core.model.data.Video.VideoBlockInfo
import jp.shirataki707.yamato.feature.home.model.VideoResources

internal data class HomeLoadedSectionState(
    val videoResources: VideoResources,
    val onVideoClick: (videoId: String) -> Unit,
    val onMoreButtonClick: (VideoBlockInfo) -> Unit,
) : HomeContentSectionState

@Composable
internal fun rememberHomeLoadedSectionState(
    videoResources: VideoResources,
    navigateToDetailPage: (VideoBlockInfo) -> Unit,
): HomeLoadedSectionState {
    val context = LocalContext.current

    val onVideoClick = { videoId: String ->
        openYoutubeVideo(context = context, videoId = videoId)
    }

    val onMoreButtonClick = { videoBlockInfo: VideoBlockInfo ->
        navigateToDetailPage(videoBlockInfo)
    }

    return remember(
        videoResources,
    ) {
        HomeLoadedSectionState(
            videoResources = videoResources,
            onVideoClick = onVideoClick,
            onMoreButtonClick = onMoreButtonClick,
        )
    }
}
