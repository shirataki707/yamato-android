package jp.shirataki707.yamato.feature.home.detail.ui.section

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import jp.shirataki707.yamato.core.common.utils.openYoutubeVideo
import jp.shirataki707.yamato.core.model.data.DetailVideoResources

internal data class DetailLoadedSectionState(
    val detailVideoResources: DetailVideoResources,
    val onVideoClick: (videoId: String) -> Unit,
) : DetailContentSectionState

@Composable
internal fun rememberDetailLoadedSectionState(
    detailVideoResources: DetailVideoResources,
): DetailLoadedSectionState {
    val context = LocalContext.current

    val onVideoClick = { videoId: String ->
        openYoutubeVideo(context = context, videoId = videoId)
    }

    return remember(
        detailVideoResources,
    ) {
        DetailLoadedSectionState(
            detailVideoResources = detailVideoResources,
            onVideoClick = onVideoClick,
        )
    }
}
