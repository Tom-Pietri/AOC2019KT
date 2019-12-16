package com.pietri.tom.aoc.aoc2019.day15

fun computeFirstSolution(input: String): Int {
    val computer = Computer(input.split(",").map { it.toBigInteger() }.toMutableList())

    var currentPosition = Point2D(25, 25)
    val grid = MutableList(50) { MutableList(50) { Tiles.UNKNOWN } }
    var nextDirections = nextDirections(currentPosition, grid)
    while (nextDirections.isNotEmpty()) {
        val nextDirection = nextDirections.removeAt(0)
        val droidOutput = computer.getNexOutput(nextDirection.value)
        if (droidOutput != null) {
            val nextPosition = nextDirection.nextPosition(currentPosition)
            currentPosition = procesDroidOutput(grid, nextPosition, droidOutput, currentPosition)
        }
        if (nextDirections.isEmpty()) {
            nextDirections = nextDirections(currentPosition, grid)
        }
    }
    val pathToOxygenSystem = findPathToClosestCell(Point2D(25, 25), grid, Tiles.OXYGEN_SYSTEM)
//    fillPath(currentPosition, nextDirections, pathToOxygenSystem, computer, grid)

    grid.forEach {
        println(it.map { it.value }.joinToString(""))
    }

    return pathToOxygenSystem.size
}

private fun fillPath(currentPosition: Point2D, nextDirections: MutableList<Direction>, pathToOxygenSystem: MutableList<Direction>, computer: Computer, grid: MutableList<MutableList<Tiles>>) {
    var currentPosition1 = currentPosition
    var nextDirections1 = nextDirections
    currentPosition1 = Point2D(25, 25)
    nextDirections1 = pathToOxygenSystem
    while (nextDirections1.isNotEmpty()) {
        val nextDirection = nextDirections1.removeAt(0)
        val droidOutput = computer.getNexOutput(nextDirection.value)
        if (droidOutput != null) {
            currentPosition1 = nextDirection.nextPosition(currentPosition1)
            grid[currentPosition1.y][currentPosition1.x] = Tiles.PATH
        }
    }
}

private fun procesDroidOutput(grid: MutableList<MutableList<Tiles>>, nextPosition: Point2D, droidOutput: Int, currentPosition: Point2D): Point2D {
    grid[nextPosition.y][nextPosition.x] = Tiles.forValue(droidOutput)
    return when (droidOutput) {
        1, 2 -> nextPosition
        else -> currentPosition
    }
}

fun nextDirections(position: Point2D, grid: List<List<Tiles>>): MutableList<Direction> {
    val unknownCellAround = findCellAround(position, grid, Tiles.UNKNOWN)
    return if (unknownCellAround == null) {
        findPathToClosestCell(position, grid, Tiles.UNKNOWN)
    } else {
        mutableListOf(unknownCellAround)
    }
}

fun findCellAround(position: Point2D, grid: List<List<Tiles>>, searchedTile: Tiles): Direction? {
    return Direction.values().firstOrNull {
        val nextPosition = it.nextPosition(position)
        grid[nextPosition.y][nextPosition.x] == searchedTile
    }
}

fun findEmptyAround(position: Point2D, grid: List<List<Tiles>>, commingFrom: Direction?): List<Direction> {
    return Direction.values()
            .filter { commingFrom == null || commingFrom.oppositeDirection() != it }
            .filter {
                val nextPosition = it.nextPosition(position)
                grid[nextPosition.y][nextPosition.x] == Tiles.EMPTY
            }
}

fun findPathToClosestCell(position: Point2D, grid: List<List<Tiles>>, searchedTile: Tiles): MutableList<Direction> {
    val emptyAroundPosition = findEmptyAround(position, grid, null).map { Pair(mutableListOf(it), it.nextPosition(position)) }.toMutableList()

    while (emptyAroundPosition.size > 0) {
        val testedCurrentEmptyPosition = emptyAroundPosition.removeAt(0)
        val unkwnownAround = findCellAround(testedCurrentEmptyPosition.second, grid, searchedTile)
        if (unkwnownAround != null) {
            return testedCurrentEmptyPosition.first.plus(unkwnownAround).toMutableList()
        }
        emptyAroundPosition.addAll(
                findEmptyAround(testedCurrentEmptyPosition.second, grid, testedCurrentEmptyPosition.first.last())
                        .map { Pair(testedCurrentEmptyPosition.first.plus(it).toMutableList(), it.nextPosition(testedCurrentEmptyPosition.second)) })
    }

    return mutableListOf()
}