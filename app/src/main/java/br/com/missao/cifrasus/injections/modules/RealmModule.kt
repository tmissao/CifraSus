package br.com.missao.cifrasus.injections.modules

import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

/**
 * Realm's Module
 */
@Module
class RealmModule {

  /**
   * Provides [Realm]
   */
  @Provides
  @Singleton
  fun providesRealm() = Realm.getDefaultInstance()
}