package com.pietri.tom.aoc.aoc2019.day7


fun generateAllSequences(range : IntRange): List<List<Int>> {
    val sequences = mutableListOf<List<Int>>()
    for (first in range) for (second in range) for (third in range) for (fourth in range) for (fith in range) {
        if (first == second || first == third || first == fourth || first == fith
                || second == third || second == fourth || second == fith
                || third == fourth || third == fith
                || fourth == fith) {
        } else {
            sequences.add(listOf(first, second, third, fourth, fith))
        }
    }
    return sequences;
}

fun findBestOpCodeSequence(input: String): Int {
    val sequences = generateAllSequences(0..4);
    return sequences.map { runOpCodeWithPhaseSequence(it, input) }.max()!!
}

fun findBestOpCodeSequence2(input: String): Int {
    val sequences = generateAllSequences(5..9)
    return sequences.map { runOpCodeWithPhaseSequence(it, input) }.max()!!
}

fun runOpCodeWithPhaseSequence(sequence: List<Int>, input: String): Int {
    val computers = sequence.map { Computer(instructions = input.split(",").map { it.toInt() }.toMutableList(), phase = it) }

    var allComputersFinished = false;
    var lastOutput = 0
    while(!allComputersFinished) {
        for (computer in computers) {
            val nextOutputs = computer.getNextOutput(lastOutput)
            lastOutput = nextOutputs ?: lastOutput;
            allComputersFinished = computer.finished;
        }
    }

    return lastOutput
}
