package jp.shirataki707.yamato.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import jp.shirataki707.yamato.feature.home.ui.section.HomeContentSectionState
import jp.shirataki707.yamato.feature.home.ui.section.rememberHomeInitialSectionState
import jp.shirataki707.yamato.feature.home.ui.section.rememberHomeLoadedSectionState
import jp.shirataki707.yamato.feature.home.ui.section.rememberHomeLoadingSectionState

internal class HomePageState(
    val contentSectionState: HomeContentSectionState,
)

@Composable
internal fun rememberHomePageState(
    homePageViewModel: HomePageViewModel,
): HomePageState {
    val isInitialLoading = homePageViewModel.isLoading
    val youtubeVideoResources = homePageViewModel.videoResources

    val onVideoClick: (videoId: String) -> Unit = {}

    val onMoreButtonClick: () -> Unit = {}

    val contentSectionState = when {
        isInitialLoading -> rememberHomeLoadingSectionState()
        youtubeVideoResources is ParcelableResult.Success -> {
            rememberHomeLoadedSectionState(
                videoResources = youtubeVideoResources.result,
                onVideoClick = onVideoClick,
                onMoreButtonClick = onMoreButtonClick,
            )
        }
        else -> {
            rememberHomeInitialSectionState(
                videoResources = youtubeVideoResources,
                initialLoadIfNeeded = homePageViewModel::initialLoadIfNeeded,
            )
        }
    }

    return remember(contentSectionState) {
        HomePageState(
            contentSectionState = contentSectionState,
        )
    }
}
