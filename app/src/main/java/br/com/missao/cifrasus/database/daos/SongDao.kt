package br.com.missao.cifrasus.database.daos

import br.com.missao.cifrasus.bases.DaoBase
import br.com.missao.cifrasus.database.entities.Song

/**
 * Song`s Dao
 */
class SongDao : DaoBase<Song>(Song::class.java)
