package hitfm.uz.data.model

import java.util.UUID

data class VideoUiState(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val author: String,
    val authorImageResId: Int,
    val viewsCount: String,
    val publishDate: String,
    val thumbnailResId: Any,
    val youtubevideoid:String?=""
)
