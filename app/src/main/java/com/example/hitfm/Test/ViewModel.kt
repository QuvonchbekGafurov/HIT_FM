package com.example.hitfm
import android.graphics.pdf.PdfRenderer.Page
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hitfm.apimodel.Item
import com.example.hitfm.apimodel.PageInfo
import com.example.hitfm.apimodel.YouTubeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class YouTubeViewModel @Inject constructor(
    private val youTubeRepository: YouTubeRepository
) : ViewModel() {

    var playlistItems: List<Item>? = null
    var errorMessage: String? = null

    // Playlist elementlarini olish
    fun getPlaylistItems(playlistId: String, apiKey: String) {
        viewModelScope.launch {
            val response: Response<YouTubeResponse> = youTubeRepository.getPlaylistItems(playlistId, apiKey)
            if (response.isSuccessful) {
                playlistItems = response.body()?.items
            } else {
                errorMessage = "Error: ${response.message()}"
            }
        }
    }
}
