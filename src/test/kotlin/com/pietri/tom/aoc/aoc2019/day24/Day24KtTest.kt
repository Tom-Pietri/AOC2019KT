package com.pietri.tom.aoc.aoc2019.day24

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.lang.IllegalArgumentException

internal class Day24KtTest {

    @Test
    fun `We can make a bird grid from a string representation`() {
        val stringRepresentation = listOf("#.",
                ".#")
        assertThat(BugGrid.fromStringRepresentation((stringRepresentation))).isNotEqualTo(null)
    }

    @Test
    fun `Bird grid string representation should only contains # and dot`() {
        val stringRepresentation = listOf("#.",
                ".p")
        try {
            val bugGrid = BugGrid.fromStringRepresentation((stringRepresentation))
        } catch (e: IllegalArgumentException) {
            assert(true) { "IAE was raised" }
            return
        }

        assert(false) { "IAE should have been raised" }
    }

    @ParameterizedTest
    @MethodSource("bugGrid")
    fun `Bird grid made from a string representation is good`(stringRepresentation: List<String>,
                                                              expectedTileGrid: List<List<Tile>>) {
        val bugGrid = BugGrid.fromStringRepresentation((stringRepresentation))
        assertThat(bugGrid.grid).isEqualTo(expectedTileGrid)
    }

    //    @Test
    fun firstSolution() {
        val inputGrid = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day21/day21.input").readLines(Charsets.UTF_8)
        assert(false)
    }

    companion object {
        @JvmStatic
        fun bugGrid() = listOf(
                Arguments.of(listOf(""), listOf(listOf<Tile>())),
                Arguments.of(listOf("#.",".#"), listOf(listOf(Tile.BUG, Tile.EMPTY),listOf(Tile.EMPTY, Tile.BUG))),
                Arguments.of(listOf("##","##"), listOf(listOf(Tile.BUG, Tile.BUG),listOf(Tile.BUG, Tile.BUG))),
                Arguments.of(listOf("#.#",".##"), listOf(listOf(Tile.BUG, Tile.EMPTY, Tile.BUG),listOf(Tile.EMPTY, Tile.BUG, Tile.BUG)))
        )
    }
}