package jp.shirataki707.yamato.feature.mountain.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.shirataki707.feature.mountain.R
import jp.shirataki707.yamato.core.common.ResourceUtils
import jp.shirataki707.yamato.core.designsystem.icon.YamatoIcons
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

    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.secondaryContainer
        },
    )

    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow,
                    ),
                )
                .background(color = color),
        ) {
            Image(
                painter = painterResource(id = mountainImageResId),
                contentDescription = mountain.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Row {
                    Text(
                        text = mountain.name,
                        fontSize = 24.sp,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { expanded = !expanded },
                    ) {
                        Icon(
                            imageVector = if (expanded) YamatoIcons.ExpandLess else YamatoIcons.ExpandMore,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                        )
                    }
                }
                Text(
                    text = "標高: ${mountain.elevation}m",
                )
                Text(
                    text = if (expanded) mountain.longDescription else mountain.shortDescription,
                )
            }
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
