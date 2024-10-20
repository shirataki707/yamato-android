package jp.shirataki707.feature.mountain

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import jp.shirataki707.yamato.core.data.model.Mountain
import jp.shirataki707.yamato.core.data.repository.MountainRepository

@Composable
fun MountainScreen(
    modifier: Modifier = Modifier
) {
    val mountains = MountainRepository.mountains
    MountainCard(
        mountain = mountains.first()
    )
}

@Composable
fun MountainCard(
    mountain: Mountain,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(id = mountain.nameRes)
        )
    }
}

@Preview
@Composable
fun MountainCardPreview() {
    MountainCard(
        mountain = MountainRepository.mountains.first()
    )
}
