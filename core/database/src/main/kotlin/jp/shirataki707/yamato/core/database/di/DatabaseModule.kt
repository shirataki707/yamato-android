package jp.shirataki707.yamato.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.shirataki707.yamato.core.database.YamatoDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context,
    ): YamatoDatabase = Room.databaseBuilder(
        context,
        YamatoDatabase::class.java,
        "yamato-database",
    ).createFromAsset("database/japan_100_mountains.db").build()
}
