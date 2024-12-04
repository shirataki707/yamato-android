package jp.shirataki707.yamato.core.ui.video

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.shirataki707.core.ui.R
import jp.shirataki707.yamato.core.designsystem.theme.YamatoTheme
import jp.shirataki707.yamato.core.model.data.Video
import jp.shirataki707.yamato.core.model.data.Video.VideoBlockInfo
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType.Recommended
import jp.shirataki707.yamato.core.model.data.Video.VideoSummary
import jp.shirataki707.yamato.core.ui.video.component.VideoCarouselBlockHeader
import jp.shirataki707.yamato.core.ui.video.component.VideoCarouselBlockItem

@Composable
fun VideoCarouselBlock(
    video: Video,
    onVideoClick: (videoId: String) -> Unit,
    onMoreButtonClick: (VideoBlockInfo) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        VideoCarouselBlockHeader(
            headerTitle = video.videoBlockInfo.videoBlockTitle,
            buttonText = stringResource(R.string.core_ui_more_show),
            onButtonClick = {
                onMoreButtonClick(video.videoBlockInfo)
            },
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(video.videoSummaries) { videoSummary ->
                VideoCarouselBlockItem(
                    imageUrl = videoSummary.thumbnailUrl,
                    videoTitle = videoSummary.videoTitle,
                    videoId = videoSummary.videoId,
                    channelName = videoSummary.channelName,
                    onVideoClick = onVideoClick,
                )
            }
        }
    }
}

@Preview
@Composable
private fun VideoCarouselBlockPreview() {
    YamatoTheme {
        VideoCarouselBlock(
            video = Video(
                videoSummaries = listOf(
                    VideoSummary(
                        videoTitle = "富士山登山やってみた",
                        channelName = "しらたきチャンネル",
                        description = "",
                        thumbnailUrl = "",
                        videoId = "",
                        publishedAt = "",
                    ),
                    VideoSummary(
                        videoTitle = "八ヶ岳に行ってみた",
                        channelName = "やっほーチャンネル",
                        description = "",
                        thumbnailUrl = "",
                        videoId = "",
                        publishedAt = "",
                    ),
                ),
                videoBlockInfo = VideoBlockInfo(
                    videoBlockTitle = "おすすめ",
                    videoCarouselBlockType = Recommended,
                    searchKeyword = "登山",
                ),
            ),
            onVideoClick = { },
            onMoreButtonClick = { },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
