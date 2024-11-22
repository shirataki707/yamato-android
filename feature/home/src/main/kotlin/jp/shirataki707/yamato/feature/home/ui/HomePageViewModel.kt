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
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
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

            val keyword = "Android"

            videoResources = withContext(Dispatchers.IO) {
                val videoCarouselBlockTypes = videoResourceRepository.getVideoCarouselBlockTypeList()
                Log.d("HomePageViewModel", "videoCarouselBlockTypes: $videoCarouselBlockTypes")
                val videoSummaries = videoResourceRepository.getVideoResourcesByKeyword(keyword)
                Log.d("HomePageViewModel", "videoSummaries: $videoSummaries")
                ParcelableResult.Success(
                    VideoResources(
                        videoSummaries = videoSummaries,
                    ),
                )
            }

            isLoading = false
        }
    }
}
