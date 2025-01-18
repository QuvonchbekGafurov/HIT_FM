package hitfm.uz.presentor.presentation.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hitfm.uz.domainn.domain.model.YouTubeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import hitfm.uz.domainn.domain.GetPlaylistItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YouTubeViewModel @Inject constructor(
    private val getPlaylistItemsUseCase: hitfm.uz.domainn.domain.GetPlaylistItemsUseCase
) : ViewModel() {

    private val _youTubeResponse = MutableStateFlow<hitfm.uz.domainn.domain.model.YouTubeResponse?>(null)
    val youTubeResponse: StateFlow<hitfm.uz.domainn.domain.model.YouTubeResponse?> = _youTubeResponse

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    var errorMessage: String? = null

    fun getPlaylistItems() {
        viewModelScope.launch {
            _isLoading.value = true // Yuklash boshlandi
            try {
                val response = getPlaylistItemsUseCase.invoke() // UseCase'dan foydalanish
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
