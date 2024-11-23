package jp.shirataki707.yamato.feature.home.detail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailPageHost(
    keyword: String,
    modifier: Modifier = Modifier,
    detailPageViewModel: DetailPageViewModel = hiltViewModel(),
) {
    val detailPageState = rememberDetailPageState(
        detailPageViewModel = detailPageViewModel,
        keyword = keyword,
    )

    DetailPage(
        detailPageState = detailPageState,
    )
}

@Composable
private fun DetailPage(
    detailPageState: DetailPageState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text("[Detail Page] Keyword: ${detailPageState.keyword}")
    }
}