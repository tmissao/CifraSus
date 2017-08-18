package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.interfaces.Logger
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/**
 * Tests for class [LoggerModule]
 */
class LoggerModuleTest {

    val module = LoggerModule()

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun providesLogger() {
        val result = module.providesLogger()
        Assertions.assertThat(result).isInstanceOf(Logger::class.java)
    }

}