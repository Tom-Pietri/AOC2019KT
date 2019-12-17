package com.pietri.tom.aoc.aoc2019.day16

import java.util.*
import kotlin.math.abs
import kotlin.math.floor


fun computeFirstSolution(input: String): String {
    val signal = toListOfInt(input)
    return resultAfterPhases(signal).subList(0, 8).joinToString("")
}

fun computeSecondSolution(input: String): String {
    val signal = toListOfInt(input)
    val offSet = signal.subList(0, 7).joinToString("").toInt()
    var realSignal = toListOfInt(input).toMutableList()
    repeat(9999) {realSignal.addAll(signal)}
    realSignal = realSignal.subList(offSet, realSignal.size)
    return resultAfterPhases2(realSignal).subList(0, 8).joinToString("")
}

fun toListOfInt(input: String): List<Int> {
    return input.split("")
            .subList(1, input.length + 1)
            .map { it.toInt() }
}

fun resultAfterPhases(signal: List<Int>): List<Int> {
    var currentSignal = signal
    repeat(100) {
        currentSignal = nextPhase(currentSignal) }
//    return currentSignal.subList(0, 8).joinToString("")
    return currentSignal
}

fun resultAfterPhases2(signal: List<Int>): List<Int> {
    var currentSignal = signal
    repeat(100) {
        currentSignal = nextPhase2(currentSignal)
    }
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

fun nextPhase2(signal: List<Int>): List<Int> {
    val nextValues = MutableList(signal.size) { 0 }
    val lastIndex = signal.size - 1
    nextValues[lastIndex] = signal[lastIndex]
    for (i in 1 until signal.size) {
        nextValues[lastIndex - i] = signal[lastIndex - i] + nextValues[signal.size - i]
    }

    return nextValues.map { it % 10 }
}

private fun i(signalValue: Int, basePattern: List<Int>, index: Int, i: Int): Int {
    val modifier = basePattern[floor((index + 1).toDouble() / (i + 1) % basePattern.size).toInt()]
    return signalValue * modifier
}


