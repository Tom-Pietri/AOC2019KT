package com.pietri.tom.aoc.aoc2019.day9

data class Computer(val instructions: MutableList<Int>) {

    private var position: Int = 0
    private var relativeBase = 0;

    fun getNextOutput(input: Int): Int {
        var finalOutput = 0
        do {
            val opCode = getNextOpCode();
            val firstParamMode = (instructions[position] / 100 % 10)
            val (newPosition, output) = getOutputAndPositionForOpcode(opCode, instructions, position, firstParamMode, input)

            position = newPosition
            if (output != null) {
                finalOutput = output
            }
        } while (opCode != 99)

        return finalOutput
    }

    private fun getNextOpCode() = instructions[position] % 100

    private fun getOutputAndPositionForOpcode(opCode: Int, intcodes: MutableList<Int>, position: Int, firstParamMode: Int, input: Int): OperationResult {
        return when (opCode) {
            1, 2 -> OperationResult(addOrMultiply(intcodes, position, firstParamMode, opCode))
            3 -> OperationResult(writeBufferValue(intcodes, position, input))
            4 -> readOutputValue(intcodes, position)
            5, 6, 7, 8 -> OperationResult(fiveThroughHeight(intcodes, position, firstParamMode, opCode))
            9 -> {
                val param1 = if (firstParamMode == 0) {
                    intcodes[intcodes[position + 1]]
                } else if (firstParamMode == 2) {
                    intcodes[relativeBase + intcodes[position + 1]]
                } else {
                    intcodes[position + 1]
                }

                relativeBase = param1;
                return OperationResult(position + 2)
            }
            else -> OperationResult(-1, null)
        }
    }

    private fun addOrMultiply(intcodes: MutableList<Int>, position: Int, firstParamMode: Int, opCode: Int): Int {
        val secondParamMode = (intcodes[position] / 1000 % 10)
        val param1 = if (firstParamMode == 0) {
            intcodes[intcodes[position + 1]]
        } else if (firstParamMode == 2) {
            intcodes[relativeBase + intcodes[position + 1]]
        } else {
            intcodes[position + 1]
        }

        val param2 = if (secondParamMode == 0) {
            intcodes[intcodes[position + 2]]
        } else if (secondParamMode == 2) {
            intcodes[relativeBase + intcodes[position + 2]]
        } else {
            intcodes[position + 2]
        }

        val result = if (opCode == 1) {
            param1 + param2
        } else {
            param1 * param2
        }

        intcodes[intcodes[position + 3]] = result

        return position + 4
    }

    private fun writeBufferValue(intcodes: MutableList<Int>, position: Int, input: Int): Int {
        val param1 = intcodes[position + 1];
        intcodes[param1] = input

        return position + 2
    }

    private fun readOutputValue(intcodes: MutableList<Int>, position: Int): OperationResult {
        return OperationResult(position + 2, intcodes[intcodes[position + 1]])
    }

    // TODO : Extract indedpendant operations
    private fun fiveThroughHeight(intcodes: MutableList<Int>, position: Int, firstParamMode: Int, opCode: Int): Int {
        val secondParamMode = (intcodes[position] / 1000 % 10)
        val param1 = if (firstParamMode == 0) {
            intcodes[intcodes[position + 1]]
        } else if (firstParamMode == 2) {
            intcodes[relativeBase + intcodes[position + 1]]
        } else {
            intcodes[position + 1]
        }

        val param2 = if (secondParamMode == 0) {
            intcodes[intcodes[position + 2]]
        } else if (secondParamMode == 2) {
            intcodes[relativeBase + intcodes[position + 2]]
        } else {
            intcodes[position + 2]
        }

        return when (opCode) {
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

    data class OperationResult(val position: Int, val value: Int? = null);
}
