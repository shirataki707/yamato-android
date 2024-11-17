package jp.shirataki707.yamato.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.shirataki707.yamato.core.data.repository.YoutubeVideoRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val youtubeVideoRepository: YoutubeVideoRepository,
) : ViewModel() {

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val data = youtubeVideoRepository.searchVideosByKeyword("八ヶ岳　登山")
            Log.d("HomeScreenViewModel", "Fetched data: $data")
        }
    }
}
