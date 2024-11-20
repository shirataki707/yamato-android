package jp.shirataki707.yamato.feature.home.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.shirataki707.yamato.core.ui.youtube.VideoCarouselBlock

@Composable
internal fun HomeLoadedSection(
    sectionState: HomeLoadedSectionState,
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues),
    ) {
        VideoCarouselBlock(
            headerTitle = "おすすめ",
            videoSummaries = sectionState.videoResources.videoSummaries,
            onVideoClick = sectionState.onVideoClick,
            onMoreButtonClick = sectionState.onMoreButtonClick,
            modifier = Modifier.padding(16.dp),
        )
    }
}
