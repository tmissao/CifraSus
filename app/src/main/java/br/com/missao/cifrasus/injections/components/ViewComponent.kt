package br.com.missao.cifrasus.injections.components


import br.com.missao.cifrasus.activities.SongActivity
import br.com.missao.cifrasus.fragments.SongsFragment
import br.com.missao.cifrasus.injections.modules.*
import dagger.Component
import javax.inject.Singleton

/**
 * Application's view component
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RetrofitModule::class, ApiModule::class, LoggerModule::class,
    MapperModule::class, DomainModule::class, RealmModule::class, DaoModule::class, PresenterModule::class))
interface ViewComponent {

  /**
   * Injects in [SongActivity] its dependencies
   */
  fun inject(activity: SongActivity)

  /**
   * Injects in [SongsFragment] its dependencies
   */
  fun inject(fragment: SongsFragment)
}