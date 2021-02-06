package pl.kserocki.githubapp.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import pl.kserocki.githubapp.factory.GithubViewModelFactory

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(githubViewModelFactory: GithubViewModelFactory): ViewModelProvider.Factory

}
