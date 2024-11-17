package jp.shirataki707.yamato.core.ui.youtube.component

import androidx.compose.foundation.Image
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
import jp.shirataki707.core.ui.R
import jp.shirataki707.yamato.core.designsystem.component.DynamicAsyncImage
import jp.shirataki707.yamato.core.designsystem.theme.YamatoTheme

@Composable
fun VideoCarouselBlockItem(
    imageUrl: String,
    videoTitle: String,
    channelName: String,
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = remember(configuration) {
        configuration.screenWidthDp.dp
    }
    val thumbnailWidthDp = remember(screenWidthDp) {
        screenWidthDp / 2.2f
    }

    Column(
        modifier = modifier.width(thumbnailWidthDp)
    ) {
        AsyncVideoThumbnail(
            imageUrl = imageUrl,
            videoTitle = videoTitle,
        )
        Text(
            text = videoTitle,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp),
        )
        Text(
            text = channelName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp),
        )
    }
}

@Composable
fun AsyncVideoThumbnail(
    imageUrl: String?,
    videoTitle: String,
    modifier: Modifier = Modifier,
) {
    if (imageUrl == null) {
        Image(
            painter = painterResource(id = jp.shirataki707.core.designsystem.R.drawable.core_designsystem_ic_placeholder_default),
            contentDescription = videoTitle,
            modifier = modifier,
        )
    } else {
        DynamicAsyncImage(
            imageUrl = imageUrl,
            contentDescription = videoTitle,
            modifier = modifier,
        )
    }
}

@Preview
@Composable
fun VideoCarouselBlockItemPreview() {
    YamatoTheme {
        VideoCarouselBlockItem(
            imageUrl = "",
            videoTitle = "八ヶ岳完全ガイド！登山ルートからアクセス方法まで徹底解説",
            channelName = "なるまさ山旅",
        )
    }
}
