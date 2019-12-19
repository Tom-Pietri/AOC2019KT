package com.pietri.tom.aoc.aoc2019.day17

fun computeFirstSolution(input: String): Int {
    val computer = Computer(input.split(",").map { it.toBigInteger() }.toMutableList())
    val grid = mutableListOf<List<Char>>()
    var line = mutableListOf<Char>()
    do {
        val nexOutput = computer.getNexOutput(0)
        if (nexOutput != null) {
            when (nexOutput) {
                35 -> {
                    line.add('#')
                }
                46 -> {
                    line.add('.')
                }
                10 -> {
                    if (line.isNotEmpty()) {
                        grid.add(line)
                    }

                    line = mutableListOf()
                }
//                35 means #, 46 means ., 10 starts a new line
            }
        }
    } while (nexOutput != null)
    if (line.isNotEmpty()) {
        grid.add(line)
    }
    var result = 0
    for (y in 0 until grid.size) {
        for (x in 0 until grid[y].size) {
            if (grid[y][x] == '#') {
                if (y - 1 < 0 || x - 1 < 0 || y + 1 >= grid.size || x + 1 >= grid[y].size) {

                } else {
                    if (grid[y - 1][x] == '#' && grid[y + 1][x] == '#' &&
                            grid[y][x - 1] == '#' && grid[y][x + 1] == '#') {
                        result += x * y
                    }
                }
            }
        }
    }
    grid.map { it.joinToString("") }
            .forEach { println(it) }
    return result
}
