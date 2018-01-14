package br.com.missao.cifrasus.apis


import br.com.missao.cifrasus.model.dtos.RequestChord
import io.reactivex.Observable
import retrofit2.Retrofit

/**
 * Chords Rest API
 */
class ChordsRest(retrofit: Retrofit) : ChordsAPI {

  /**
   * API to perform request
   */
  private val api: ChordsAPI = retrofit.create(ChordsAPI::class.java)

  override fun saveChord(body: RequestChord): Observable<RequestChord> {
    return api.saveChord(body)
  }
}