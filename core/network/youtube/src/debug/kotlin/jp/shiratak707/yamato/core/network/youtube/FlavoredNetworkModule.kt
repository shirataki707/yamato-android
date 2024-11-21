package jp.shiratak707.yamato.core.network.youtube

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.shirataki707.yamato.core.network.youtube.YoutubeDataSource
import jp.shirataki707.yamato.core.network.youtube.demo.DemoYoutubeDataSource
import jp.shirataki707.yamato.core.network.youtube.retorofit.YoutubeApiClient

@Module
@InstallIn(SingletonComponent::class)
internal interface FlavoredNetworkModule {

    @Binds
//    fun binds(impl: DemoYoutubeDataSource): YoutubeDataSource
    fun binds(impl: YoutubeApiClient): YoutubeDataSource
}
