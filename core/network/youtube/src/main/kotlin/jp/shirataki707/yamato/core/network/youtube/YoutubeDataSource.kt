package jp.shirataki707.yamato.core.network.youtube

import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiRequest
import jp.shirataki707.yamato.core.network.youtube.model.YoutubeSearchApiResponse

interface YoutubeDataSource {
    suspend fun getVideoResources(request: YoutubeSearchApiRequest): YoutubeSearchApiResponse
}
