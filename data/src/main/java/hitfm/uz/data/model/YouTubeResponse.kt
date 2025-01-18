package hitfm.uz.data.model

data class YouTubeResponse(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val pageInfo: PageInfo
)