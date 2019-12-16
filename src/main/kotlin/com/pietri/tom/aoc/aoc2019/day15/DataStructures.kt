package com.pietri.tom.aoc.aoc2019.day15

enum class Direction(val value: Int) {
    NORTH(1) {
        override fun nextPosition(currentPosition: Point2D) = currentPosition.add(Point2D(0, -1))
        override fun oppositeDirection() = SOUTH
    },
    SOUTH(2) {
        override fun nextPosition(currentPosition: Point2D) = currentPosition.add(Point2D(0, 1))
        override fun oppositeDirection() = NORTH
    },
    WEST(3) {
        override fun nextPosition(currentPosition: Point2D) = currentPosition.add(Point2D(-1, 0))
        override fun oppositeDirection() = EAST
    },
    EAST(4) {
        override fun nextPosition(currentPosition: Point2D) = currentPosition.add(Point2D(1, 0))
        override fun oppositeDirection() = WEST
    };

    abstract fun nextPosition(currentPosition: Point2D): Point2D
    abstract fun oppositeDirection(): Direction

}

enum class Tiles(val value: String) {
    EMPTY(" "),
    WALL("■"),
    OXYGEN_SYSTEM("O"),
    UNKNOWN(" "),
    PATH("x");

    companion object {
        fun forValue(value: Int): Tiles {
            return when (value) {
                0 -> Tiles.WALL
                1 -> Tiles.EMPTY
                2 -> Tiles.OXYGEN_SYSTEM
                else -> Tiles.UNKNOWN
            }
        }
    }
}

data class Point2D(val x: Int, val y: Int) {
    fun add(point2D: Point2D): Point2D {
        return Point2D(point2D.x + x, point2D.y + y)
    }
}