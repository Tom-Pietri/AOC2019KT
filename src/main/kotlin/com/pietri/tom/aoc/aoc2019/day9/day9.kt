package com.pietri.tom.aoc.aoc2019.day9

fun computeFirstSolution(input: String, value: Int): Int {
    val computer = Computer(input.split(",").map { it.toInt() }.toMutableList())
    return computer.getNextOutput(value)
}
