package br.com.missao.cifrasus.injections.modules

import android.content.Context
import com.nhaarman.mockito_kotlin.mock
import okhttp3.OkHttpClient
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

/**
 * Tests for [RetrofitModule]
 */
class RetrofitModuleTest {

    lateinit var module: RetrofitModule

    @Before
    fun setUp() {
        module = RetrofitModule()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun providesBaseUrl() {
        val expected = "https://www.reddit.com"
        val result = module.providesBaseUrl()
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun providesOkHttpClient() {
        val context: Context = mock()
        val result = module.providesOkHttpClient(context)
        Assertions.assertThat(result).isInstanceOf(OkHttpClient::class.java)
    }

    @Test
    fun providesRetrofit() {
        val base = "https://www.reddit.com"
        val httpClient: OkHttpClient = mock()
        val result = module.providesRetrofit(httpClient, base)
        Assertions.assertThat(result).isInstanceOf(Retrofit::class.java)
    }

}