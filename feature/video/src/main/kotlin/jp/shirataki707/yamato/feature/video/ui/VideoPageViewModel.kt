package jp.shirataki707.yamato.feature.video.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.shirataki707.yamato.core.common.network.Dispatcher
import jp.shirataki707.yamato.core.common.network.YamatoDispatchers
import jp.shirataki707.yamato.core.data.repository.VideoResourceRepository
import jp.shirataki707.yamato.core.model.data.Video.VideoBlockInfo
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType.Channel
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType.Latest
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType.Mountain
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType.Popular
import jp.shirataki707.yamato.core.model.data.Video.VideoCarouselBlockType.Recommended
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import jp.shirataki707.yamato.feature.video.model.VideoResources
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class VideoPageViewModel @Inject constructor(
    @Dispatcher(YamatoDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val videoResourceRepository: VideoResourceRepository,
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var videoResources: ParcelableResult<VideoResources>? by mutableStateOf(null)
        private set

    fun initialLoadIfNeeded(videoBlockInfo: VideoBlockInfo) {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, _ ->
                isLoading = false
                videoResources = ParcelableResult.Failure()
            },
        ) {
            if (isLoading) {
                return@launch
            }

            if (videoResources is ParcelableResult.Success) {
                return@launch
            }

            isLoading = true

            val videoSummaries = withContext(ioDispatcher) {
                when (videoBlockInfo.videoCarouselBlockType) {
                    Recommended -> {
                        videoResourceRepository.getVideoSummariesByKeyword(
                            keyword = videoBlockInfo.searchKeyword,
                            maxResults = 30,
                        )
                    }

                    Popular -> {
                        videoResourceRepository.getVideoSummariesByKeyword(
                            keyword = videoBlockInfo.searchKeyword,
                            order = videoBlockInfo.searchOrder,
                            maxResults = 30,
                        )
                    }

                    Latest -> {
                        videoResourceRepository.getVideoSummariesByKeyword(
                            keyword = videoBlockInfo.searchKeyword,
                            order = videoBlockInfo.searchOrder,
                            maxResults = 30,
                        )
                    }

                    is Mountain -> {
                        videoResourceRepository.getVideoSummariesByKeyword(
                            keyword = videoBlockInfo.searchKeyword,
                            maxResults = 30,
                        )
                    }

                    is Channel -> {
                        videoResourceRepository.getVideoSummariesByKeyword(
                            keyword = videoBlockInfo.searchKeyword,
                            channelId = videoBlockInfo.searchChannelId,
                            maxResults = 30,
                        )
                    }
                }
            }

            videoResources = ParcelableResult.Success(
                VideoResources(
                    videoPageTitle = videoBlockInfo.videoBlockTitle,
                    videoSummaries = videoSummaries,
                ),
            )

            isLoading = false
        }
    }
}
