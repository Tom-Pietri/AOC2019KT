package com.pietri.tom.aoc.aoc2019.day15

import java.math.BigInteger

data class Computer(val instructions: MutableList<BigInteger>) {

    private var position: Int = 0
    private var relativeBase = 0;

    fun getNexOutput(input: Int): Int? {
        do {
            val operation = Operation(getValueAtPosition(position))
            val (newPosition, output) = getOutputAndPositionForOpcode(operation, input)

            position = newPosition
            if (output != null) {
                return output.toInt()
            }

        } while (operation.opCode != 99)

        return null
    }

    private fun getValueAtPosition(position: Int): BigInteger {
        while (instructions.size - 1 < position) {
            instructions.add(BigInteger.ZERO);
        }

        return instructions[position]
    }

    private fun setValueAtPosition(position: Int, value: BigInteger) {
        while (instructions.size - 1 < position) {
            instructions.add(BigInteger.ZERO);
        }

        instructions[position] = value
    }

    private fun getOutputAndPositionForOpcode(operation: Operation, input: Int): OperationResult {
        return when (operation.opCode) {
            1, 2 -> OperationResult(addOrMultiply(operation))
            3 -> OperationResult(writeBufferValue(input, operation))
            4 -> readOutputValue(operation)
            5, 6, 7, 8 -> OperationResult(fiveThroughHeight(operation))
            9 -> {
                return updateRelativeBase(operation)
            }
            else -> OperationResult(-1, null)
        }
    }

    private fun addOrMultiply(operation: Operation): Int {
        val param1Address = getFirstParamAddres(operation.firstParamMode)
        val param1 = getValueAtPosition(param1Address)

        val param2Address = getSecondParamAddres(operation.secondParamMode);
        val param2 = getValueAtPosition(param2Address)

        val result = if (operation.opCode == 1) {
            param1 + param2
        } else {
            param1 * param2
        }

        val param3Addr = getThirdParamAddres(operation.thirdParamMode)
        setValueAtPosition(param3Addr, result)

        return position + 4
    }

    private fun writeBufferValue(input: Int, operation: Operation): Int {
        val param1Addr = getFirstParamAddres(operation.firstParamMode)
        setValueAtPosition(param1Addr, input.toBigInteger())

        return position + 2
    }

    private fun readOutputValue(operation: Operation): OperationResult {
        val param1Address = getFirstParamAddres(operation.firstParamMode)
        val param1 = getValueAtPosition(param1Address)

        return OperationResult(position + 2, param1)
    }

    // TODO : Extract indedpendant operations
    private fun fiveThroughHeight(operation: Operation): Int {

        val param1Address = getFirstParamAddres(operation.firstParamMode)
        val param1 = getValueAtPosition(param1Address)

        val param2Address = getSecondParamAddres(operation.secondParamMode);
        val param2 = getValueAtPosition(param2Address)

        val thirdParamAddres = getThirdParamAddres(operation.thirdParamMode)

        return when (operation.opCode) {
            5 -> {
                if (param1 != BigInteger.ZERO) {
                    param2.toInt()
                } else {
                    position + 3;
                }
            }
            6 -> {
                if (param1 == BigInteger.ZERO) {
                    param2.toInt();
                } else {
                    position + 3;
                }
            }
            7 -> {
                val valueToSet = if (param1 < param2) {
                    BigInteger.ONE
                } else {
                    BigInteger.ZERO
                }
                setValueAtPosition(thirdParamAddres, valueToSet)

                position + 4;
            }
            8 -> {
                val valueToSet =
                        if (param1 == param2) {
                            BigInteger.ONE
                        } else {
                            BigInteger.ZERO
                        }
                setValueAtPosition(thirdParamAddres, valueToSet)
                position + 4
            }
            else -> -1
        }
    }

    private fun updateRelativeBase(operation: Operation): OperationResult {
        val firstParamAddres = getFirstParamAddres(operation.firstParamMode)
        val param1 = getValueAtPosition(firstParamAddres)

        relativeBase += param1.toInt()
        return OperationResult(position + 2)
    }

    data class OperationResult(val position: Int, val value: BigInteger? = null)

    fun getFirstParamAddres(firstParamMode: Int): Int {
        return when (firstParamMode) {
            0 -> getValueAtPosition(position + 1).toInt()
            1 -> position + 1
            2 -> relativeBase + getValueAtPosition(position + 1).toInt()
            else -> -1
        }
    }

    fun getSecondParamAddres(secondParamMode: Int): Int {
        return when (secondParamMode) {
            0 -> getValueAtPosition(position + 2).toInt()
            1 -> position + 2
            2 -> relativeBase + getValueAtPosition(position + 2).toInt()
            else -> -1
        }
    }

    fun getThirdParamAddres(thirdParamMode: Int): Int {
        return when (thirdParamMode) {
            0 -> getValueAtPosition(position + 3).toInt()
            1 -> position + 3
            2 -> relativeBase + getValueAtPosition(position + 3).toInt()
            else -> -1
        }
    }

    class Operation(private val operation: BigInteger) {
        val opCode = operation.toInt() % 100
        val firstParamMode = operation.toInt() / 100 % 10
        val secondParamMode = operation.toInt() / 1000 % 10
        val thirdParamMode = operation.toInt() / 10000 % 10
    }
}
