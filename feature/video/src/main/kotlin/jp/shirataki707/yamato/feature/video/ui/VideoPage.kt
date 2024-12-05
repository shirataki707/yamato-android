package jp.shirataki707.yamato.feature.video.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import jp.shirataki707.yamato.core.designsystem.component.CanBackTopBar
import jp.shirataki707.yamato.core.model.data.Video.VideoBlockInfo
import jp.shirataki707.yamato.feature.video.ui.section.VideoInitialSection
import jp.shirataki707.yamato.feature.video.ui.section.VideoInitialSectionState
import jp.shirataki707.yamato.feature.video.ui.section.VideoLoadedSection
import jp.shirataki707.yamato.feature.video.ui.section.VideoLoadedSectionState
import jp.shirataki707.yamato.feature.video.ui.section.VideoLoadingSection
import jp.shirataki707.yamato.feature.video.ui.section.VideoLoadingSectionState

@Composable
internal fun VideoPageHost(
    videoBlockInfo: VideoBlockInfo,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    videoPageViewModel: VideoPageViewModel = hiltViewModel(),
) {
    val videoPageState = rememberVideoPageState(
        videoPageViewModel = videoPageViewModel,
        videoBlockInfo = videoBlockInfo,
        onBackClick = onBackClick,
    )

    LaunchedEffect(videoPageViewModel) {
        videoPageViewModel.initialLoadIfNeeded(videoBlockInfo)
    }

    VideoPage(
        videoPageState = videoPageState,
    )
}

@Composable
private fun VideoPage(
    videoPageState: VideoPageState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CanBackTopBar(
            title = videoPageState.videoBlockInfo.videoBlockTitle,
            onBackClick = videoPageState.onBackClick,
        )
        when (val sectionState = videoPageState.contentSectionState) {
            is VideoInitialSectionState -> {
                VideoInitialSection(
                    sectionState = sectionState,
                )
            }

            is VideoLoadingSectionState -> {
                VideoLoadingSection(
                    sectionState = sectionState,
                )
            }

            is VideoLoadedSectionState -> {
                VideoLoadedSection(
                    sectionState = sectionState,
                    modifier = modifier.padding(8.dp),
                )
            }
        }
    }
}
