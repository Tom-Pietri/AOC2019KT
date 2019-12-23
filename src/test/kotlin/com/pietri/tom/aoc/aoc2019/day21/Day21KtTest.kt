package com.pietri.tom.aoc.aoc2019.day21

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import java.math.BigInteger

internal class Day21KtTest {
    @Test
    fun firstSolution() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day21/day21.input").readLines(Charsets.UTF_8).get(0)
        assertThat(computeFirstSolution(input)).isEqualTo(BigInteger.valueOf(19350375))
    }

    @Test
    fun secondSolution() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day21/day21.input").readLines(Charsets.UTF_8).get(0)
        assertThat(computeSecondSolution(input)).isEqualTo(BigInteger.valueOf(1143990055))
    }
}