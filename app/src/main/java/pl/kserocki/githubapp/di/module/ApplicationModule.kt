package pl.kserocki.githubapp.di.module

import android.app.Application
import androidx.room.Room
import pl.kserocki.githubapp.db.callback.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.NonNull
import okhttp3.OkHttpClient
import pl.kserocki.githubapp.db.api.GithubAPI
import pl.kserocki.githubapp.db.dao.GithubCommitDao
import pl.kserocki.githubapp.db.dao.GithubRepoDao
import pl.kserocki.githubapp.db.database.ApplicationDatabase
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
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
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
            "GithubCommitsApp.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubRepoDao(@NonNull applicationDatabase: ApplicationDatabase): GithubRepoDao {
        return applicationDatabase.githubRepoDao()
    }

    @Provides
    @Singleton
    fun provideGithubCommitDao(@NonNull applicationDatabase: ApplicationDatabase): GithubCommitDao {
        return applicationDatabase.githubCommitDao()
    }

}
