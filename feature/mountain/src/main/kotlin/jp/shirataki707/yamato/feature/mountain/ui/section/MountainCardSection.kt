package jp.shirataki707.yamato.feature.mountain.ui.section

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.shirataki707.yamato.core.model.data.Mountain
import jp.shirataki707.yamato.feature.mountain.ui.component.MountainCardItem

@Composable
fun MountainCardSection(
    mountains: List<Mountain>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(mountains) { mountain ->
            MountainCardItem(
                mountain = mountain,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
