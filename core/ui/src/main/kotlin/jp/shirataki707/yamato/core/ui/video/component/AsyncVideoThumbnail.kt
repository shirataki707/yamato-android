package jp.shirataki707.yamato.core.ui.video.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage

@Composable
fun AsyncVideoThumbnail(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    val isPreview = LocalInspectionMode.current

    if (isPreview) {
        Image(
            painter = painterResource(id = jp.shirataki707.core.designsystem.R.drawable.core_designsystem_ic_placeholder_default),
            contentDescription = contentDescription,
            modifier = modifier,
        )
    } else {
        AsyncImage(
            model = imageUrl,
            placeholder = painterResource(id = jp.shirataki707.core.designsystem.R.drawable.core_designsystem_ic_placeholder_default),
            contentDescription = contentDescription,
            modifier = modifier,
        )
    }
}
