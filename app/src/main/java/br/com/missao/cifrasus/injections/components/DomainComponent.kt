package br.com.missao.cifrasus.injections.components


import br.com.missao.cifrasus.injections.modules.*
import br.com.missao.cifrasus.mvps.SongMvpModelOperations
import dagger.Component
import javax.inject.Singleton

/**
 * Application's Domain Component
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RetrofitModule::class, ApiModule::class, LoggerModule::class,
    MapperModule::class, DomainModule::class, RealmModule::class, DaoModule::class))
interface DomainComponent {

  /**
   * Obtains [SongMvpModelOperations]
   */
  fun getSongMvpDomainOperations(): SongMvpModelOperations
}
