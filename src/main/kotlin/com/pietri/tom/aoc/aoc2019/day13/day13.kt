package com.pietri.tom.aoc.aoc2019.day13

import java.math.BigInteger


fun computeFirstSolution(input: String): Int {
    val computer = Computer(input.split(",").map { it.toBigInteger() }.toMutableList())
    val board = mutableListOf<Tile>()
    do{
        val nextOutputs = computer.getNexOutputs(0, 3)
        if(nextOutputs != null) {
            val (x,y,tileId) = nextOutputs;
            board.add(Tile(Point2D(x, y), tileId));
        }
    } while (nextOutputs != null)

    return board.filter { it.tileId == TileType.BLOCK.id }.size
}

fun computeSecondSolution(input: String): Int {
    val computerInstructions = input.split(",").map { it.toBigInteger() }.toMutableList()
    computerInstructions[0] = BigInteger.valueOf(2)
    val computer = Computer(computerInstructions)
    var score = 0
    do {
        val nextOutputs = computer.getNexOutputs(0, 3)
        if (nextOutputs != null) {
            val (x, y, tileId) = nextOutputs
            if (x == -1) {
                score = tileId
            }
        }
    } while (nextOutputs != null)

    return score
}

data class Tile(val position: Point2D, val tileId: Int);

data class Point2D(val x: Int,val y: Int);

enum class TileType(val id: Int) {
    EMPTY(0),
    WALL(1),
    BLOCK(2),
    PADDLE(3),
    BALL(4)
}