package jp.shirataki707.yamato.feature.mountain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.shirataki707.yamato.core.data.repository.MountainRepository
import jp.shirataki707.yamato.core.model.data.Mountain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MountainViewModel @Inject constructor(
    private val mountainRepository: MountainRepository,
) : ViewModel() {

    private val _mountains = MutableStateFlow<List<Mountain>>(emptyList())
    val mountains: StateFlow<List<Mountain>> = _mountains

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            mountainRepository.getMountains().collect { data ->
                _mountains.value = data
                Log.d("MountainViewModel", "Fetched data: $data")
            }
        }
    }
}
