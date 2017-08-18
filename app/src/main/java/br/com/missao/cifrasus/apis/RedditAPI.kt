package br.com.missao.cifrasus.apis


import br.com.missao.cifrasus.model.dtos.RedditNewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Reddit Rest API
 */
interface RedditAPI {

    /**
     * Gets the most relevant news from Reddit
     * @param limit Quantity of news desired
     * @param after Quantity of firsts news to be ignored
     */
    @GET("top.json")
    fun getTopNews(@Query("after") after: Int,
                   @Query("limit") limit: Int): Observable<RedditNewsResponse>

}