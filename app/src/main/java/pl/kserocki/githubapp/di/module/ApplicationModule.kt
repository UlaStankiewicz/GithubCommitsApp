package pl.kserocki.githubapp.di.module

import android.app.Application
import androidx.room.Room
import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.NonNull
import okhttp3.OkHttpClient
import pl.kserocki.githubapp.api.GithubAPI
import pl.kserocki.githubapp.api.dao.GithubRepoDao
import pl.kserocki.githubapp.api.database.ApplicationDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubAPI(@NonNull retrofit: Retrofit): GithubAPI {
        return retrofit.create(GithubAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideApplicationDatabase(@NonNull application: Application): ApplicationDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            ApplicationDatabase::class.java,
            "GithubCommitsApp.db")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubRepoDao(@NonNull applicationDatabase: ApplicationDatabase): GithubRepoDao {
        return applicationDatabase.githubRepoDao()
    }

}
