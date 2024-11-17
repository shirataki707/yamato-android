package jp.shirataki707.yamato.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import jp.shirataki707.yamato.core.model.data.YoutubeVideoResource

internal class HomePageState(
    val youtubeVideoResources: List<YoutubeVideoResource>,
    val onVideoClick: (videoId: String) -> Unit,
    val onMoreButtonClick: () -> Unit,
)

@Composable
internal fun rememberHomePageState(
    homePageViewModel: HomePageViewModel,
): HomePageState {
    val isLoading = homePageViewModel.isLoading
    val youtubeVideoResources = homePageViewModel.youtubeVideoResources

    val onVideoClick: (videoId: String) -> Unit = remember {
        { videoId: String ->
            // TODO
        }
    }

    val onMoreButtonClick: () -> Unit = remember {
        {
            // TODO
        }
    }

    return remember(
        youtubeVideoResources,
        onVideoClick,
        onMoreButtonClick,
    ) {
        HomePageState(
            youtubeVideoResources = youtubeVideoResources,
            onVideoClick = onVideoClick,
            onMoreButtonClick = onMoreButtonClick,
        )
    }
}
