package com.pietri.tom.aoc.aoc2019.day5

import java.io.File

fun runOpCode(s: String, value: Int): Int {
    var intcodes = s.split(",").map { it.toInt() }.toMutableList()
    var output = 0

    var position = 0;
    var opCode = intcodes[position] % 100;
    while (opCode != 99) {
        val firstParamMode = (intcodes[position] / 100 % 10)
        val pair = getOutputAndPositionForOpcode(opCode, intcodes, position, firstParamMode, value, output)
        output = pair.second
        position = pair.first
        opCode = intcodes[position] % 100
    }

    return output;
}

private fun getOutputAndPositionForOpcode(opCode: Int, intcodes: MutableList<Int>, position: Int, firstParamMode: Int, value: Int, output: Int): Pair<Int, Int> {
    var opCodeOutput = output
    val nextPosition = when (opCode) {
        1, 2 -> {
            val secondParamMode = (intcodes[position] / 1000 % 10)
            val param1 = if (firstParamMode == 0) {
                intcodes[intcodes[position + 1]]
            } else {
                intcodes[position + 1]
            }

            val param2 = if (secondParamMode == 0) {
                intcodes[intcodes[position + 2]]
            } else {
                intcodes[position + 2]
            }

            val result = if (opCode == 1) {
                param1 + param2
            } else {
                param1 * param2
            }

            intcodes[intcodes[position + 3]] = result

            position + 4
        }
        3 -> {
            val param1 = intcodes[position + 1];
            intcodes[param1] = value
            position + 2
        }
        4 -> {
            val param1 = intcodes[position + 1];
            opCodeOutput = intcodes[param1]
            position + 2
        }
        5, 6, 7, 8 -> {
            val secondParamMode = (intcodes[position] / 1000 % 10)
            val param1 = if (firstParamMode == 0) {
                intcodes[intcodes[position + 1]]
            } else {
                intcodes[position + 1]
            }

            val param2 = if (secondParamMode == 0) {
                intcodes[intcodes[position + 2]]
            } else {
                intcodes[position + 2]
            }

            when (opCode) {
                5 -> {
                    if (param1 != 0) {
                        param2;
                    } else {
                        position + 3;
                    }
                }
                6 -> {
                    if (param1 == 0) {
                        param2;
                    } else {
                        position + 3;
                    }
                }
                7 -> {
                    if (param1 < param2) {
                        intcodes[intcodes[position + 3]] = 1;
                    } else {
                        intcodes[intcodes[position + 3]] = 0
                    }
                    position + 4;
                }
                8 -> {
                    if (param1 == param2) {
                        intcodes[intcodes[position + 3]] = 1
                    } else {
                        intcodes[intcodes[position + 3]] = 0
                    }
                    position + 4
                }
                else -> -1
            }
        }
        else -> -1
    }
    return Pair(nextPosition, opCodeOutput)
}

fun main() {
    var input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day5/day5.input").readLines(Charsets.UTF_8).get(0)
//    println(runOpCode(input, 1))
    println(runOpCode(input, 5))
}
