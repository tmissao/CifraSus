package br.com.missao.cifrasus.database.daos

import br.com.missao.cifrasus.bases.DaoBase
import br.com.missao.cifrasus.database.entities.Song
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort

/**
 * Song`s Dao
 */
class SongDao : DaoBase<Song>(Song::class.java) {

  fun getAllOrderByName(realm: Realm): RealmResults<Song>
      = realm.where(Song::class.java)
      .sort("name", Sort.ASCENDING)
      .findAll()
}
