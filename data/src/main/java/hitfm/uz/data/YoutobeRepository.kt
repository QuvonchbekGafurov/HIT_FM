package hitfm.uz.data



import retrofit2.Response
import javax.inject.Inject

class YouTubeRepositoryImpl @Inject constructor(
    private val youTubeService: YouTubeService
) : hitfm.uz.domainn.domain.YouTubeRepository {
    override suspend fun getPlaylistItems(): Response<hitfm.uz.domainn.domain.model.YouTubeResponse> {
        return youTubeService.getPlaylistItems()
    }
}
