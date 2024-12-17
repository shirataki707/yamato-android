package jp.shirataki707.yamato.core.common.network

import java.io.InputStream

fun interface DemoAssetManager {
    fun open(fileName: String): InputStream
}
