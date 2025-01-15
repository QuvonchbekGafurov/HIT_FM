package com.example.hitfm.presentation.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hitfm.presentation.repository.YouTubeRepository
import com.example.hitfm.model.YouTubeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class YouTubeViewModel @Inject constructor(
    private val youTubeRepository: YouTubeRepository
) : ViewModel() {

    private val _youTubeResponse = MutableStateFlow<YouTubeResponse?>(null)
    val youTubeResponse: StateFlow<YouTubeResponse?> = _youTubeResponse

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    var errorMessage: String? = null

    fun getPlaylistItems() {
        viewModelScope.launch {
            _isLoading.value = true // Yuklash boshlandi
            try {
                val response = youTubeRepository.getPlaylistItems()
                if (response.isSuccessful && response.body() != null) {
                    _youTubeResponse.value = response.body()
                } else {
                    _youTubeResponse.value = null
                    errorMessage = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _youTubeResponse.value = null
                errorMessage = "Exception: ${e.message}"
            } finally {
                _isLoading.value = false // Yuklash tugadi
            }
        }
    }
}
