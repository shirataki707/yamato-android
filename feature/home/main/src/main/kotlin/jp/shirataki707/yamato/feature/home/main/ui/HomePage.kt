package jp.shirataki707.yamato.feature.home.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import jp.shirataki707.yamato.feature.home.main.ui.section.HomeInitialSection
import jp.shirataki707.yamato.feature.home.main.ui.section.HomeInitialSectionState
import jp.shirataki707.yamato.feature.home.main.ui.section.HomeLoadedSection
import jp.shirataki707.yamato.feature.home.main.ui.section.HomeLoadedSectionState
import jp.shirataki707.yamato.feature.home.main.ui.section.HomeLoadingSection
import jp.shirataki707.yamato.feature.home.main.ui.section.HomeLoadingSectionState

@Composable
fun HomePageHost(
    onNavigateToDetail: (String) -> Unit,
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
