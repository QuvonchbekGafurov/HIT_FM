package com.example.hitfm.presentation.repository

import com.example.hitfm.data.YouTubeService
import com.example.hitfm.model.YouTubeResponse
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
