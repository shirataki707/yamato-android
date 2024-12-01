package jp.shirataki707.yamato.feature.home.main.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.shirataki707.yamato.core.data.repository.VideoResourceRepository
import jp.shirataki707.yamato.core.model.data.DetailPageConfig
import jp.shirataki707.yamato.core.model.data.VideoResources
import jp.shirataki707.yamato.core.model.data.VideoResources.VideoCarouselBlockType
import jp.shirataki707.yamato.core.model.data.VideoResources.VideoCarouselBlockType.Channel
import jp.shirataki707.yamato.core.model.data.VideoResources.VideoCarouselBlockType.Latest
import jp.shirataki707.yamato.core.model.data.VideoResources.VideoCarouselBlockType.Mountain
import jp.shirataki707.yamato.core.model.data.VideoResources.VideoCarouselBlockType.Popular
import jp.shirataki707.yamato.core.model.data.VideoResources.VideoCarouselBlockType.Recommended
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

            val videoCarouselBlockTypes = withContext(Dispatchers.IO) {
                videoResourceRepository.getVideoCarouselBlockTypeList()
            }
            Log.d("HomePageViewModel", "videoCarouselBlockTypes: $videoCarouselBlockTypes")

            val deferredCarouselBlocks = withContext(Dispatchers.IO) {
                coroutineScope {
                    videoCarouselBlockTypes.map { blockType ->
                        async {
                            getVideoSummaries(blockType)
                        }
                    }.awaitAll()
                }
            }

            videoResources = ParcelableResult.Success(
                VideoResources(
                    videoCarouselBlocks = videoCarouselBlockTypes.mapIndexed { index, blockType ->
                        val blockTitle = when (blockType) {
                            Recommended -> {
                                "おすすめ"
                            }

                            Latest -> {
                                "最新"
                            }

                            Popular -> {
                                "人気"
                            }

                            is Mountain -> {
                                blockType.mountainName
                            }

                            is Channel -> {
                                deferredCarouselBlocks[index].first().channelName
                            }
                        }
                        VideoResources.VideoCarouselBlock(
                            blockTitle = blockTitle,
                            blockType = blockType,
                            videoSummaries = deferredCarouselBlocks[index],
                            detailPageConfig = DetailPageConfig(
                                detailPageTitle = blockTitle,
                                carouselBlockType = blockType,
                                keyword = getSearchKeyword(blockType),
                                channelId = if (blockType is Channel) blockType.channelId else null,
                                order = getSearchOrder(blockType),
                            ),
                        )
                    },
                ),
            )

            isLoading = false
        }
    }

    private suspend fun getVideoSummaries(blockType: VideoCarouselBlockType) =
        when (blockType) {
            Recommended -> {
                // TODO: Implement recommendation logic
                videoResourceRepository.getVideoSummariesByKeyword(
                    keyword = getSearchKeyword(blockType),
                )
            }

            Popular -> {
                // TODO: Implement popularity logic
                videoResourceRepository.getVideoSummariesByKeyword(
                    keyword = getSearchKeyword(blockType),
                    order = getSearchOrder(blockType),
                )
            }

            Latest -> {
                videoResourceRepository.getVideoSummariesByKeyword(
                    keyword = getSearchKeyword(blockType),
                    order = getSearchOrder(blockType),
                )
            }

            is Mountain -> {
                videoResourceRepository.getVideoSummariesByKeyword(
                    keyword = getSearchKeyword(blockType),
                )
            }

            is Channel -> {
                videoResourceRepository.getVideoSummariesByKeyword(
                    keyword = getSearchKeyword(blockType),
                    channelId = blockType.channelId,
                )
            }
        }

    private fun getSearchKeyword(blockType: VideoCarouselBlockType): String =
        when (blockType) {
            is Channel, Recommended -> "登山"
            Popular, Latest -> "日本百名山 登山"
            is Mountain -> "${blockType.mountainName} 登山"
        }

    private fun getSearchOrder(blockType: VideoCarouselBlockType): YoutubeSearchApiRequest.Order? =
        when (blockType) {
            Popular -> YoutubeSearchApiRequest.Order.VIEW_COUNT
            Latest -> YoutubeSearchApiRequest.Order.DATE
            else -> null
        }
}

