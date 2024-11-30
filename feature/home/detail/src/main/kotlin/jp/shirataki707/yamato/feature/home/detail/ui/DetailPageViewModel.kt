package jp.shirataki707.yamato.feature.home.detail.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPageViewModel @Inject constructor() : ViewModel() {

    init {
        Log.d("DetailPageViewModel", "DetailPageViewModel created")
    }
}
