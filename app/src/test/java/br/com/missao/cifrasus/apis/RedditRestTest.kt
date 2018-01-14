package br.com.missao.cifrasus.apis


import br.com.missao.cifrasus.model.dtos.RedditNewsResponse
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

/**
 * Testes para a classe [ChordsRest]
 */
class RedditRestTest {

    lateinit var api: ChordsAPI
    lateinit var rest: ChordsRest
    lateinit var retrofit: Retrofit

    @Before
    fun setUp() {
        api = mock()
        retrofit = mock()
        whenever(retrofit.create(ChordsAPI::class.java)).doReturn(api)

        rest = ChordsRest(retrofit)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getTopNews() {
        val limit = 10
        val after = 5
        val response: RedditNewsResponse = mock()
        whenever(api.getTopNews(after, limit)).doReturn(Observable.just(response))

        val result = rest.getTopNews(after, limit).blockingFirst()
        Assertions.assertThat(result).isEqualTo(response)
    }

}