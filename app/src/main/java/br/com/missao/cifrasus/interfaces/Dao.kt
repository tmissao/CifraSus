package br.com.missao.cifrasus.interfaces

import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults

/**
 * Common repository methods
 */
interface DAO<T> where T : RealmObject, T : Entity {

  /**
   * Gets element with [id]
   */
  fun getById(realm: Realm, id: String): T?

  /**
   * Gets all elements
   */
  fun getAll(realm: Realm): RealmResults<T>

  /**
   * Inserts or updates [element]
   */
  fun save(realm: Realm, element: T)

  /**
   * Removes [element]
   */
  fun delete(realm: Realm, element: T)

  /**
   * Removes element with [id]
   */
  fun delete(realm: Realm, id: String)

  /**
   * Refreshes Realm
   */
  fun refresh(realm: Realm)

}