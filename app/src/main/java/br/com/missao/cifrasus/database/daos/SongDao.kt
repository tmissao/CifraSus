package br.com.missao.cifrasus.database.daos

import br.com.missao.cifrasus.bases.DaoBase
import br.com.missao.cifrasus.database.entities.Song
import io.realm.Realm

/**
 * Song`s Dao
 */
class SongDao(realm: Realm) : DaoBase<Song>(realm, Song::class.java)
