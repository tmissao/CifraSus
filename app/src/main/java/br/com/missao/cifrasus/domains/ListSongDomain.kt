package br.com.missao.cifrasus.domains

import br.com.missao.cifrasus.bases.DomainBase
import br.com.missao.cifrasus.database.daos.SongDao
import br.com.missao.cifrasus.interfaces.Logger
import br.com.missao.cifrasus.mappers.SongMapper
import br.com.missao.cifrasus.mvps.ListSongMvpModelOperations
import br.com.missao.cifrasus.mvps.ListSongMvpRequiredPresenterOperations
import br.com.missao.cifrasus.mvps.SongMvpModelOperations
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

/**
 * Resolves [SongMvpModelOperations]
 */
class ListSongDomain(private val songDao: SongDao, private val songMapper: SongMapper,
    private val logger: Logger) :
    DomainBase<ListSongMvpRequiredPresenterOperations>(),
    ListSongMvpModelOperations {

  /**
   * Class's TAG
   */
  val TAG = ListSongDomain::class.java.simpleName

  override fun getSongs() {
    lateinit var realm: Realm

    Observable.defer {
      realm = Realm.getDefaultInstance()
      Observable.fromIterable(songDao.getAllOrderByName(realm))
    }
        .subscribeOn(Schedulers.io())
        .map(songMapper::toInfoWrapper)
        .doOnTerminate { realm.close() }
        .toList()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { presenter?.onGetSongs(it) },
            { logger.e(TAG, it) }
        )

  }

  override fun setPresenter(presenter: ListSongMvpRequiredPresenterOperations) {
    super.presenter = presenter
  }

}