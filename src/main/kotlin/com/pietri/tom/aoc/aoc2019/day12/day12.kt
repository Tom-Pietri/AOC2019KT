package com.pietri.tom.aoc.aoc2019.day12

import kotlin.math.abs

fun computeFirstSolution(input: List<Vector3D>): Int {
    val moons = input.map { Moon(it) }
    return getTotalEnergyAfterSteps(moons, 1000)
}

fun getTotalEnergyAfterSteps(moons: List<Moon>, nbSteps: Long): Int {
    var currentUniverse = Universe(moons)

    for (i in 0 until nbSteps) {
        currentUniverse = currentUniverse.simulateNextStep()
    }

    return currentUniverse.getTotalEnergy()
}

data class Vector3D(var x: Int, var y: Int, var z: Int) {
    fun add(vector: Vector3D): Vector3D {
        return Vector3D(this.x + vector.x, this.y + vector.y, this.z + vector.z)
    }

    fun absSum(): Int {
        return abs(x) + abs(y) + abs(z)
    }
}

data class Universe(private val moons: List<Moon>) {
    fun simulateNextStep(): Universe {
        val updatedMoons = moons.map {
            val otherMoons = moons.minus(it)
            val nextVelocity = it.computeNextVelocity(otherMoons)
            Pair(it, nextVelocity);
        }.map { it.first.nextMoonPosition(it.second) }

        return Universe(updatedMoons)
    }

    fun getTotalEnergy() = moons.map { it.computeTotalEnergy() }.sum();
}

data class Moon(var position: Vector3D, var velocity: Vector3D = Vector3D(0, 0, 0)) {

    fun nextMoonPosition(velocity: Vector3D) = Moon(position.add(velocity), velocity)

    fun computeNextVelocity(otherMoons: List<Moon>): Vector3D {
        val xChange = otherMoons.map { velocityChange(position.x, it.position.x) }.sum()
        val yChange = otherMoons.map { velocityChange(position.y, it.position.y) }.sum()
        val zChange = otherMoons.map { velocityChange(position.z, it.position.z) }.sum()

        return velocity.add(Vector3D(xChange, yChange, zChange))
    }

    private fun velocityChange(first: Int, second: Int): Int {
        return when {
            first < second -> +1
            first > second -> -1
            else -> 0
        }
    }

    fun computeTotalEnergy(): Int {
        return computeKineticEnergy() * computePotentialEnergy();
    }

    private fun computePotentialEnergy(): Int {
        return position.absSum()
    }

    private fun computeKineticEnergy(): Int {
        return velocity.absSum()
    }
}