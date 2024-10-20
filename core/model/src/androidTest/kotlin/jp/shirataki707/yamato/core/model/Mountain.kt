package jp.shirataki707.yamato.core.model

data class Mountain(
    val id: Int,
    val name: String,
    val imageName: String,
    val shortDescription: String,
    val longDescription: String,
    val elevation: Int,
    val latitude: Double,
    val longitude: Double,
    val isClimbed: Boolean,
)
