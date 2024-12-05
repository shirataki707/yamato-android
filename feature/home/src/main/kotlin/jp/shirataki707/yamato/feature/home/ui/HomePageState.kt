package jp.shirataki707.yamato.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import jp.shirataki707.yamato.core.model.data.Video.VideoBlockInfo
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
    onNavigateToDetailPage: (VideoBlockInfo) -> Unit,
): HomePageState {
    val isInitialLoading = homePageViewModel.isLoading
    val videoResources = homePageViewModel.videoResources

    val contentSectionState = when {
        isInitialLoading -> rememberHomeLoadingSectionState()
        videoResources is ParcelableResult.Success -> {
            rememberHomeLoadedSectionState(
                videoResources = videoResources.result,
                navigateToDetailPage = onNavigateToDetailPage,
            )
        }

        else -> {
            rememberHomeInitialSectionState(
                videoResources = videoResources,
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
