package br.com.missao.cifrasus.bases

import br.com.missao.cifrasus.interfaces.DAO
import br.com.missao.cifrasus.interfaces.Entity
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import java.util.*

/**
 * Base DAO implementing common methods
 */
abstract class DaoBase<T>(val clazz: Class<T>) : DAO<T> where T : RealmObject, T : Entity {

  /**
   * Executes Realm's operations inside a transaction
   */
  inline fun transaction(realm: Realm, element: T, crossinline body: (T) -> Unit) {
    realm.executeTransaction {
      body(element)
    }
  }

  /**
   * Gets element by [id]
   */
  override fun getById(realm: Realm, id: String): T? {
    return realm.where(clazz).equalTo(Entity.ID_KEY, id).findFirst()
  }

  /**
   * Gets all elements from a class
   */
  override fun getAll(realm: Realm): RealmResults<T> {
    return realm.where(clazz).findAll()
  }

  /**
   * Inserts or updates an [element]
   */
  override fun save(realm: Realm, element: T) {
    element.id = element.id ?: UUID.randomUUID().toString()
    transaction(realm, element) { realm.insertOrUpdate(element) }
  }

  /**
   * Deletes a specific [element]
   */
  override fun delete(realm: Realm, element: T) {
    element.id?.let { delete(realm, it) }
  }

  /**
   * Deletes an element by [id]
   */
  override fun delete(realm: Realm, id: String) {
    realm.where(clazz).equalTo(Entity.ID_KEY, id).findFirst()?.apply {
      transaction(realm, this) {
        this.deleteFromRealm()
      }
    }
  }

  /**
   * Refreshes Realm instance
   */
  override fun refresh(realm: Realm) {
    realm.refresh()
  }
}