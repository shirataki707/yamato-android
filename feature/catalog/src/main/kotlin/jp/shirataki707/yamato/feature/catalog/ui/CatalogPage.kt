package jp.shirataki707.yamato.feature.catalog.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun CatalogPageHost(
    modifier: Modifier = Modifier,
    catalogPageViewModel: CatalogPageViewModel = hiltViewModel(),
) {
    val catalogPageState = rememberCatalogPageState(
        catalogPageViewModel = catalogPageViewModel,
    )

    CatalogPage(
        catalogPageState = catalogPageState,
    )
}

@Composable
private fun CatalogPage(
    catalogPageState: CatalogPageState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text("Catalog Page")
    }
}