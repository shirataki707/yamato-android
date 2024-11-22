package jp.shirataki707.yamato.feature.home.ui.section

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.shirataki707.yamato.core.ui.common.CommonErrorContent
import jp.shirataki707.yamato.core.ui.common.ParcelableResult

@Composable
internal fun HomeInitialSection(
    sectionState: HomeInitialSectionState,
    modifier: Modifier = Modifier,
) {
    // TODO: Implement the initial section include blank and error
    if (sectionState.videoResources is ParcelableResult.Failure) {
        CommonErrorContent(
            onReloadButtonClick = sectionState.initialLoadIfNeeded,
            modifier = modifier.fillMaxSize(),
        )
    }
}
