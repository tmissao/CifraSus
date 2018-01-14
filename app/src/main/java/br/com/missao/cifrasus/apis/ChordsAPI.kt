package br.com.missao.cifrasus.apis


import br.com.missao.cifrasus.model.dtos.RequestChord
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Chords Rest API
 */
interface ChordsAPI {

  /**
   * Gets a chord from a specific URL
   */
  @POST("cifras")
  fun saveChord(@Body body: RequestChord): Observable<RequestChord>

}