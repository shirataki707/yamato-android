package jp.shirataki707.yamato.core.common.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val yamatoDispatcher: YamatoDispatchers)

enum class YamatoDispatchers {
    Default,
    IO,
}

@Module
@InstallIn(SingletonComponent::class)
object YamatoDispatchersModule {

    @Dispatcher(YamatoDispatchers.Default)
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Dispatcher(YamatoDispatchers.IO)
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
