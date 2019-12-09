package com.pietri.tom.aoc.aoc2019.day9

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class Day9KtTest {


    @Test
    fun `Part 1 test input 1 should get `() {
        var input = "1102,34915192,34915192,7,4,7,99,0"
        Assertions.assertThat(computeFirstSolution(input, 1)).isEqualTo(1)
    }

    @Test
    fun `Part 2 my input should get list`() {
        var input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day9/day9.input").readLines(Charsets.UTF_8).get(0)
        Assertions.assertThat(computeFirstSolution(input, 1)).isEqualTo(1)
    }
}
