package com.example.hitfm.apimodel

data class YouTubeResponse(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val pageInfo: PageInfo
)