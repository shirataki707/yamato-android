package jp.shirataki707.yamato.feature.mountain.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.shirataki707.feature.mountain.R
import jp.shirataki707.yamato.core.common.ResourceUtils
import jp.shirataki707.yamato.core.model.data.Mountain

@Composable
fun MountainCardItem(
    mountain: Mountain,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val mountainImageResId = ResourceUtils
        .getDrawableResIdFromFileName(context, mountain.imageName)
        ?: R.drawable.place_holder

    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(id = mountainImageResId),
                contentDescription = mountain.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(200.dp),
            )
            Text(
                text = mountain.name,
                fontSize = 20.sp
            )
        }
    }
}

@Preview
@Composable
fun MountainCardItemPreview() {
    MountainCardItem(
        mountain = Mountain(
            id = 1,
            name = "利尻岳",
            imageName = "mountain_001_rishiri",
            shortDescription = "日本海に浮かぶ利尻島にある利尻山(別名「利尻富士」)は、深田久弥の『日本百名山』の最初に登場する。",
            longDescription = "日本海に浮かぶ利尻島にある利尻山(別名「利尻富士」)は、深田久弥の『日本百名山』の最初に登場する。深田氏は、「島全 体が一つの頂点に引きしぼられて天に向かっている。こんなみごとな海上の山は利尻山だけである」と称賛している。",
            elevation = 1719,
            latitude = 45.1786111,
            longitude = 141.2419444,
            isClimbed = false,
        ),
    )
}