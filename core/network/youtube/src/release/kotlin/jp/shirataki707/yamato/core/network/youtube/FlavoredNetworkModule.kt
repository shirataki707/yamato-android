package jp.shirataki707.yamato.core.network.youtube

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.shirataki707.yamato.core.network.youtube.retorofit.YoutubeApiClient

@Module
@InstallIn(SingletonComponent::class)
internal interface FlavoredNetworkModule {

    @Binds
    fun binds(impl: YoutubeApiClient): YoutubeDataSource
}
