package jp.shirataki707.yamato.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.shirataki707.yamato.core.ui.youtube.VideoCarouselBlock

@Composable
fun HomePageHost(
    homePageViewModel: HomePageViewModel = viewModel(),
    modifier: Modifier = Modifier,
) {
    val homePageState = rememberHomePageState(
        homePageViewModel = homePageViewModel,
    )

    LaunchedEffect(homePageViewModel) {
        homePageViewModel.initialLoad("富士山")
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
    Column(modifier = modifier) {
        VideoCarouselBlock(
            headerTitle = "おすすめ",
            youtubeVideoResources = homePageState.youtubeVideoResources,
            onVideoClick = {},
            onMoreButtonClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
