package jp.shirataki707.yamato.feature.home.main.ui.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.shirataki707.yamato.core.ui.video.VideoCarouselBlock

@Composable
internal fun HomeLoadedSection(
    sectionState: HomeLoadedSectionState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        sectionState.videoResources.videoCarouselBlocks.forEach { videoCarouselBlock ->
            VideoCarouselBlock(
                videoCarouselBlock = videoCarouselBlock,
                onVideoClick = sectionState.onVideoClick,
                onMoreButtonClick = sectionState.onMoreButtonClick,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
