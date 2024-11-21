package jp.shirataki707.yamato.feature.home.section

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
    paddingValues: PaddingValues,
) {
    // TODO: Implement the initial section include blank and error
    if (sectionState.videoResources is ParcelableResult.Failure) {
        CommonErrorContent(
            onReloadButtonClick = sectionState.initialLoadIfNeeded,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        )
    }
}
