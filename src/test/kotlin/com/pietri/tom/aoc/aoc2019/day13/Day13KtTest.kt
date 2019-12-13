package com.pietri.tom.aoc.aoc2019.day13

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File


internal class Day13KtTest {

    @Test
    fun `Part 1 my input should get 2054`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day13/day13.input").readLines(Charsets.UTF_8).get(0)
        Assertions.assertThat(computeFirstSolution(input)).isEqualTo(333)
    }
}