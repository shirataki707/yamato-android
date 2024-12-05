package jp.shirataki707.yamato.feature.home.ui.section

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.shirataki707.yamato.core.ui.video.VideoCarouselBlock

@Composable
internal fun HomeLoadedSection(
    sectionState: HomeLoadedSectionState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 8.dp),
    ) {
        items(sectionState.videoResources.videos) { videoCarouselBlock ->
            VideoCarouselBlock(
                video = videoCarouselBlock,
                onVideoClick = sectionState.onVideoClick,
                onMoreButtonClick = {
                    sectionState.onMoreButtonClick(videoCarouselBlock.videoBlockInfo)
                },
            )
        }
    }
}
