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
    @MethodSource("stringRepresentationTests")
    fun `Bird grid made from a string representation is good`(stringRepresentation: List<String>,
                                                              expectedTileGrid: List<List<Tile>>) {
        val bugGrid = BugGrid.fromStringRepresentation((stringRepresentation))
        assertThat(bugGrid.grid).isEqualTo(expectedTileGrid)
    }

    @ParameterizedTest
    @MethodSource("nextStepTests")
    fun `Bird grid next step works`(stringRepresentation: List<String>,
                                    expectedNextStep: List<List<Tile>>) {
        val bugGrid = BugGrid.fromStringRepresentation(stringRepresentation)
        assertThat(bugGrid.nextGrid().grid).isEqualTo(expectedNextStep)
    }

    @Test
    fun `Empty grid biodiversity rating should be 0`() {
        val stringRepresentation = listOf("")
        val bugGrid = BugGrid.fromStringRepresentation(stringRepresentation)
        assertThat(bugGrid.biodiversityRating()).isEqualTo(0.0)
    }

    @ParameterizedTest
    @MethodSource("biodiversityTests")
    fun `Grid biodiversity rating`(stringRepresentation: List<String>, expectedRating: Double) {
        val bugGrid = BugGrid.fromStringRepresentation(stringRepresentation)
        assertThat(bugGrid.biodiversityRating()).isEqualTo(expectedRating)
    }

    //    @Test
    fun firstSolution() {
        val inputGrid = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day21/day21.input").readLines(Charsets.UTF_8)
        assert(false)
    }

    companion object {
        @JvmStatic
        fun stringRepresentationTests() = listOf(
                Arguments.of(listOf(""), listOf(listOf<Tile>())),
                Arguments.of(listOf("#.",".#"), listOf(listOf(Tile.BUG, Tile.EMPTY),listOf(Tile.EMPTY, Tile.BUG))),
                Arguments.of(listOf("##","##"), listOf(listOf(Tile.BUG, Tile.BUG),listOf(Tile.BUG, Tile.BUG))),
                Arguments.of(listOf("#.#",".##"), listOf(listOf(Tile.BUG, Tile.EMPTY, Tile.BUG),listOf(Tile.EMPTY, Tile.BUG, Tile.BUG)))
        )

        @JvmStatic
        fun nextStepTests() = listOf(
                Arguments.of(listOf(""), listOf(listOf<Tile>())),
                Arguments.of(listOf("..", ".."), listOf(listOf(Tile.EMPTY, Tile.EMPTY), listOf(Tile.EMPTY, Tile.EMPTY))),
                Arguments.of(listOf("#.", ".#"), listOf(listOf(Tile.EMPTY, Tile.BUG), listOf(Tile.BUG, Tile.EMPTY))),
                Arguments.of(listOf(
                        "#.#",
                        ".##"), listOf(listOf(Tile.EMPTY, Tile.EMPTY, Tile.BUG),
                        listOf(Tile.BUG, Tile.BUG, Tile.EMPTY))),
                Arguments.of(listOf("....#",
                        "#..#.",
                        "#..##",
                        "..#..",
                        "#...."),
                        BugGrid.fromStringRepresentation(
                                listOf("#..#.",
                                        "####.",
                                        "###.#",
                                        "##.##",
                                        ".##..")).grid)
        )

        @JvmStatic
        fun biodiversityTests() = listOf(
                Arguments.of(listOf(""), 0.0),
                Arguments.of(listOf("..", ".."), 0.0),
                Arguments.of(listOf("#.", ".."), 1.0),
                Arguments.of(listOf(".#", ".."), 2.0),
                Arguments.of(listOf("##", ".."), 3.0),
                Arguments.of(listOf("##", "##"), 15.0),
                Arguments.of(
                        listOf(".....",
                                ".....",
                                ".....",
                                "#....",
                                ".#..."), 2129920.0)
        )
    }
}