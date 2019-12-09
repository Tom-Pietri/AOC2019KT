package com.pietri.tom.aoc.aoc2019.day9

import java.math.BigInteger

data class Computer(val instructions: MutableList<BigInteger>) {

    private var position: Int = 0
    private var relativeBase = 0;

    fun getNextOutput(input: Int): BigInteger {
        var finalOutput = BigInteger.valueOf(0)
        do {
            val opCode = getNextOpCode()
            val firstParamMode = (instructions[position].toInt() / 100 % 10).toInt()
            val (newPosition, output) = getOutputAndPositionForOpcode(opCode, instructions, position, firstParamMode, input)

            position = newPosition
            if (output != null) {
                finalOutput = output
            }
        } while (opCode != 99)

        return finalOutput
    }

    private fun getNextOpCode() = instructions[position].toInt() % 100

    private fun getOutputAndPositionForOpcode(opCode: Int, intcodes: MutableList<BigInteger>, position: Int, firstParamMode: Int, input: Int): OperationResult {
        return when (opCode) {
            1, 2 -> OperationResult(addOrMultiply(intcodes, position, firstParamMode, opCode))
            3 -> OperationResult(writeBufferValue(intcodes, position, input))
            4 -> readOutputValue(intcodes, position)
            5, 6, 7, 8 -> OperationResult(fiveThroughHeight(intcodes, position, firstParamMode, opCode))
            9 -> {
                val param1 = if (firstParamMode == 0) {
                    intcodes[intcodes[position + 1].toInt()]
                } else if (firstParamMode == 2) {
                    intcodes[relativeBase + intcodes[position + 1].toInt()]
                } else {
                    intcodes[position + 1]
                }

                relativeBase = param1.toInt()
                return OperationResult(position + 2)
            }
            else -> OperationResult(-1, null)
        }
    }

    private fun addOrMultiply(intcodes: MutableList<BigInteger>, position: Int, firstParamMode: Int, opCode: Int): Int {
        val secondParamMode = (intcodes[position].toInt() / 1000 % 10)
        val param1 = if (firstParamMode == 0) {
            intcodes[intcodes[position + 1].toInt()]
        } else if (firstParamMode == 2) {
            intcodes[relativeBase + intcodes[position + 1].toInt()]
        } else {
            intcodes[position + 1]
        }

        val param2 = if (secondParamMode == 0) {
            intcodes[intcodes[position + 2].toInt()]
        } else if (secondParamMode == 2) {
            intcodes[relativeBase + intcodes[position + 2].toInt()]
        } else {
            intcodes[position + 2]
        }

        val result = if (opCode == 1) {
            param1 + param2
        } else {
            param1 * param2
        }

        intcodes[intcodes[position + 3].toInt()] = result

        return position + 4
    }

    private fun writeBufferValue(intcodes: MutableList<BigInteger>, position: Int, input: Int): Int {
        val param1 = intcodes[position + 1].toInt()
        intcodes[param1] = input.toBigInteger()

        return position + 2
    }

    private fun readOutputValue(intcodes: MutableList<BigInteger>, position: Int): OperationResult {
        return OperationResult(position + 2, intcodes[intcodes[position + 1].toInt()])
    }

    // TODO : Extract indedpendant operations
    private fun fiveThroughHeight(intcodes: MutableList<BigInteger>, position: Int, firstParamMode: Int, opCode: Int): Int {
        val secondParamMode = (intcodes[position].toInt() / 1000 % 10)
        val param1 = if (firstParamMode == 0) {
            intcodes[intcodes[position + 1].toInt()]
        } else if (firstParamMode == 2) {
            intcodes[relativeBase + intcodes[position + 1].toInt()]
        } else {
            intcodes[position + 1]
        }

        val param2 = if (secondParamMode == 0) {
            intcodes[intcodes[position + 2].toInt()]
        } else if (secondParamMode == 2) {
            intcodes[relativeBase + intcodes[position + 2].toInt()]
        } else {
            intcodes[position + 2]
        }

        return when (opCode) {
            5 -> {
                if (param1 == BigInteger.ZERO) {
                    param2.toInt()
                } else {
                    position + 3;
                }
            }
            6 -> {
                if (param1 != BigInteger.ZERO) {
                    param2.toInt();
                } else {
                    position + 3;
                }
            }
            7 -> {
                if (param1 < param2) {
                    intcodes[intcodes[position + 3].toInt()] = BigInteger.ONE
                } else {
                    intcodes[intcodes[position + 3].toInt()] = BigInteger.ZERO
                }
                position + 4;
            }
            8 -> {
                if (param1 == param2) {
                    intcodes[intcodes[position + 3].toInt()] = BigInteger.ONE
                } else {
                    intcodes[intcodes[position + 3].toInt()] = BigInteger.ZERO
                }
                position + 4
            }
            else -> -1
        }
    }

    data class OperationResult(val position: Int, val value: BigInteger? = null)
}
