package com.pietri.tom.aoc.aoc2019.day8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

class Day8KtTest {

    @Test
    fun `Part 1 my input should get 2460`() {
            var input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day8/day8.input").readLines(Charsets.UTF_8).get(0)
            assertThat(computeFirstSolution(input)).isEqualTo(2460)
    }

    @Test
    fun `Part 2 my input should get list`() {
            var input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day8/day8.input").readLines(Charsets.UTF_8).get(0)
            assertThat(computeSecondSolution(input)).isEqualTo(listOf(
                    "1000011100111101001010010",
                    "1000010010100001010010010",
                    "1000010010111001100010010",
                    "1000011100100001010010010",
                    "1000010100100001010010010",
                    "1111010010100001001001100"))
        //LRFKU
    }

    @Test
    fun `Part 2 test`() {
            var input = "0222112222120000"
            assertThat(computeSecondSolution(input, 2, 2)).isEqualTo(listOf("01","10"))
    }
}
