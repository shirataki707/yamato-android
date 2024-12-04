package jp.shirataki707.yamato.feature.video.ui.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import jp.shirataki707.yamato.core.common.utils.VideoUtils.openYoutubeVideo
import jp.shirataki707.yamato.feature.video.model.VideoResources

internal data class VideoLoadedSectionState(
    val videoResources: VideoResources,
    val onVideoClick: (videoId: String) -> Unit,
) : VideoContentSectionState

@Composable
internal fun rememberVideoLoadedSectionState(
    videoResources: VideoResources,
): VideoLoadedSectionState {
    val context = LocalContext.current

    val onVideoClick = { videoId: String ->
        openYoutubeVideo(context = context, videoId = videoId)
    }

    return remember(
        videoResources,
    ) {
        VideoLoadedSectionState(
            videoResources = videoResources,
            onVideoClick = onVideoClick,
        )
    }
}
