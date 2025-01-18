package hitfm.uz.data

import hitfm.uz.domainn.domain.model.YouTubeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// YouTubeService interface
interface YouTubeService {
    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String = "snippet",
        @Query("playlistId")playlistId:String= "UUkwhyPlGmFm2o_Nhn2_QC5w",
        @Query("maxResults") maxResults: Int = 50,
        @Query("key") apiKey: String="AIzaSyB56f9JO5_7L6s57gYWixdQdvZKXruTEjs" // API kalitini shu yerda uzatish kerak
    ): Response<YouTubeResponse>
}