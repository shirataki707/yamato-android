package jp.shirataki707.yamato.feature.mountain

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MountainScreen(
    viewModel: MountainViewModel = viewModel(),
    modifier: Modifier = Modifier,
) {
//    val mountains = MountainRepository.mountains
//    MountainCard(
//        mountain = mountains.first()
//    )
}

//@Composable
//fun MountainCard(
//    mountain: Mountain,
//    modifier: Modifier = Modifier,
//) {
//    Card(
//        modifier = modifier,
//    ) {
//        Text(
//            text = stringResource(id = mountain.nameRes),
//        )
//    }
//}

//@Preview
//@Composable
//fun MountainCardPreview() {
//    MountainCard(
////        mountain = MountainRepository.mountains.first()
//    )
//}
