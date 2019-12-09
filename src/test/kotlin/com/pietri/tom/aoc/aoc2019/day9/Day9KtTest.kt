package com.pietri.tom.aoc.aoc2019.day9

import jdk.nashorn.internal.ir.annotations.Ignore
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.math.BigInteger

internal class Day9KtTest {


    @Test
    fun `Part 1 test input 2 should get `() {
        var input = "1102,34915192,34915192,7,4,7,99,0"
        Assertions.assertThat(computeFirstSolution(input, 1)).isEqualTo(BigInteger.valueOf(1219070632396864))
    }

    @Test @Ignore
    fun `Part 1 test input 1 should get `() {
        var input = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99"
        Assertions.assertThat(computeFirstSolution(input, 1)).isEqualTo(1)
    }

    @Test @Ignore
    fun `Part 2 my input should get list`() {
        var input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day9/day9.input").readLines(Charsets.UTF_8).get(0)
        Assertions.assertThat(computeFirstSolution(input, 1)).isEqualTo(1)
    }
}
