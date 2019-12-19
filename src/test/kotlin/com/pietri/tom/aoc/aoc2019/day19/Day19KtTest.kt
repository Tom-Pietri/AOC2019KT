package com.pietri.tom.aoc.aoc2019.day19

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class Day19KtTest {
    @Test
    fun `Part 1 my input should get 226`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day19/day19.input").readLines(Charsets.UTF_8).get(0)
        Assertions.assertThat(computeFirstSolution(input)).isEqualTo(226)
    }

    @Test
    fun `Part 2 my input should get ?`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day19/day19.input").readLines(Charsets.UTF_8).get(0)
        Assertions.assertThat(computeSecondSolution(input)).isEqualTo(7900946)
    }
}