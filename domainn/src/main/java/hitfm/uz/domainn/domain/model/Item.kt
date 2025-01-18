package hitfm.uz.domainn.domain.model

data class Item(
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet
)