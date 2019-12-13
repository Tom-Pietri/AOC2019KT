package com.pietri.tom.aoc.aoc2019.day13



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

data class Tile(val position: Point2D, val tileId: Int);

data class Point2D(val x: Int,val y: Int);

enum class TileType(val id: Int) {
    EMPTY(0),
    WALL(1),
    BLOCK(2),
    PADDLE(3),
    BALLS(4)
}