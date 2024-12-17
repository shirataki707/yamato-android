package jp.shirataki707.yamato.feature.map.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.shirataki707.yamato.core.data.repository.MountainRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MapPageViewModel @Inject constructor(
    private val mountainRepository: MountainRepository,
) : ViewModel() {

    fun initialLoadIfNeeded() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, exception ->
                Log.e("MapPageViewModel", "initialLoadIfNeeded", exception)
            },
        ) {
            val mountains = mountainRepository.getMountains()
            Log.d("MapPageViewModel", "mountains: $mountains")
        }
    }
}
