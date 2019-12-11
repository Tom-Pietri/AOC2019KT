package com.pietri.tom.aoc.aoc2019.day10

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2

fun findAllAsteroidsOnGrid(grid: List<List<Char>>): List<Point> {
    val output = mutableListOf<Point>()
    for (y in 0 until grid.size) {
        for (x in 0 until grid[y].size) {
            if (grid[y][x] == '#') {
                output.add(Point(x = x, y = y))
            }
        }
    }
    return output
}

fun makeAllPossibleVectorsFromOrigin(grid: List<List<Char>>): List<Vector> {
    val vectors = mutableListOf<Vector>()
    for (x in 0 until grid.size) {
        for (y in 0 until grid[x].size) {
            if (x == 0 && y == 0) {
                continue
            }
            val noOtherVector = vectors.none {
                if (x == 0) {
                    it.x == 0 && (it.y % y == 0)
                } else if (y == 0) {
                    (it.x % x == 0) && (it.y == 0)
                } else {
                    (it.x % x == 0) && (it.y % y == 0)
                }
            }
            if (noOtherVector) {
                vectors.add(Vector(x, y))
            }
        }
    }
    return vectors
}

fun computeFirstSolution(input: List<String>): Int {
    val grid = makeGridFromInput(input)
    val asteroidPositions = findAllAsteroidsOnGrid(grid)
    val vectorsPerAsteroids = asteroidPositions.map { pairPointWithVisibleAsteroids(it, asteroidPositions) }

    return vectorsPerAsteroids.map { it.second.size }.max()!!
}

fun computeSecondSolution(input: List<String>): Int {
    val grid = makeGridFromInput(input)
    val asteroidPositions = findAllAsteroidsOnGrid(grid)
    val vectorsPerAsteroids = asteroidPositions.map { pairPointWithVisibleAsteroids(it, asteroidPositions) }

    val vectorWithMaxVisibleAsteroids = vectorsPerAsteroids.maxBy { it.second.size }!!
    val topRightCorner = vectorWithMaxVisibleAsteroids.second.filter { it.x >= 0 && it.y < 0 }
    val bottomRightCorner = vectorWithMaxVisibleAsteroids.second.filter { it.x >= 0 && it.y >= 0 }
    val bottomLeftCorner = vectorWithMaxVisibleAsteroids.second.filter { it.x < 0 && it.y > 0 }
    val topLeftCorner = vectorWithMaxVisibleAsteroids.second.filter { it.x < 0 && it.y <= 0 }
    var totalCount = 0;
    if (topRightCorner.size + totalCount >= 200) {
        val topRightSortedByAngle = topRightCorner.sortedWith(compareBy { abs(it.toAngle) })
        val vector = topRightSortedByAngle[(200 - totalCount - 1)]
        return (vectorWithMaxVisibleAsteroids.first.x + vector.x) * 100 + (vectorWithMaxVisibleAsteroids.first.y + vector.y)
    } else {
        totalCount += topRightCorner.size
    }

    if (bottomRightCorner.size + totalCount >= 200) {
        val bottomRightSortedByAngle = bottomRightCorner.sortedWith(compareBy { abs(it.toAngle) })
        val vector = bottomRightSortedByAngle[(200 - totalCount - 1)]
        return (vectorWithMaxVisibleAsteroids.first.x + vector.x) * 100 + (vectorWithMaxVisibleAsteroids.first.y + vector.y)
    } else {
        totalCount += bottomRightCorner.size
    }

    if (bottomLeftCorner.size + totalCount >= 200) {
        val bottomLeftSortedByAngle = bottomLeftCorner.sortedWith(compareBy { abs(it.toAngle) })
        val vector = bottomLeftSortedByAngle[(200 - totalCount - 1)]
        return (vectorWithMaxVisibleAsteroids.first.x + vector.x) * 100 + (vectorWithMaxVisibleAsteroids.first.y + vector.y)
    } else {
        totalCount += bottomLeftCorner.size

    }

    if (topLeftCorner.size + totalCount >= 200) {

        val topLeftSortedByAngle = topLeftCorner.sortedWith(compareBy { abs(it.toAngle) })
        val vector = topLeftSortedByAngle[(200 - totalCount - 1)]
        return (vectorWithMaxVisibleAsteroids.first.x + vector.x) * 100 + (vectorWithMaxVisibleAsteroids.first.y + vector.y)
    } else {
        totalCount += topLeftCorner.size
    }

    return -1
}

private fun pairPointWithVisibleAsteroids(asteroidPosition: Point, asteroidPositions: List<Point>): Pair<Point, MutableList<Vector>> {
    val directLineOfSightForAsteroid = makeVectorsToGoToAllOtherAsteroids(asteroidPosition, asteroidPositions)
            .fold(mutableListOf<Vector>(), { acc, vector ->
                val colidingVector = acc.find { it.colinearWith(vector) && it.isInSameDirection(vector) }
                if (colidingVector == null) {
                    acc.add(vector)
                } else {
                    if (vector.manhattanLength < colidingVector.manhattanLength) {
                        acc[acc.indexOf(colidingVector)] = vector
                    }
                }
                acc
            })
    return Pair(asteroidPosition, directLineOfSightForAsteroid)
}

fun makeVectorsToGoToAllOtherAsteroids(origin: Point, asteroidPositions: List<Point>): List<Vector> {
    return asteroidPositions.map { Vector(it.x - origin.x, it.y - origin.y) }
            .filter { it.isNotNull() }
            .sortedWith(compareBy({ abs(it.x) }, { abs(it.y) }))
}

fun makeGridFromInput(input: List<String>) = input.map { it.toList() }.toList()


data class Point(val x: Int, val y: Int)
data class Vector(val x: Int, val y: Int) {

    fun add(vector: Vector): Vector {
        return Vector(this.x + vector.x, this.y + vector.y)
    }

    val manhattanLength = abs(x) + abs(y)

    val toAngle = atan2(x.toDouble(), y.toDouble()) * 100 / PI

    fun isNull(): Boolean {
        return (x == 0 && y == 0)
    }

    fun isNotNull(): Boolean {
        return !isNull()
    }

    fun colinearWith(vector: Vector): Boolean {
        return (this.x * vector.y) - (this.y * vector.x) == 0
    }

    fun isInSameDirection(vector: Vector): Boolean {
        val xInSameDirection = if (vector.x < 0) {
            x < 0
        } else {
            x >= 0
        }

        val yInSameDirection = if (vector.y < 0) {
            y < 0
        } else {
            y >= 0
        }

        return xInSameDirection && yInSameDirection
    }
}
