package jp.shirataki707.yamato.feature.mountain

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.shirataki707.yamato.core.database.model.MountainEntity
import jp.shirataki707.yamato.feature.mountain.section.MountainCardSection

@Composable
fun MountainScreen(
    modifier: Modifier = Modifier,
    viewModel: MountainViewModel = viewModel(),
) {
    val mountains = viewModel.mountains.collectAsState()

    MountainCardSection(
        mountains = mountains.value,
        modifier = modifier,
    )
}
