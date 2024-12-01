package jp.shirataki707.yamato.feature.home.detail.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import jp.shirataki707.yamato.core.model.data.DetailPageConfig
import jp.shirataki707.yamato.feature.home.detail.ui.section.DetailInitialSection
import jp.shirataki707.yamato.feature.home.detail.ui.section.DetailInitialSectionState
import jp.shirataki707.yamato.feature.home.detail.ui.section.DetailLoadedSection
import jp.shirataki707.yamato.feature.home.detail.ui.section.DetailLoadedSectionState
import jp.shirataki707.yamato.feature.home.detail.ui.section.DetailLoadingSection
import jp.shirataki707.yamato.feature.home.detail.ui.section.DetailLoadingSectionState

@Composable
fun DetailPageHost(
    detailPageConfig: DetailPageConfig,
    modifier: Modifier = Modifier,
    detailPageViewModel: DetailPageViewModel = hiltViewModel(),
) {
    val detailPageState = rememberDetailPageState(
        detailPageViewModel = detailPageViewModel,
        detailPageConfig = detailPageConfig,
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
            )
        }
    }
}
