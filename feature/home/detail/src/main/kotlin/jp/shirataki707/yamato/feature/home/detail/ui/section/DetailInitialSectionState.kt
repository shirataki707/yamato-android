package jp.shirataki707.yamato.feature.home.detail.ui.section

import androidx.compose.runtime.Composable
import jp.shirataki707.yamato.core.model.data.video.DetailVideoResources
import jp.shirataki707.yamato.core.ui.common.ParcelableResult

internal data class DetailInitialSectionState(
    val detailVideoResources: ParcelableResult<DetailVideoResources>?,
    val initialLoadIfNeeded: () -> Unit,
) : DetailContentSectionState

@Composable
internal fun rememberDetailInitialSectionState(
    detailVideoResources: ParcelableResult<DetailVideoResources>?,
    initialLoadIfNeeded: () -> Unit,
): DetailInitialSectionState {
    return androidx.compose.runtime.remember(
        detailVideoResources,
        initialLoadIfNeeded,
    ) {
        DetailInitialSectionState(
            detailVideoResources = detailVideoResources,
            initialLoadIfNeeded = initialLoadIfNeeded,
        )
    }
}
