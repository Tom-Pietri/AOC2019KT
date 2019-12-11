package com.pietri.tom.aoc.aoc2019.day10

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

internal class Day10KtTest {

    @Test
    fun `Part 1 First test should return 8`() {
        val input = listOf(
                ".#..#",
                ".....",
                "#####",
                "....#",
                "...##")

        assertThat(computeFirstSolution(input)).isEqualTo(8)
    }

    @Test
    fun `Part 2 First test should return 8`() {
        val input = listOf(
                ".#....#####...#..",
                "##...##.#####..##",
                "##...#...#.#####.",
                "..#.....X...###..",
                "..#.#.....#....##")
          assertThat(computeSecondSolution(input)).isEqualTo(8)
    }

    @Test
    fun `Part 1 my input should get 344`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day10/day10.input").readLines(Charsets.UTF_8)
        Assertions.assertThat(computeFirstSolution(input)).isEqualTo(344)
    }

    @Test
    fun `Part 2 my input should get 344`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day10/day10.input").readLines(Charsets.UTF_8)
        Assertions.assertThat(computeSecondSolution(input)).isEqualTo(2732)
    }

}

