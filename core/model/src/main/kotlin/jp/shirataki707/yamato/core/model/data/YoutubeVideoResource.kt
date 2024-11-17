package jp.shirataki707.yamato.core.model.data

data class YoutubeVideoResource(
    val videoTitle: String,
    val channelTitle: String,
    val description: String,
    val thumbnailUrl: String,
    val videoId: String,
    val publishedAt: String,
)
