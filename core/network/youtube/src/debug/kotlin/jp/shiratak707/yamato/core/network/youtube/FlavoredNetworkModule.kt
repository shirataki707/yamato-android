package jp.shiratak707.yamato.core.network.youtube

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.shirataki707.yamato.core.network.youtube.YoutubeDataSource
import jp.shirataki707.yamato.core.network.youtube.demo.DemoYoutubeDataSource

@Module
@InstallIn(SingletonComponent::class)
internal interface FlavoredNetworkModule {

    @Binds
    fun binds(impl: DemoYoutubeDataSource): YoutubeDataSource
    // TODO: releaseにすると自動で切り替わるので、開発が終わったら以下は削除する
//    fun binds(impl: YoutubeApiClient): YoutubeDataSource
}
