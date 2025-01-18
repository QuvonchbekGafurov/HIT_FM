package hitfm.uz.data.di

import hitfm.uz.data.YouTubeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hitfm.uz.data.YouTubeRepositoryImpl
import hitfm.uz.domainn.domain.YouTubeRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    @Provides
    fun provideYouTubeService(retrofit: Retrofit): YouTubeService {
        return retrofit.create(YouTubeService::class.java)
    }

    @Provides
    fun provideYouTubeRepository(youTubeService: YouTubeService): YouTubeRepository {
        return YouTubeRepositoryImpl(youTubeService)
    }

}
