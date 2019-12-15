package com.pietri.tom.aoc.aoc2019.day14

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File


internal class Day14KtTest {

    @Test
    fun `separate 10 ORE - 10A should give 10ore and 10A`() {
        val input = "10 ORE => 10 A"
        assertThat(separateProducedAndRequired(input)).isEqualTo(Pair("10 ORE ", " 10 A"))
    }

    @Test
    fun `separate produced and required`() {
        val input = "7 A, 1 B => 1 C"
        assertThat(separateProducedAndRequired(input)).isEqualTo(Pair("7 A, 1 B ", " 1 C"))
    }

    @Test
    fun `transform string to paired should return 3, A`() {
        val input = "3 A"
        assertThat(transformToChemical(input)).isEqualTo(ChemicalWithQuantity(3, "A"))
    }

    @Test
    fun `Part 1 example 1 should give 31`() {
        val input = File("./src/test/kotlin/com/pietri/tom/aoc/aoc2019/day14/part1-1.input").readLines()
        assertThat(computeFirstSolution(input)).isEqualTo(31)
    }

    @Test
    fun `Part 1 example 2 should give 165`() {
        val input = File("./src/test/kotlin/com/pietri/tom/aoc/aoc2019/day14/part1-2.input").readLines()
        assertThat(computeFirstSolution(input)).isEqualTo(165)
    }

    @Test
    fun `Part 1 example 3 should give 13312`() {
        val input = File("./src/test/kotlin/com/pietri/tom/aoc/aoc2019/day14/part1-.input").readLines()
        assertThat(computeFirstSolution(input)).isEqualTo(13312)
    }

    @Test
    fun `Part 1 my input should get 337075`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day14/day14.input").readLines(Charsets.UTF_8)
        Assertions.assertThat(computeFirstSolution(input)).isEqualTo(337075)
    }

    @Test
    fun `Part 2 example 3 should give 82892753`() {
        val input = File("./src/test/kotlin/com/pietri/tom/aoc/aoc2019/day14/part1-3.input").readLines()
        assertThat(computeSecondSolution(input)).isEqualTo(82892753)
    }

    @Test
    fun `Part 2 my input should get 337075`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day14/day14.input").readLines(Charsets.UTF_8)
        Assertions.assertThat(computeSecondSolution(input)).isEqualTo(5194174)
    }
}