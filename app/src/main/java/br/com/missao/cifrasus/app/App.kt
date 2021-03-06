package br.com.missao.cifrasus.app

import android.app.Application
import br.com.missao.cifrasus.injections.components.AppComponent
import br.com.missao.cifrasus.injections.components.DaggerAppComponent
import br.com.missao.cifrasus.injections.components.DaggerViewComponent
import br.com.missao.cifrasus.injections.components.ViewComponent
import br.com.missao.cifrasus.injections.modules.AppModule
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Android's application class
 */
open class App : Application() {
  val instance = this

  val appModule = AppModule(instance)

  val appComponent: AppComponent by lazy {
    DaggerAppComponent.builder().appModule(appModule).build()
  }


  private val viewComponent: ViewComponent by lazy {
    DaggerViewComponent.builder().appModule(appModule).build()
  }

  override fun onCreate() {
    super.onCreate()
    this.setupRealm()
  }

  private fun setupRealm() {
    Realm.init(this)

    RealmConfiguration.Builder()
        .name(Realm.DEFAULT_REALM_NAME)
        .schemaVersion(0)
        .deleteRealmIfMigrationNeeded()
        .build().apply { Realm.setDefaultConfiguration(this) }
  }

  /**
   * Obtains DaggerViewComponent for injection
   */
  open fun getDaggerViewComponent() = viewComponent

}
