package hitfm.uz.domainn.domain

import hitfm.uz.domainn.domain.model.YouTubeResponse
import retrofit2.Response

interface YouTubeRepository {
    suspend fun getPlaylistItems(): Response<YouTubeResponse>
}