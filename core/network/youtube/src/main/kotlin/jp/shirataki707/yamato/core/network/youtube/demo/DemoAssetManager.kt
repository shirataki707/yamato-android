package jp.shirataki707.yamato.core.network.youtube.demo

import java.io.InputStream

fun interface DemoAssetManager {
    fun open(fileName: String): InputStream
}
