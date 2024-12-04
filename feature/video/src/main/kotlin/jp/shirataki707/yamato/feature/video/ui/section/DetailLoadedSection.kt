package jp.shirataki707.yamato.feature.video.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.shirataki707.yamato.core.common.utils.DateFormatUtils
import jp.shirataki707.yamato.feature.video.ui.component.DetailVideoGridItem

@Composable
internal fun DetailLoadedSection(
    sectionState: DetailLoadedSectionState,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement the loaded section
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(sectionState.detailVideoResources.videoSummaries) { videoSummary ->
            DetailVideoGridItem(
                imageUrl = videoSummary.thumbnailUrl,
                videoTitle = videoSummary.videoTitle,
                videoId = videoSummary.videoId,
                channelName = videoSummary.channelName,
                publishedAt = DateFormatUtils.formatDate(videoSummary.publishedAt),
                onVideoClick = sectionState.onVideoClick,
            )
        }
    }
}
