package jp.shirataki707.yamato.feature.home.detail.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.shirataki707.yamato.core.common.network.Dispatcher
import jp.shirataki707.yamato.core.common.network.YamatoDispatchers
import jp.shirataki707.yamato.core.data.repository.VideoResourceRepository
import jp.shirataki707.yamato.core.model.data.video.DetailPageConfig
import jp.shirataki707.yamato.core.model.data.video.DetailVideoResources
import jp.shirataki707.yamato.core.model.data.video.VideoResources.VideoCarouselBlockType.Channel
import jp.shirataki707.yamato.core.model.data.video.VideoResources.VideoCarouselBlockType.Latest
import jp.shirataki707.yamato.core.model.data.video.VideoResources.VideoCarouselBlockType.Mountain
import jp.shirataki707.yamato.core.model.data.video.VideoResources.VideoCarouselBlockType.Popular
import jp.shirataki707.yamato.core.model.data.video.VideoResources.VideoCarouselBlockType.Recommended
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailPageViewModel @Inject constructor(
    @Dispatcher(YamatoDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val videoResourceRepository: VideoResourceRepository,
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var detailVideoResources: ParcelableResult<DetailVideoResources>? by mutableStateOf(null)
        private set

    fun initialLoadIfNeeded(detailPageConfig: DetailPageConfig) {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, _ ->
                isLoading = false
                detailVideoResources = ParcelableResult.Failure()
            },
        ) {
            if (isLoading) {
                return@launch
            }

            if (detailVideoResources is ParcelableResult.Success) {
                return@launch
            }

            isLoading = true

            val videoSummaries = withContext(ioDispatcher) {
                when (detailPageConfig.carouselBlockType) {
                    Recommended -> {
                        videoResourceRepository.getVideoSummariesByKeyword(
                            keyword = detailPageConfig.keyword,
                            maxResults = 30,
                        )
                    }

                    Popular -> {
                        videoResourceRepository.getVideoSummariesByKeyword(
                            keyword = detailPageConfig.keyword,
                            order = detailPageConfig.order,
                            maxResults = 30,
                        )
                    }

                    Latest -> {
                        videoResourceRepository.getVideoSummariesByKeyword(
                            keyword = detailPageConfig.keyword,
                            order = detailPageConfig.order,
                            maxResults = 30,
                        )
                    }

                    is Mountain -> {
                        videoResourceRepository.getVideoSummariesByKeyword(
                            keyword = detailPageConfig.keyword,
                            maxResults = 30,
                        )
                    }

                    is Channel -> {
                        videoResourceRepository.getVideoSummariesByKeyword(
                            keyword = detailPageConfig.keyword,
                            channelId = detailPageConfig.channelId,
                            maxResults = 30,
                        )
                    }
                }
            }

            detailVideoResources = ParcelableResult.Success(
                DetailVideoResources(
                    detailPageTitle = detailPageConfig.detailPageTitle,
                    videoSummaries = videoSummaries,
                ),
            )

            isLoading = false
        }
    }
}
