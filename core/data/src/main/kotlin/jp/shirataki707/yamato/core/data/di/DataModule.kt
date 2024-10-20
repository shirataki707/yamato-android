package jp.shirataki707.yamato.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.shirataki707.yamato.core.data.repository.MountainRepository
import jp.shirataki707.yamato.core.data.repository.MountainRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindMountainRepository(
        mountainRepository: MountainRepositoryImpl
    ): MountainRepository
}
