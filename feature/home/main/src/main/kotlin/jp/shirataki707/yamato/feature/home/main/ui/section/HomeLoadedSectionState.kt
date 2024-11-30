package jp.shirataki707.yamato.feature.home.main.ui.section

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import jp.shirataki707.yamato.core.model.data.DetailPageConfig
import jp.shirataki707.yamato.core.model.data.VideoResources

internal data class HomeLoadedSectionState(
    val videoResources: VideoResources,
    val onVideoClick: (videoId: String) -> Unit,
    val onMoreButtonClick: (DetailPageConfig) -> Unit,
) : HomeContentSectionState

@Composable
internal fun rememberHomeLoadedSectionState(
    videoResources: VideoResources,
    navigateToDetailPage: (DetailPageConfig) -> Unit,
): HomeLoadedSectionState {
    val context = LocalContext.current

    val onVideoClick = { videoId: String ->
        openYoutubeVideo(context = context, videoId = videoId)
    }

    val onMoreButtonClick = { detailPageConfig: DetailPageConfig ->
        navigateToDetailPage(detailPageConfig)
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

private fun openYoutubeVideo(context: Context, videoId: String) {
    val appUri = Uri.parse("vnd.youtube:$videoId")
    val webUri = Uri.parse("https://www.youtube.com/watch?v=$videoId")

    val appIntent = Intent(Intent.ACTION_VIEW, appUri)
    val webIntent = Intent(Intent.ACTION_VIEW, webUri)

    try {
        context.startActivity(appIntent)
    } catch (e: ActivityNotFoundException) {
        context.startActivity(webIntent)
    }
}
