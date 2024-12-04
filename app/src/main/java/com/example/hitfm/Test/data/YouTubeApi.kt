package com.example.hitfm.data

import com.example.hitfm.apimodel.YouTubeResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeService {
    @GET("playlistItems")
    fun getPlaylistItems(
        @Query("part") part: String = "snippet",
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: Int = 50
    ): Response<YouTubeResponse>
}