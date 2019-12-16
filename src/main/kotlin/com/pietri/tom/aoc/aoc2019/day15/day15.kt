package com.pietri.tom.aoc.aoc2019.day15

private fun makeComputer(input: String) = Computer(input.split(",").map { it.toBigInteger() }.toMutableList())

fun computeFirstSolution(input: String): Int {
    val computer = makeComputer(input)

    val originalPosition = Point2D(25, 25)
    val grid = MutableList(50) { MutableList(50) { Tiles.UNKNOWN } }
    fillGridWithMaze(originalPosition, grid, computer)
    val pathToOxygenSystem = findPathToClosestCell(Point2D(25, 25), grid, Tiles.OXYGEN_SYSTEM)

    printGrid(grid)

    return pathToOxygenSystem.size
}

fun computeSecondSolution(input: String): Int {
    val computer = makeComputer(input)

    val originalPosition = Point2D(25, 25)
    val grid = MutableList(50) { MutableList(50) { Tiles.UNKNOWN } }
    fillGridWithMaze(originalPosition, grid, computer)
    val pathToOxygenSystem = findPathToClosestCell(Point2D(25, 25), grid, Tiles.OXYGEN_SYSTEM)
    val oxygenSystemPosition = pathToOxygenSystem.fold(originalPosition, { currentPostion, direction -> direction.nextPosition(currentPostion) })

    return findPathToFurthest(oxygenSystemPosition, grid).size
}

private fun fillGridWithMaze(originalPostion: Point2D, grid: MutableList<MutableList<Tiles>>, computer: Computer) {
    var currentPosition = originalPostion
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

fun findPathToFurthest(position: Point2D, grid: List<List<Tiles>>): List<Direction> {
    val emptyAroundPosition = findEmptyAround(position, grid, null).map { Pair(mutableListOf(it), it.nextPosition(position)) }.toMutableList()
    val visitedPositions = mutableSetOf<Point2D>(position)
    val finalPaths = mutableListOf<List<Direction>>()
    while (emptyAroundPosition.size > 0) {
        val testedCurrentEmptyPosition = emptyAroundPosition.removeAt(0)
        val emptyAround = findEmptyAround(testedCurrentEmptyPosition.second, grid, testedCurrentEmptyPosition.first.last());
        if (emptyAround.isEmpty()) {
            finalPaths.add(testedCurrentEmptyPosition.first)
        } else {
            val pathsToUse = emptyAround.map { Pair(testedCurrentEmptyPosition.first.plus(it).toMutableList(), it.nextPosition(testedCurrentEmptyPosition.second)) }
                    .filterNot { visitedPositions.contains(it.second) }
            visitedPositions.addAll(pathsToUse.map { it.second })
            emptyAroundPosition.addAll(pathsToUse)
        }
    }

    return finalPaths.maxBy { it.size }!!
}

private fun printGrid(grid: MutableList<MutableList<Tiles>>) {
    grid.forEach {
        println(it.map { it.value }.joinToString(""))
    }
}