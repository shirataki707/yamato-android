package jp.shirataki707.yamato.feature.home.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.shirataki707.yamato.core.common.network.Dispatcher
import jp.shirataki707.yamato.core.common.network.YamatoDispatchers
import jp.shirataki707.yamato.core.data.repository.VideoResourceRepository
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import jp.shirataki707.yamato.feature.home.model.VideoResources
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class HomePageViewModel @Inject constructor(
    @Dispatcher(YamatoDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
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

            videoResources = withContext(ioDispatcher) {
                ParcelableResult.Success(
                    VideoResources(
                        videos = videoResourceRepository.getCarouselBlockVideoResources(),
                    ),
                )
            }

            isLoading = false
        }
    }
}
