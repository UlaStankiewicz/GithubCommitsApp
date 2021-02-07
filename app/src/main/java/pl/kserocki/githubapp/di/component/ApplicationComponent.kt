package pl.kserocki.githubapp.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pl.kserocki.githubapp.di.module.ActivityModule
import pl.kserocki.githubapp.di.module.ApplicationModule
import pl.kserocki.githubapp.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        ApplicationModule::class]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(instance: DaggerApplication)
}
