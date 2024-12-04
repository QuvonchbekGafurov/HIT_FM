package com.example.hitfm

import com.example.hitfm.apimodel.YouTubeResponse
import com.example.hitfm.data.YouTubeService
import retrofit2.Call
import javax.inject.Inject

import retrofit2.Response

class YouTubeRepository @Inject constructor(
    private val youTubeService: YouTubeService
) {

    // Playlist elementlarini olish
    suspend fun getPlaylistItems(playlistId: String, apiKey: String): Response<YouTubeResponse> {
        return youTubeService.getPlaylistItems(playlistId, apiKey)
    }
}
