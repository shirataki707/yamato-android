package jp.shirataki707.yamato.feature.map.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.shirataki707.yamato.core.data.repository.MountainRepository
import jp.shirataki707.yamato.core.ui.common.ParcelableResult
import jp.shirataki707.yamato.feature.map.model.MapResources
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MapPageViewModel @Inject constructor(
    private val mountainRepository: MountainRepository,
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var mapResources: ParcelableResult<MapResources>? by mutableStateOf(null)
        private set

    fun initialLoadIfNeeded() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, exception ->
                Log.e("MapPageViewModel", "initialLoadIfNeeded", exception)
            },
        ) {
            if (isLoading) {
                return@launch
            }

            if (mapResources is ParcelableResult.Success) {
                return@launch
            }

            isLoading = true

            val mountains = mountainRepository.getMountains()
            Log.d("MapPageViewModel", "mountains: $mountains")

            mapResources = ParcelableResult.Success(
                MapResources(
                    mountains = mountains,
                ),
            )

            isLoading = false
        }
    }
}
