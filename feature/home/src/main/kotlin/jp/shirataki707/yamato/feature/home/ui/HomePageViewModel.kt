package jp.shirataki707.yamato.feature.home.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.shirataki707.yamato.core.data.repository.VideoResourceRepository
import jp.shirataki707.yamato.core.model.data.VideoResources
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiRequest
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val videoResourceRepository: VideoResourceRepository,
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var videoResources: ParcelableResult<VideoResources>? by mutableStateOf(null)
        private set

    fun initialLoadIfNeeded() {
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

            videoResources = withContext(Dispatchers.IO) {
                val videoCarouselBlockTypes =
                    videoResourceRepository.getVideoCarouselBlockTypeList()
                Log.d("HomePageViewModel", "videoCarouselBlockTypes: $videoCarouselBlockTypes")

                val deferredCarouselBlocks = coroutineScope {
                    videoCarouselBlockTypes.map { blockType ->
                        async {
                            when (blockType) {
                                VideoResources.VideoCarouselBlockType.Recommended -> {
                                    // TODO: Implement recommendation logic
                                    videoResourceRepository.getVideoSummariesByKeyword(keyword = "登山")
                                }

                                VideoResources.VideoCarouselBlockType.Popular -> {
                                    // TODO: Implement popularity logic
                                    videoResourceRepository.getVideoSummariesByKeyword(
                                        keyword = "日本百名山 登山",
                                        order = YoutubeSearchApiRequest.Order.VIEW_COUNT,
                                    )
                                }

                                VideoResources.VideoCarouselBlockType.Latest -> {
                                    videoResourceRepository.getVideoSummariesByKeyword(
                                        keyword = "日本百名山　登山",
                                        order = YoutubeSearchApiRequest.Order.DATE,
                                    )
                                }

                                is VideoResources.VideoCarouselBlockType.Mountain -> {
                                    videoResourceRepository.getVideoSummariesByKeyword(
                                        keyword = "${blockType.mountainName} 登山",
                                    )
                                }

                                is VideoResources.VideoCarouselBlockType.Channel -> {
                                    videoResourceRepository.getVideoSummariesByKeyword(
                                        keyword = "登山",
                                        channelId = blockType.channelId,
                                    )
                                }
                            }
                        }
                    }.awaitAll()
                }

                Log.d("HomePageViewModel", "deferredCarouselBlocks: $deferredCarouselBlocks")

                ParcelableResult.Success(
                    VideoResources(
                        videoCarouselBlocks = videoCarouselBlockTypes.mapIndexed { index, blockType ->
                            VideoResources.VideoCarouselBlock(
                                blockTitle = when (blockType) {
                                    is VideoResources.VideoCarouselBlockType.Mountain -> {
                                        blockType.mountainName
                                    }
                                    is VideoResources.VideoCarouselBlockType.Channel -> {
                                        deferredCarouselBlocks[index].first().channelName
                                    }
                                    else -> blockType.toString()
                                },
                                blockType = blockType,
                                videoSummaries = deferredCarouselBlocks[index],
                            )
                        },
                    )
                )
        }

        isLoading = false
    }
}
}
