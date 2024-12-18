package jp.shirataki707.yamato.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import jp.shirataki707.yamato.core.model.data.Video.VideoBlockInfo
import jp.shirataki707.yamato.feature.home.ui.section.HomeInitialSection
import jp.shirataki707.yamato.feature.home.ui.section.HomeInitialSectionState
import jp.shirataki707.yamato.feature.home.ui.section.HomeLoadedSection
import jp.shirataki707.yamato.feature.home.ui.section.HomeLoadedSectionState
import jp.shirataki707.yamato.feature.home.ui.section.HomeLoadingSection
import jp.shirataki707.yamato.feature.home.ui.section.HomeLoadingSectionState

@Composable
internal fun HomePageHost(
    onNavigateToDetail: (VideoBlockInfo) -> Unit,
    modifier: Modifier = Modifier,
    homePageViewModel: HomePageViewModel = hiltViewModel(),
) {
    val homePageState = rememberHomePageState(
        homePageViewModel = homePageViewModel,
        onNavigateToDetailPage = onNavigateToDetail,
    )

    LaunchedEffect(homePageViewModel) {
        homePageViewModel.initialLoadIfNeeded()
    }

    HomePage(
        homePageState = homePageState,
    )
}

@Composable
private fun HomePage(
    homePageState: HomePageState,
    modifier: Modifier = Modifier,
) {
    when (val sectionState = homePageState.contentSectionState) {
        is HomeInitialSectionState -> {
            HomeInitialSection(
                sectionState = sectionState,
            )
        }

        is HomeLoadingSectionState -> {
            HomeLoadingSection(
                sectionState = sectionState,
            )
        }

        is HomeLoadedSectionState -> {
            HomeLoadedSection(
                sectionState = sectionState,
            )
        }
    }
}
