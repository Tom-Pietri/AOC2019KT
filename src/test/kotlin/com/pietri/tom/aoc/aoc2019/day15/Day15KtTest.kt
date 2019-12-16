package com.pietri.tom.aoc.aoc2019.day15

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

internal class Day15KtTest {

    @Test
    fun `Find unknown cell around postition should return NORTH`() {
        val grid = MutableList(10) { MutableList(10) { Tiles.UNKNOWN } }
        val position = Point2D(3, 4);
        grid[4][2] = Tiles.WALL
        grid[4][4] = Tiles.WALL

        assertThat(findCellAround(position, grid, Tiles.UNKNOWN)).isEqualTo(Direction.NORTH)
    }

    @Test
    fun `Find unknown cell around postition should return SOUTH`() {
        val grid = MutableList(10) { MutableList(10) { Tiles.UNKNOWN } }
        val position = Point2D(3, 4);
        grid[3][3] = Tiles.WALL
        grid[4][2] = Tiles.EMPTY
        grid[4][4] = Tiles.EMPTY

        assertThat(findCellAround(position, grid, Tiles.UNKNOWN)).isEqualTo(Direction.SOUTH)
    }

    @Test
    fun `Part 1 my input should get 0`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day15/day15.input").readLines(Charsets.UTF_8).get(0)
        assertThat(computeFirstSolution(input)).isEqualTo(304)
    }
}