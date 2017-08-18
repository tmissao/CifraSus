package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.apis.RedditAPI
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit

/**
 * Test for [ApiModule]
 */
class ApiModuleTest {

    lateinit var module: ApiModule

    @Before
    fun setUp() {
        module = ApiModule()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun providesRedditApi() {
        val api: RedditAPI = mock()
        val retrofit: Retrofit = mock()

        whenever(retrofit.create((RedditAPI::class.java))).thenReturn(api)

        val result = module.providesRedditApi(retrofit)
        Assertions.assertThat(result).isInstanceOf(RedditAPI::class.java)
    }

}