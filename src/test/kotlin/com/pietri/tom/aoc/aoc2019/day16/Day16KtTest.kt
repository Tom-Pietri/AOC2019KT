package com.pietri.tom.aoc.aoc2019.day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

internal class Day16KtTest {

    @Test
    fun `toListOfInt of 12345678 listOf(1, 2, 3, 4, 5, 6, 7, 8)`() {
        val input = "12345678"
        assertThat(toListOfInt(input)).isEqualTo(listOf(1, 2, 3, 4, 5, 6, 7, 8))
    }

    @Test
    fun `12345678 next phase should be 48226158`() {
        val signal = listOf(1, 2, 3, 4, 5, 6, 7, 8)
        assertThat(nextPhase(signal)).isEqualTo(listOf(4, 8, 2, 2, 6, 1, 5, 8))
    }

    @Test
    fun `48226158 next phase should be 34040438`() {
        val signal = listOf(4, 8, 2, 2, 6, 1, 5, 8)
        assertThat(nextPhase(signal)).isEqualTo(listOf(3, 4, 0, 4, 0, 4, 3, 8))
    }

    @Test
    fun `34040438 next phase should be 03415518`() {
        val signal = listOf(3, 4, 0, 4, 0, 4, 3, 8)
        assertThat(nextPhase(signal)).isEqualTo(listOf(0, 3, 4, 1, 5, 5, 1, 8))
    }

    @Test
    fun `Part 1 first example after 100 phase should get 24176176`() {
        val signal = toListOfInt("80871224585914546619083218645595")
        assertThat(resultAfterPhases(signal)).isEqualTo("24176176")
    }

    @Test
    fun `Part 1 example 2 after 100 phase should get 73745418`() {
        val signal = toListOfInt("19617804207202209144916044189917")
        assertThat(resultAfterPhases(signal)).isEqualTo("73745418")
    }

    @Test
    fun `Part 1 example 3 after 100 phase should get 52432133`() {
        val signal = toListOfInt("69317163492948606335995924319873")
        assertThat(resultAfterPhases(signal)).isEqualTo("52432133")
    }

    @Test
    fun `Part 1 solution should be 52611030`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day16/day16.input").readLines(Charsets.UTF_8).get(0)
        assertThat(computeFirstSolution(input)).isEqualTo("52611030")
    }

    @Test
    fun `Part 2 solution should be ?`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day16/day16.input").readLines(Charsets.UTF_8).get(0)
        assertThat(computeSecondSolution(input)).isEqualTo("?")
    }
}