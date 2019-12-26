package com.pietri.tom.aoc.aoc2019.day24

import java.lang.IllegalArgumentException
import kotlin.math.pow


fun computeFirstSolution(stringRepresentation: List<String>): Int {
    var bugGrid = BugGrid.fromStringRepresentation(stringRepresentation)
    var biodiversityRating = bugGrid.biodiversityRating()
    val bugGrids = mutableSetOf<Double>()
    do {
        bugGrids.add(biodiversityRating)
        bugGrid = bugGrid.nextGrid()
        biodiversityRating = bugGrid.biodiversityRating()
    } while (!bugGrids.contains(biodiversityRating))

    return bugGrid.biodiversityRating().toInt()
}

class BugGrid(val grid : List<List<Tile>>) {

    companion object {
        fun fromStringRepresentation(stringRepresentation: List<String>): BugGrid {
            val tileGrid = stringRepresentation.map { line ->
                line.map {
                    when (it) {
                        '#' -> Tile.BUG
                        '.' -> Tile.EMPTY
                        else -> throw IllegalArgumentException("Bug grid string representation should only be made of # and .")
                    }
                }
            }
            return BugGrid(tileGrid)
        }
    }

    fun nextGrid(): BugGrid {

        val nextGrid = mutableListOf<MutableList<Tile>>()
        for (i in 0 until grid.size) {
            val curentLine = MutableList(grid[i].size) { Tile.EMPTY }
            for (j in 0 until grid[i].size) {
                val adjacentBugs = adjacentBugAt(i, j)
                curentLine[j] = when (grid[i][j]) {
                    Tile.BUG -> {
                        if (adjacentBugs == 1) {
                            Tile.BUG
                        } else {
                            Tile.EMPTY
                        }
                    }
                    Tile.EMPTY -> {
                        if (adjacentBugs == 2 || adjacentBugs == 1) {
                            Tile.BUG
                        } else {
                            Tile.EMPTY
                        }
                    }
                }
            }
            nextGrid.add(curentLine)
        }
        return BugGrid(nextGrid)
    }

    private fun adjacentBugAt(i: Int, j: Int): Int {
        var adjacentBugs = 0
        if (i - 1 >= 0 && grid[i - 1][j] == Tile.BUG)
            adjacentBugs++
        if (i + 1 < grid.size && grid[i + 1][j] == Tile.BUG)
            adjacentBugs++
        if (j - 1 >= 0 && grid[i][j - 1] == Tile.BUG)
            adjacentBugs++
        if (j + 1 < grid[i].size && grid[i][j + 1] == Tile.BUG)
            adjacentBugs++
        return adjacentBugs
    }

    fun biodiversityRating(): Double {
        var result = 0.0
        for (i in 0 until grid.size) {
            for (j in 0 until grid[i].size) {
                if (grid[i][j] == Tile.BUG) {
                    result += 2.0.pow((i*grid.size)+j)
                }
            }
        }

        return result
    }
}

enum class Tile {
    BUG,
    EMPTY
}