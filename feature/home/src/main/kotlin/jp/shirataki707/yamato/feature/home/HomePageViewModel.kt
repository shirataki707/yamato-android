package jp.shirataki707.yamato.feature.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.shirataki707.yamato.core.data.repository.YoutubeVideoRepository
import jp.shirataki707.yamato.core.model.data.YoutubeVideoResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val youtubeVideoRepository: YoutubeVideoRepository,
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var youtubeVideoResources by mutableStateOf<List<YoutubeVideoResource>>(emptyList())
        private set

    fun initialLoad(keyword: String) {
        viewModelScope.launch {
            youtubeVideoResources = withContext(Dispatchers.IO) {
                val searchedVideos = youtubeVideoRepository.searchVideosByKeyword(keyword = keyword)
                searchedVideos.map { searchedVideo ->
                    YoutubeVideoResource(
                        videoTitle = searchedVideo.videoTitle,
                        channelName = searchedVideo.channelName,
                        description = searchedVideo.description,
                        thumbnailUrl = searchedVideo.thumbnailUrl,
                        videoId = searchedVideo.videoId,
                        publishedAt = searchedVideo.publishedAt,
                    )
                }
            }
            Log.d("HomePageViewModel", "youtubeVideoResources: $youtubeVideoResources")
        }
    }
}
