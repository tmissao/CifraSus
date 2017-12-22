package br.com.missao.cifrasus.injections.components

import br.com.missao.cifrasus.injections.modules.RealmModule
import dagger.Component
import io.realm.Realm

import javax.inject.Singleton

/**
 * Realm`s component
 */
@Singleton
@Component(modules = [RealmModule::class])
interface RealmComponent {

  fun getRealm(): Realm
}