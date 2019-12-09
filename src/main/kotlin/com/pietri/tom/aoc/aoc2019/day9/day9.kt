package com.pietri.tom.aoc.aoc2019.day9

import java.math.BigInteger

fun computeFirstSolution(input: String, value: Int): BigInteger {
    val computer = Computer(input.split(",").map { it.toBigInteger() }.toMutableList())
    return computer.getNextOutput(value)
}

fun computeSecondSolution(input: String, value: Int): BigInteger {
    val computer = Computer(input.split(",").map { it.toBigInteger() }.toMutableList())
    return computer.getNextOutput(value)
}
