package jp.shirataki707.yamato.feature.home.detail.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.shirataki707.yamato.feature.home.detail.navigation.DetailRoute
import javax.inject.Inject

@HiltViewModel
class DetailPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val keyword = savedStateHandle.toRoute<DetailRoute>().keyword

    init {
        Log.d("DetailPageViewModel", "keyword: $keyword")
    }
}
