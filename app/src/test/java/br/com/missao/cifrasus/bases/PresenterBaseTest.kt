package br.com.missao.cifrasus.bases

import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
/**
 * Tests for class [PresenterBase]
 */
class PresenterBaseTest {

    lateinit var presenterBase: PresenterBase<Int, String>

    @Before
    fun setUp() {
        presenterBase = PresenterBase()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getView() {
        val expected = 10
        var result: Int?

        result = presenterBase.view
        Assertions.assertThat(result).isNull()

        presenterBase.view = expected
        result = presenterBase.view
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun setView() {
        val expected = 10
        var result: Int?

        presenterBase.view = expected
        result = presenterBase.view
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun getDomain() {
        val expected = "Hello"
        var result: String?

        result = presenterBase.domain
        Assertions.assertThat(result).isNull()

        presenterBase.domain = expected
        result = presenterBase.domain
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun setDomain() {
        val expected = "Hello"
        var result: String?

        presenterBase.domain = expected
        result = presenterBase.domain
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun clear() {
        var resultView: Int?
        var resultDomain: String?
        presenterBase.domain = "hello"
        presenterBase.view = 1
        presenterBase.clear()

        resultView = presenterBase.view
        resultDomain = presenterBase.domain

        Assertions.assertThat(resultDomain).isNull()
        Assertions.assertThat(resultView).isNull()

    }

}