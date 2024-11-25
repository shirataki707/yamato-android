package jp.shirataki707.yamato.feature.home.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import jp.shirataki707.yamato.feature.home.main.ui.section.HomeContentSectionState
import jp.shirataki707.yamato.feature.home.main.ui.section.rememberHomeInitialSectionState
import jp.shirataki707.yamato.feature.home.main.ui.section.rememberHomeLoadedSectionState
import jp.shirataki707.yamato.feature.home.main.ui.section.rememberHomeLoadingSectionState

internal class HomePageState(
    val contentSectionState: HomeContentSectionState,
)

@Composable
internal fun rememberHomePageState(
    homePageViewModel: HomePageViewModel,
    onNavigateToDetailPage: (String) -> Unit,
): HomePageState {
    val isInitialLoading = homePageViewModel.isLoading
    val youtubeVideoResources = homePageViewModel.videoResources

    val contentSectionState = when {
        isInitialLoading -> rememberHomeLoadingSectionState()
        youtubeVideoResources is ParcelableResult.Success -> {
            rememberHomeLoadedSectionState(
                videoResources = youtubeVideoResources.result,
                navigateToDetailPage = onNavigateToDetailPage,
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
