package com.pietri.tom.aoc.aoc2019.day19

import java.math.BigInteger

fun computeFirstSolution(intcodeProgram: String): Int {
    var total = 0
    for (x in 0 until 50) {
        for (y in 0 until 50) {
            val nexOutput = getComputerOutput(intcodeProgram.split(",").map { it.toBigInteger() }, x, y)
            if (nexOutput == 1) {
                total++
            }
        }
    }

    return total
}

fun computeSecondSolution(intcodeProgram: String): Int {
    val programAsInt = intcodeProgram.split(",").map { it.toBigInteger() }
    var y = 0;
    var firstGoodXOfRow: Int? = null
    var firstFound: Pair<Int, Int>? = null;
    while (firstFound == null) {
        var lastOutputWasGood = false

        val xStart = firstGoodXOfRow ?: 0
        firstGoodXOfRow = null
        var x = xStart
        while (firstFound == null) {
            if (isPositionInBeam(programAsInt, x, y)) {
                if (firstGoodXOfRow == null) {
                    firstGoodXOfRow = x
                }
                if (isPositionInBeam(programAsInt, x + 99, y + 99)
                        && isPositionInBeam(programAsInt, x + 99, y)
                        && isPositionInBeam(programAsInt, x, y + 99)) {
                    firstFound = Pair(x, y)
                }
                lastOutputWasGood = true
            } else if (lastOutputWasGood) {
                y += 100
                break
            }
            x++;
        }
        if (!lastOutputWasGood) {
            y += 100;
        }
    }

    y -= 100
    while (y < firstFound!!.second) {
        var lastOutputWasGood = false
        val xStart = firstGoodXOfRow ?: 0
        firstGoodXOfRow = null
        var x = xStart;
        while (true) {
            if (isPositionInBeam(programAsInt, x, y)) {
                if (firstGoodXOfRow == null) {
                    firstGoodXOfRow = x
                }
                if (isPositionInBeam(programAsInt, x + 99, y + 99)
                        && isPositionInBeam(programAsInt, x + 99, y)
                        && isPositionInBeam(programAsInt, x, y + 99)) {
                    return (x*10000) + y
                }
                lastOutputWasGood = true
            } else if (lastOutputWasGood) {
                y++
                break
            }
            x++;
        }
        y++
    }


    return -1
}

private fun isPositionInBeam(programAsInt: List<BigInteger>, x: Int, y: Int): Boolean {
    return getComputerOutput(programAsInt, x, y) == 1;
}

private fun getComputerOutput(programAsInt: List<BigInteger>, x: Int, y: Int): Int {
    val computer = Computer(programAsInt.toMutableList())
    return computer.getNexOutput(mutableListOf(x, y))!!
}