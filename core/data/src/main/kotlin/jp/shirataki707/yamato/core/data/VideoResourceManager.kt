package jp.shirataki707.yamato.core.data

// TODO: NetworkまたはLocalからHome画面に表示する動画リソースの種類を取得する
object VideoResourceManager {

    fun getVideoCarouselBlockTypeList(): List<VideoCarouselBlockType> {
        return listOf(
            VideoCarouselBlockType.Recommended,
            VideoCarouselBlockType.Popular,
            VideoCarouselBlockType.New,
            VideoCarouselBlockType.Mountain("富士山"),
            VideoCarouselBlockType.Mountain("立山"),
            VideoCarouselBlockType.Mountain("白山"),
            VideoCarouselBlockType.Channel("登山チャンネル"),
            VideoCarouselBlockType.Channel("アウトドアチャンネル"),
            VideoCarouselBlockType.Channel("キャンプチャンネル"),
        )
    }

    sealed interface VideoCarouselBlockType {
        data object Recommended : VideoCarouselBlockType
        data object Popular : VideoCarouselBlockType
        data object New : VideoCarouselBlockType
        data class Mountain(val mountainName: String) : VideoCarouselBlockType
        data class Channel(val channelName: String) : VideoCarouselBlockType
    }
}