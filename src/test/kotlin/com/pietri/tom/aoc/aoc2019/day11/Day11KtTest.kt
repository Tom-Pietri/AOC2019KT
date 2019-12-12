package com.pietri.tom.aoc.aoc2019.day11

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class Day11KtTest {
    @Test
    fun `Part 1 my input should get 2054`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day11/day11.input").readLines(Charsets.UTF_8).get(0)
        Assertions.assertThat(computeFirstSolution(input)).isEqualTo(2054)
    }

    @Test
    fun `Part 2 my input should get 2775723069`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day11/day11.input").readLines(Charsets.UTF_8).get(0)

        Assertions.assertThat(computeSecondSolution(input)).isEqualTo(listOf(
                " █  █ ███  ████ ████  ██    ██ █  █ ███   █",
                "██ █  █  █    █ █    █  █    █ █  █ █  █   ",
                "███   █  █   █  ███  █  █    █ ████ ███    ",
                " █ █  ███   █   █    ████    █ █  █ █  █  █",
                " █ █  █ █  █    █    █  █ █  █ █  █ █  █ ██",
                "██  █ █  █ ████ ████ █  █  ██  █  █ ███  ██"))
        //KRZEAJHB
    }
}






