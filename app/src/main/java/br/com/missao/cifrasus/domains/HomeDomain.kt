package br.com.missao.cifrasus.domains

import br.com.missao.cifrasus.apis.ChordsAPI
import br.com.missao.cifrasus.bases.DomainBase
import br.com.missao.cifrasus.database.daos.SongDao
import br.com.missao.cifrasus.exceptions.NoConnectivityException
import br.com.missao.cifrasus.interfaces.Logger
import br.com.missao.cifrasus.mappers.ChordMapper
import br.com.missao.cifrasus.mappers.SongMapper
import br.com.missao.cifrasus.mvps.HomeMvpModelOperations
import br.com.missao.cifrasus.mvps.HomeMvpRequiredPresenterOperations
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Home's Domain
 */
class HomeDomain(private val songDao: SongDao, private val songMapper: SongMapper,
    private val chordMapper: ChordMapper, private val api: ChordsAPI, private val logger: Logger) :
    DomainBase<HomeMvpRequiredPresenterOperations>(), HomeMvpModelOperations {

  /**
   * Class's TAG
   */
  val TAG = ListSongDomain::class.java.simpleName

  override fun addChord(url: String) {
    Observable.defer { api.saveChord(chordMapper.toChordRequestDTO(url)) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { },
            {
              when (it) {
                is NoConnectivityException -> presenter?.onAddChordError(true)
                else                       -> {
                  logger.e(TAG, it); presenter?.onAddChordError(false)
                }
              }
            }
        )
  }

  override fun setPresenter(presenter: HomeMvpRequiredPresenterOperations) {
    super.presenter = presenter
  }

}