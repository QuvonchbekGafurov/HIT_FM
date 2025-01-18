package hitfm.uz.domainn.domain
import hitfm.uz.domainn.domain.model.YouTubeResponse
import retrofit2.Response
import javax.inject.Inject

// GetPlaylistItemsUseCase.kt
class GetPlaylistItemsUseCase @Inject constructor(
    private val youTubeRepository: YouTubeRepository
) {
    suspend operator fun invoke(): Response<YouTubeResponse> {
        return youTubeRepository.getPlaylistItems()
    }
}
