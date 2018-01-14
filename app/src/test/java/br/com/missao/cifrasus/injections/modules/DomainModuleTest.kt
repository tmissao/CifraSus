package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.apis.ChordsAPI
import br.com.missao.cifrasus.interfaces.Logger
import br.com.missao.cifrasus.mappers.RedditNewsMapper
import br.com.missao.cifrasus.mvps.SongMvpModelOperations
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Testes for class [DomainModule]
 */
class DomainModuleTest {

    val api: ChordsAPI = mock()
    val logger: Logger = mock()
    val mapper: RedditNewsMapper = mock()
    val module: DomainModule = DomainModule()

    @Test
    fun provides() {
        val result = module.providesSongDomain()
        Assertions.assertThat(result).isInstanceOf(SongMvpModelOperations::class.java)
    }

}