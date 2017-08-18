package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.mvps.SongMvpModelOperations
import br.com.missao.cifrasus.mvps.SongMvpPresenterOperations
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Tests for class [PresenterModule]
 */
class PresenterModuleTest {

    val domain: SongMvpModelOperations = mock()
    val module = PresenterModule()

    @Test
    fun providesMainPresenter() {
        val expected = module.providesSongPresenter(domain)
        Assertions.assertThat(expected).isInstanceOf(SongMvpPresenterOperations::class.java)
    }

}