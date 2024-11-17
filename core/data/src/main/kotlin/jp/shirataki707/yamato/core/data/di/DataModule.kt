package jp.shirataki707.yamato.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.shirataki707.yamato.core.data.repository.MountainRepository
import jp.shirataki707.yamato.core.data.repository.MountainRepositoryImpl
import jp.shirataki707.yamato.core.data.repository.YoutubeVideoRepository
import jp.shirataki707.yamato.core.data.repository.YoutubeVideoRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindMountainRepository(
        mountainRepository: MountainRepositoryImpl,
    ): MountainRepository

    @Binds
    internal abstract fun bindSearchVideoRepository(
        youtubeVideoRepository: YoutubeVideoRepositoryImpl,
    ): YoutubeVideoRepository
}
