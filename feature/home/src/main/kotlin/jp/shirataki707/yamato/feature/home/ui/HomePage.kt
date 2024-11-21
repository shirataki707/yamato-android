package jp.shirataki707.yamato.feature.home.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.shirataki707.yamato.core.ui.common.Navigator
import jp.shirataki707.yamato.feature.home.ui.section.HomeInitialSection
import jp.shirataki707.yamato.feature.home.ui.section.HomeInitialSectionState
import jp.shirataki707.yamato.feature.home.ui.section.HomeLoadedSection
import jp.shirataki707.yamato.feature.home.ui.section.HomeLoadedSectionState
import jp.shirataki707.yamato.feature.home.ui.section.HomeLoadingSection
import jp.shirataki707.yamato.feature.home.ui.section.HomeLoadingSectionState

@Composable
fun HomePageHost(
    homePageViewModel: HomePageViewModel = viewModel(),
    navigator: Navigator,
) {
    val homePageState = rememberHomePageState(
        homePageViewModel = homePageViewModel,
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
