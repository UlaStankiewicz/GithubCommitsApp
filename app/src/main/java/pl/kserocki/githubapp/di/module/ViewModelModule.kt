package pl.kserocki.githubapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kserocki.githubapp.di.key.ViewModelKey
import pl.kserocki.githubapp.factory.GithubViewModelFactory
import pl.kserocki.githubapp.ui.repo.RepoViewModel
import pl.kserocki.githubapp.ui.search.SearchViewModel

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(githubViewModelFactory: GithubViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel::class)
    internal abstract fun bindRepoViewModel(repoViewModel: RepoViewModel): ViewModel

}
