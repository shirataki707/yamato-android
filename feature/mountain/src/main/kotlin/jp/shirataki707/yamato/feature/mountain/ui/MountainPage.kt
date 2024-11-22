package jp.shirataki707.yamato.feature.mountain.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.shirataki707.yamato.feature.mountain.ui.section.MountainCardSection

@Composable
fun MountainPageHost(
    modifier: Modifier = Modifier,
    viewModel: MountainViewModel = hiltViewModel(),
) {
    MountainPage(
        modifier = modifier,
    )
}

@Composable
fun MountainPage(
    modifier: Modifier = Modifier,
    viewModel: MountainViewModel = viewModel(),
) {
    val mountains = viewModel.mountains.collectAsState()

    MountainCardSection(
        mountains = mountains.value,
        modifier = modifier,
    )
}
