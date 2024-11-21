package jp.shirataki707.yamato.feature.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.shirataki707.yamato.feature.home.section.HomeInitialSection
import jp.shirataki707.yamato.feature.home.section.HomeInitialSectionState
import jp.shirataki707.yamato.feature.home.section.HomeLoadedSection
import jp.shirataki707.yamato.feature.home.section.HomeLoadedSectionState
import jp.shirataki707.yamato.feature.home.section.HomeLoadingSection
import jp.shirataki707.yamato.feature.home.section.HomeLoadingSectionState

@Composable
fun HomePageHost(
    homePageViewModel: HomePageViewModel = viewModel(),
    modifier: Modifier = Modifier,
) {
    val homePageState = rememberHomePageState(
        homePageViewModel = homePageViewModel,
    )

    LaunchedEffect(homePageViewModel) {
        homePageViewModel.initialLoadIfNeeded()
    }

    HomePage(
        homePageState = homePageState,
        modifier = modifier,
    )
}

@Composable
private fun HomePage(
    homePageState: HomePageState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { padding ->
        when (val sectionState = homePageState.contentSectionState) {
            is HomeInitialSectionState -> {
                HomeInitialSection(
                    sectionState = sectionState,
                    paddingValues = padding,
                )
            }

            is HomeLoadingSectionState -> {
                HomeLoadingSection(
                    sectionState = sectionState,
                    paddingValues = padding,
                )
            }

            is HomeLoadedSectionState -> {
                HomeLoadedSection(
                    sectionState = sectionState,
                    paddingValues = padding,
                )
            }
        }
    }
}
