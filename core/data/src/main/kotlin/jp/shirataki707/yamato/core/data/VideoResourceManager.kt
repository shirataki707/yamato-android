package jp.shirataki707.yamato.core.data

import jp.shirataki707.yamato.core.model.data.video.VideoResources.VideoCarouselBlockType

// TODO: NetworkまたはLocalからHome画面に表示する動画リソースの種類を取得する
object VideoResourceManager {

    fun getVideoCarouselBlockTypeList(): List<VideoCarouselBlockType> {
        return listOf(
            VideoCarouselBlockType.Recommended,
            VideoCarouselBlockType.Popular,
            VideoCarouselBlockType.Latest,
            VideoCarouselBlockType.Mountain("富士山"),
            VideoCarouselBlockType.Mountain("立山"),
            VideoCarouselBlockType.Mountain("白山"),
            VideoCarouselBlockType.Channel("UCChGo50REPrjKh231fy3Meg"),
            VideoCarouselBlockType.Channel("UCsK4ocOQQRKLTE0AHoH-Gsg"),
            VideoCarouselBlockType.Channel("UC1Q990j5wBf8DsUAmFHHogg"),
        )
    }
}
