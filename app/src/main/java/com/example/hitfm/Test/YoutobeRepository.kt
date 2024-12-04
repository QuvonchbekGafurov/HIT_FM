package com.example.hitfm.Test

import com.example.hitfm.Test.apimodel.YouTubeResponse
import com.example.hitfm.Test.data.YouTubeService
import javax.inject.Inject

import retrofit2.Response

class YouTubeRepository @Inject constructor(
    private val youTubeService: YouTubeService
) {

    // Playlist elementlarini olish
    suspend fun getPlaylistItems(): Response<YouTubeResponse> {
        return youTubeService.getPlaylistItems()
    }
}
