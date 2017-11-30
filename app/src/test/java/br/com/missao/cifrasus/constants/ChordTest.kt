package br.com.missao.cifrasus.constants

import org.junit.Test

import org.assertj.core.api.Assertions

/**
 * Test for [Chord] class
 */
class ChordTest {

  @Test
  fun getValue() {
    Assertions.assertThat(Chord.getByValue("C")).isEqualTo(Chord.C)
    Assertions.assertThat(Chord.getByValue("c")).isEqualTo(Chord.C)
    Assertions.assertThat(Chord.getByValue("D")).isEqualTo(Chord.D)
    Assertions.assertThat(Chord.getByValue("E")).isEqualTo(Chord.E)
    Assertions.assertThat(Chord.getByValue("F")).isEqualTo(Chord.F)
    Assertions.assertThat(Chord.getByValue("G")).isEqualTo(Chord.G)
    Assertions.assertThat(Chord.getByValue("A")).isEqualTo(Chord.A)
    Assertions.assertThat(Chord.getByValue("B")).isEqualTo(Chord.B)


    Assertions.assertThat(Chord.getByValue("C#")).isEqualTo(Chord.Csus)
    Assertions.assertThat(Chord.getByValue("c#")).isEqualTo(Chord.Csus)
    Assertions.assertThat(Chord.getByValue("db")).isEqualTo(Chord.Csus)
    Assertions.assertThat(Chord.getByValue("Db")).isEqualTo(Chord.Csus)
    Assertions.assertThat(Chord.getByValue("DB")).isEqualTo(Chord.Csus)

    Assertions.assertThat(Chord.getByValue("D#")).isEqualTo(Chord.Dsus)
    Assertions.assertThat(Chord.getByValue("Eb")).isEqualTo(Chord.Dsus)

    Assertions.assertThat(Chord.getByValue("E#")).isEqualTo(Chord.F)
    Assertions.assertThat(Chord.getByValue("Fb")).isEqualTo(Chord.E)

    Assertions.assertThat(Chord.getByValue("F#")).isEqualTo(Chord.Fsus)
    Assertions.assertThat(Chord.getByValue("Gb")).isEqualTo(Chord.Fsus)

    Assertions.assertThat(Chord.getByValue("G#")).isEqualTo(Chord.Gsus)
    Assertions.assertThat(Chord.getByValue("Ab")).isEqualTo(Chord.Gsus)

    Assertions.assertThat(Chord.getByValue("A#")).isEqualTo(Chord.Asus)
    Assertions.assertThat(Chord.getByValue("Bb")).isEqualTo(Chord.Asus)

    Assertions.assertThat(Chord.getByValue("B#")).isEqualTo(Chord.C)
    Assertions.assertThat(Chord.getByValue("Cb")).isEqualTo(Chord.B)
  }

  @Test
  fun change() {
    Assertions.assertThat(Chord.change(Chord.C, degree = 12)).isEqualTo(Chord.C)
    Assertions.assertThat(Chord.change(Chord.C, degree = -12)).isEqualTo(Chord.C)
    Assertions.assertThat(Chord.change(Chord.C, degree = 2)).isEqualTo(Chord.D)
    Assertions.assertThat(Chord.change(Chord.C, degree = -2)).isEqualTo(Chord.Asus)
    Assertions.assertThat(Chord.change(Chord.B, degree = -1)).isEqualTo(Chord.Asus)
    Assertions.assertThat(Chord.change(Chord.B, degree = 1)).isEqualTo(Chord.C)
    Assertions.assertThat(Chord.change(Chord.B, degree = 2)).isEqualTo(Chord.Csus)
    Assertions.assertThat(Chord.change(Chord.Csus, degree = 3)).isEqualTo(Chord.E)
    Assertions.assertThat(Chord.change(Chord.Csus, degree = -3)).isEqualTo(Chord.Asus)
    Assertions.assertThat(Chord.change(Chord.D, degree = 7)).isEqualTo(Chord.A)
    Assertions.assertThat(Chord.change(Chord.D, degree = -7)).isEqualTo(Chord.G)
  }

  @Test
  fun difference() {
    Assertions.assertThat(Chord.C.difference(Chord.C)).isEqualTo(0)
    Assertions.assertThat(Chord.D.difference(Chord.C)).isEqualTo(-2)
    Assertions.assertThat(Chord.Asus.difference(Chord.C)).isEqualTo(-10)
    Assertions.assertThat(Chord.Asus.difference(Chord.B)).isEqualTo(1)
    Assertions.assertThat(Chord.C.difference(Chord.B)).isEqualTo(11)
    Assertions.assertThat(Chord.Csus.difference(Chord.B)).isEqualTo(10)
    Assertions.assertThat(Chord.E.difference(Chord.Csus)).isEqualTo(-3)
    Assertions.assertThat(Chord.Asus.difference(Chord.Csus)).isEqualTo(-9)
  }

  @Test
  fun baseChord() {
    Assertions.assertThat(Chord.baseChord("Am7")).isEqualTo(Chord.A)
    Assertions.assertThat(Chord.baseChord("A#m7")).isEqualTo(Chord.Asus)
    Assertions.assertThat(Chord.baseChord("Abm7")).isEqualTo(Chord.Gsus)
  }

  @Test
  fun notBaseChord() {
    Assertions.assertThat(Chord.notBaseChord("Am7")).isEqualTo("m7")
    Assertions.assertThat(Chord.notBaseChord("A#m7")).isEqualTo("m7")
    Assertions.assertThat(Chord.notBaseChord("Ab7")).isEqualTo("7")
    Assertions.assertThat(Chord.notBaseChord("Abm7")).isEqualTo("m7")
    Assertions.assertThat(Chord.notBaseChord("A")).isEmpty()
  }

}