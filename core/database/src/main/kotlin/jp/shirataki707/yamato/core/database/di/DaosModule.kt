package jp.shirataki707.yamato.core.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.shirataki707.yamato.core.database.YamatoDatabase
import jp.shirataki707.yamato.core.database.dao.MountainDao

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun ProvidesMountainDao(
        database: YamatoDatabase
    ): MountainDao = database.mountainDao()
}