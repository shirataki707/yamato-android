package jp.shirataki707.yamato.feature.home.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import jp.shirataki707.yamato.core.model.data.DetailPageConfig
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import jp.shirataki707.yamato.feature.home.detail.ui.section.DetailContentSectionState
import jp.shirataki707.yamato.feature.home.detail.ui.section.rememberDetailInitialSectionState
import jp.shirataki707.yamato.feature.home.detail.ui.section.rememberDetailLoadedSectionState
import jp.shirataki707.yamato.feature.home.detail.ui.section.rememberDetailLoadingSectionState

internal class DetailPageState(
    val contentSectionState: DetailContentSectionState,
    val detailPageConfig: DetailPageConfig,
    val onBackClick: () -> Unit,
)

@Composable
internal fun rememberDetailPageState(
    detailPageViewModel: DetailPageViewModel,
    detailPageConfig: DetailPageConfig,
    onBackClick: () -> Unit,
): DetailPageState {
    val isInitialLoading = detailPageViewModel.isLoading
    val detailVideoResources = detailPageViewModel.detailVideoResources

    val contentSectionState = when {
        isInitialLoading -> rememberDetailLoadingSectionState()
        detailVideoResources is ParcelableResult.Success -> {
            rememberDetailLoadedSectionState(
                detailVideoResources = detailVideoResources.result,
            )
        }

        else -> {
            rememberDetailInitialSectionState(
                detailVideoResources = detailVideoResources,
                initialLoadIfNeeded = { detailPageViewModel.initialLoadIfNeeded(detailPageConfig) },
            )
        }
    }

    return remember(contentSectionState, onBackClick, detailPageConfig) {
        DetailPageState(
            contentSectionState = contentSectionState,
            detailPageConfig = detailPageConfig,
            onBackClick = onBackClick,
        )
    }
}
