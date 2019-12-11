package com.pietri.tom.aoc.aoc2019.day11

fun computeFirstSolution(input: String): Int {
    val computer = Computer(input.split(",").map { it.toBigInteger() }.toMutableList())
    val paintedAreas = HashMap<Point, Int>()
    var currentPosition = Point(0, 0)
    var currentDirection = Direction.UP
    do {
        val currentPositionColor = paintedAreas[currentPosition] ?: 0
        val nextTwoOutputs = computer.getNexOutput(currentPositionColor)
        if (nextTwoOutputs != null) {
            paintedAreas[currentPosition] = nextTwoOutputs[0]
            currentDirection = currentDirection.getNextDirection(nextTwoOutputs[1])
            currentPosition = currentDirection.getNextPosition(currentPosition)
        }
    } while (nextTwoOutputs != null)

    return paintedAreas.size
}

fun computeSecondSolution(input: String): List<String> {
    val computer = Computer(input.split(",").map { it.toBigInteger() }.toMutableList())
    val paintedAreas = HashMap<Point, Int>()
    var currentPosition = Point(0, 0)
    paintedAreas[currentPosition] = 1
    var currentDirection = Direction.UP
    do {
        val currentPositionColor = paintedAreas[currentPosition] ?: 0
        val nextTwoOutputs = computer.getNexOutput(currentPositionColor)
        if (nextTwoOutputs != null) {
            paintedAreas[currentPosition] = nextTwoOutputs[0]
            currentDirection = currentDirection.getNextDirection(nextTwoOutputs[1])
            currentPosition = currentDirection.getNextPosition(currentPosition)
        }
    } while (nextTwoOutputs != null)

    val maxX = paintedAreas.keys.map { it.x }.max()!!
    val maxY = paintedAreas.keys.map { it.y }.max()!!

    val resultArray = Array(maxY + 1) { Array((maxX + 1)) { 0 } }

    paintedAreas.filter { it.value == 0 }
            .map { it.key }
            .forEach { resultArray[it.y][it.x] = 1 }
    return resultArray.map {
        it.map {
            if (it == 0) {
                '█'
            } else {
                ' '
            }
        }.joinToString("")
    }
}

data class Point(val x: Int, val y: Int) {
    fun add(point: Point): Point {
        return Point(this.x + point.x, this.y + point.y)
    }
}

enum class Direction {
    UP {
        override fun getNextDirection(turn: Int): Direction {
            return if (turn == 0) {
                LEFT
            } else {
                RIGHT
            }
        }

        override fun getNextPosition(currentPosition: Point): Point {
            return currentPosition.add(Point(0, -1))
        }
    },
    LEFT {
        override fun getNextDirection(turn: Int): Direction {
            return if (turn == 0) {
                DOWN
            } else {
                UP
            }
        }

        override fun getNextPosition(currentPosition: Point): Point {
            return currentPosition.add(Point(-1, 0))
        }
    },
    DOWN {
        override fun getNextDirection(turn: Int): Direction {
            return if (turn == 0) {
                RIGHT
            } else {
                LEFT
            }
        }

        override fun getNextPosition(currentPosition: Point): Point {
            return currentPosition.add(Point(0, 1))
        }
    },
    RIGHT {
        override fun getNextDirection(turn: Int): Direction {
            return if (turn == 0) {
                UP
            } else {
                DOWN
            }
        }

        override fun getNextPosition(currentPosition: Point): Point {
            return currentPosition.add(Point(1, 0))
        }
    };

    abstract fun getNextDirection(turn: Int): Direction
    abstract fun getNextPosition(currentPosition: Point): Point
}