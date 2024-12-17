package jp.shirataki707.yamato.feature.map.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun MapPageHost(
    modifier: Modifier = Modifier,
    mapPageViewModel: MapPageViewModel = hiltViewModel(),
) {
    val mapPageState = rememberMapPageState(
        mapPageViewModel = mapPageViewModel,
    )

    LaunchedEffect(mapPageViewModel) {
        mapPageViewModel.initialLoadIfNeeded()
    }

    MapPage(
        mapPageState = mapPageState,
    )
}

@Composable
private fun MapPage(
    mapPageState: MapPageState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text("Map Page")
    }
}
