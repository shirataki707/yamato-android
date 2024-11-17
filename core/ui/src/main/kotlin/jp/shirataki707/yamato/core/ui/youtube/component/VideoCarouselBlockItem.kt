package jp.shirataki707.yamato.core.ui.youtube.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import jp.shirataki707.yamato.core.designsystem.theme.YamatoTheme
import jp.shirataki707.yamato.core.designsystem.theme.YamatoTypography

@Composable
internal fun VideoCarouselBlockItem(
    imageUrl: String?,
    videoTitle: String,
    channelName: String,
    onVideoClick: (videoId: String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = remember(configuration) {
        configuration.screenWidthDp.dp
    }
    // TODO: ブロックが2.2個分表示されるように調整する
    val thumbnailWidthDp = remember(screenWidthDp) {
        screenWidthDp / 2.4f
    }

    Column(
        modifier = modifier
            .width(thumbnailWidthDp)
            .clickable { onVideoClick(videoTitle) },
    ) {
        AsyncVideoThumbnail(
            imageUrl = imageUrl,
            contentDescription = videoTitle,
        )
        Text(
            text = videoTitle,
            style = YamatoTypography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp),
        )
        Text(
            text = channelName,
            style = YamatoTypography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp),
        )
    }
}

@Composable
private fun AsyncVideoThumbnail(
    imageUrl: String?,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    if (imageUrl == null) {
        Image(
            painter = painterResource(id = jp.shirataki707.core.designsystem.R.drawable.core_designsystem_ic_placeholder_default),
            contentDescription = contentDescription,
            modifier = modifier,
        )
    } else {
        AsyncImage(
            model = imageUrl,
            contentDescription = contentDescription,
            modifier = modifier,
        )
    }
}

@Preview
@Composable
private fun VideoCarouselBlockItemPreview() {
    YamatoTheme {
        VideoCarouselBlockItem(
            imageUrl = null,
            videoTitle = "八ヶ岳完全ガイド！登山ルートからアクセス方法まで徹底解説",
            channelName = "なるまさ山旅",
        )
    }
}
