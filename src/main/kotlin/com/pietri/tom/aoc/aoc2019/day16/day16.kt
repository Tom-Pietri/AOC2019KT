package com.pietri.tom.aoc.aoc2019.day16

import kotlin.math.abs
import kotlin.math.floor


fun computeFirstSolution(input: String): String {
    val signal = toListOfInt(input)
    return resultAfterPhases(signal).subList(0, 8).joinToString("")
}

fun computeSecondSolution(input: String): String {
    val signal = toListOfInt(input)
    val realSignal = toListOfInt(input).toMutableList()
    repeat(10000) {realSignal.addAll(signal)}
    return resultAfterPhases(realSignal).joinToString("")
}

fun toListOfInt(input: String): List<Int> {
    return input.split("")
            .subList(1, input.length + 1)
            .map { it.toInt() }
}

fun resultAfterPhases(signal: List<Int>): List<Int> {
    var currentSignal = signal
    repeat(100) {
        println(currentSignal.joinToString(""))
        currentSignal = nextPhase(currentSignal) }
//    return currentSignal.subList(0, 8).joinToString("")
    return currentSignal
}

fun nextPhase(signal: List<Int>): List<Int> {
    val nextPhase = MutableList<Int>(signal.size) { 0 }
    val basePattern = listOf(0, 1, 0, -1)
    for (i in 0 until signal.size) {
        nextPhase[i] = abs(signal.mapIndexed { index, signalValue ->
            i(signalValue, basePattern, index, i)
        }.sum()) % 10
    }

    return nextPhase
}

private fun i(signalValue: Int, basePattern: List<Int>, index: Int, i: Int): Int {
    val modifier = basePattern[floor((index + 1).toDouble() / (i + 1) % basePattern.size).toInt()]
    return signalValue * modifier
}


