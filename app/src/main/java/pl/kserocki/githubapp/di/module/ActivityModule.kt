package pl.kserocki.githubapp.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kserocki.githubapp.ui.details.SearchActivity
import pl.kserocki.githubapp.ui.repo.RepoActivity
import pl.kserocki.githubapp.ui.splash.SplashActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeDetailsActivity(): SearchActivity

    @ContributesAndroidInjector
    internal abstract fun contributeRepoActivity(): RepoActivity

    @ContributesAndroidInjector
    internal abstract fun contributeSplashActivity(): SplashActivity

}
