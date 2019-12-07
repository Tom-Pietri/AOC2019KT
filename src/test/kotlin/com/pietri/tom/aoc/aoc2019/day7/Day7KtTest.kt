package com.pietri.tom.aoc.aoc2019.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File


class Day7KtTest {

    @Test
    fun `Part 1 example 1 OpcodeWithSequence should be 43210`() {
        var input = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0";
        var sequence = arrayListOf(4, 3, 2, 1, 0)
        assertThat(runOpCodeWithPhaseSequence(sequence, input)).isEqualTo(43210)
    }

    @Test
    fun `Part 1 example 2 OpcodeWithSequence should be 54321`() {
        var input = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0";
        var sequence = arrayListOf(0, 1, 2, 3, 4)
        assertThat(runOpCodeWithPhaseSequence(sequence, input)).isEqualTo(54321)
    }

    @Test
    fun `Part 1 example 3 OpcodeWithSequence should be 65210`() {
        var input = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0";
        var sequence = arrayListOf(1, 0, 4, 3, 2)
        assertThat(runOpCodeWithPhaseSequence(sequence, input)).isEqualTo(65210)
    }

    @Test
    fun `OpcodeWithSequence should be 43210`() {
        var input = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0";
        var sequence = arrayListOf(4, 3, 2, 1, 0);
        assertThat(runOpCodeWithPhaseSequence(sequence, input)).isEqualTo(43210)
    }

    @Test
    fun `Part 1 my input should be 338603`() {
        var input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day7/day7.input").readLines(Charsets.UTF_8).get(0)
        assertThat(findBestOpCodeSequence(input)).isEqualTo(338603)
    }


    @Test
    fun `Part2 example 1 OpcodeWithSequence should be 139629729`() {
        val input = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5"
        val sequence = arrayListOf(9, 8, 7, 6, 5)
        assertThat(runOpCodeWithPhaseSequence(sequence, input)).isEqualTo(139629729)
    }

    @Test
    fun `Part2 example 2 OpcodeWithSequence should be 18216`() {
        val input = "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10"
        val sequence = arrayListOf(9, 7, 8, 5, 6)
        assertThat(runOpCodeWithPhaseSequence(sequence, input)).isEqualTo(18216)
    }

    @Test
    fun `Part2 my input should be ?`() {
        var input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day7/day7.input").readLines(Charsets.UTF_8).get(0)
        assertThat(findBestOpCodeSequence2(input)).isEqualTo(63103596)
    }
}
