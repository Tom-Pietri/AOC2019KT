package com.pietri.tom.aoc.aoc2019.day9

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.math.BigInteger

internal class Day9KtTest {


    @Test
    fun `Part 1 test input 1 should get `() {
        var input = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99"
        Assertions.assertThat(computeFirstSolution(input, 0)).isEqualTo(99)
        // It should display itself in the console
    }

    @Test
    fun `Part 1 test input 2 should get `() {
        var input = "1102,34915192,34915192,7,4,7,99,0"
        Assertions.assertThat(computeFirstSolution(input, 1)).isEqualTo(BigInteger.valueOf(1219070632396864))
    }

    @Test
    fun `Part 1 test input 3 should get `() {
        var input = "104,1125899906842624,99"
        Assertions.assertThat(computeFirstSolution(input, 0)).isEqualTo(BigInteger.valueOf(1125899906842624))
    }

    @Test
    fun `Part 1 my input should get 2775723069`() {
        var input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day9/day9.input").readLines(Charsets.UTF_8).get(0)
        Assertions.assertThat(computeFirstSolution(input, 1)).isEqualTo(2775723069)
    }

    @Test
    fun `Part 2 my input should get 49115`() {
        var input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day9/day9.input").readLines(Charsets.UTF_8).get(0)
        Assertions.assertThat(computeSecondSolution(input, 2)).isEqualTo(49115)
    }
}
