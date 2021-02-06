package pl.kserocki.githubapp

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pl.kserocki.githubapp.di.component.DaggerApplicationComponent

class GithubApplication : DaggerApplication() {

    private val appComponent = DaggerApplicationComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }
}