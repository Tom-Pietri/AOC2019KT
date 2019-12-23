package com.pietri.tom.aoc.aoc2019.day21

import java.math.BigInteger

fun computeFirstSolution(intcodeProgram: String): BigInteger {

    val asciiProgram = listOf(
            "NOT A T",
            "NOT B J",
            "OR T J",
            "NOT C T",
            "OR T J",
            "AND D J",
            "WALK")
    val computer = Computer(intcodeProgram.split(",").map { it.toBigInteger() }.toMutableList())
    val outputs = computer.getNexOutput(asciiProgramToIntcodeInput(asciiProgram).toMutableList())
    return outputs.last()
}

fun computeSecondSolution(intcodeProgram: String): BigInteger {

    val asciiProgram = listOf(
            "NOT A T",
            "NOT B J",
            "OR T J",
            "NOT C T",
            "OR T J",
            "AND D J",
            "NOT E T",
            "NOT T T",
            "OR H T",
            "AND T J",
            "RUN")
    val computer = Computer(intcodeProgram.split(",").map { it.toBigInteger() }.toMutableList())
    val outputs = computer.getNexOutput(asciiProgramToIntcodeInput(asciiProgram).toMutableList())
    return outputs.last()
}

fun printOutputs(outputs: List<BigInteger>) {
    outputs.forEach {
        when (it) {
            BigInteger.valueOf(35) -> print('#')
            BigInteger.valueOf(46) -> print('.')
            BigInteger.valueOf(10) -> println()
            else -> println(it)
        }
    }
}

fun asciiProgramToIntcodeInput(asciiProgram: List<String>): List<Int> {

    return asciiProgram.map { it.map(Char::toInt) }
            .mapIndexed { index, list ->
                if (index == asciiProgram.size)
                    list
                else
                    list + 10
            }.flatten()
}