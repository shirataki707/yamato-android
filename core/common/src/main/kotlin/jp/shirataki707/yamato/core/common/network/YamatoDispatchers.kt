package jp.shirataki707.yamato.core.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val yamatoDispatcher: YamatoDispatchers)

enum class YamatoDispatchers {
    Default,
    IO,
}
