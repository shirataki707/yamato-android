package jp.shirataki707.yamato.feature.video.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.shirataki707.yamato.core.common.utils.DateFormatUtils
import jp.shirataki707.yamato.feature.video.ui.component.VideoGridItem

@Composable
internal fun VideoLoadedSection(
    sectionState: VideoLoadedSectionState,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(sectionState.videoResources.videoSummaries) { videoSummary ->
            VideoGridItem(
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
