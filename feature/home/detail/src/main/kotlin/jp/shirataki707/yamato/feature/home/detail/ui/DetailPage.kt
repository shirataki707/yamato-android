package jp.shirataki707.yamato.feature.home.detail.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import jp.shirataki707.yamato.core.designsystem.component.CanBackTopBar
import jp.shirataki707.yamato.core.model.data.video.DetailPageConfig
import jp.shirataki707.yamato.feature.home.detail.ui.section.DetailInitialSection
import jp.shirataki707.yamato.feature.home.detail.ui.section.DetailInitialSectionState
import jp.shirataki707.yamato.feature.home.detail.ui.section.DetailLoadedSection
import jp.shirataki707.yamato.feature.home.detail.ui.section.DetailLoadedSectionState
import jp.shirataki707.yamato.feature.home.detail.ui.section.DetailLoadingSection
import jp.shirataki707.yamato.feature.home.detail.ui.section.DetailLoadingSectionState

@Composable
fun DetailPageHost(
    detailPageConfig: DetailPageConfig,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    detailPageViewModel: DetailPageViewModel = hiltViewModel(),
) {
    val detailPageState = rememberDetailPageState(
        detailPageViewModel = detailPageViewModel,
        detailPageConfig = detailPageConfig,
        onBackClick = onBackClick,
    )

    LaunchedEffect(detailPageViewModel) {
        detailPageViewModel.initialLoadIfNeeded(detailPageConfig)
    }

    Log.d("DetailPageHost", ": keyword=$detailPageConfig")

    DetailPage(
        detailPageState = detailPageState,
    )
}

@Composable
private fun DetailPage(
    detailPageState: DetailPageState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CanBackTopBar(
            title = detailPageState.detailPageConfig.detailPageTitle,
            onBackClick = detailPageState.onBackClick,
        )
        when (val sectionState = detailPageState.contentSectionState) {
            is DetailInitialSectionState -> {
                DetailInitialSection(
                    sectionState = sectionState,
                )
            }

            is DetailLoadingSectionState -> {
                DetailLoadingSection(
                    sectionState = sectionState,
                )
            }

            is DetailLoadedSectionState -> {
                DetailLoadedSection(
                    sectionState = sectionState,
                    modifier = modifier.padding(8.dp),
                )
            }
        }
    }
}
