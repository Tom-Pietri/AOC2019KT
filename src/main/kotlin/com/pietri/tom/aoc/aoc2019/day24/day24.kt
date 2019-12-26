package com.pietri.tom.aoc.aoc2019.day24

import java.lang.IllegalArgumentException


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
}

enum class Tile {
    BUG,
    EMPTY
}