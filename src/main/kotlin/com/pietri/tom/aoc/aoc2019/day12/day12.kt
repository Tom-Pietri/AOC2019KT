package com.pietri.tom.aoc.aoc2019.day12

import kotlin.math.abs

fun computeFirstSolution(input: List<Vector3D>): Int {
    val moons = input.map { Moon(it) }
    return getTotalEnergyAfterSteps(moons, 1000)
}

fun computeSecondSolution(input: List<Vector3D>): Long {
    val moons = input.map { Moon(it) }
    var currentUniverse = Universe(moons)
    val xPairSet = hashSetOf<List<Pair<Int, Int>>>();
    val yPairSet = hashSetOf<List<Pair<Int, Int>>>();
    val zPairSet = hashSetOf<List<Pair<Int, Int>>>();
    var firstSameXStep: Long? = null
    var firstSameYStep: Long? = null
    var firstSameZStep: Long? = null
    var i: Long = 0
    while (firstSameXStep == null || firstSameYStep == null || firstSameZStep == null) {
        if (firstSameXStep == null) {
            val currentUniverseXPairs = currentUniverse.makeXPairs()
            val contains = xPairSet.contains(currentUniverseXPairs)
            if (contains) {
                firstSameXStep = i
            } else {
                xPairSet.add(currentUniverseXPairs)
            }
        }
        if (firstSameYStep == null) {
            val currentUniverseYPairs = currentUniverse.makeYPairs()
            val contains = yPairSet.contains(currentUniverseYPairs)
            if (contains) {
                firstSameYStep = i
            } else {
                yPairSet.add(currentUniverseYPairs)
            }
        }
        if (firstSameZStep == null) {
            val currentUniverseZPairs = currentUniverse.makeZPairs()
            val contains = zPairSet.contains(currentUniverseZPairs)
            if (contains) {
                firstSameZStep = i
            } else {
                zPairSet.add(currentUniverseZPairs)
            }
        }
        currentUniverse = currentUniverse.simulateNextStep()
        i++
    }

    val lcmX_Y = lcm(firstSameXStep, firstSameYStep)
    val lcm = lcm(lcmX_Y, firstSameZStep)
    return lcm
}

fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b

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

data class Universe(val moons: List<Moon>) {
    fun simulateNextStep(): Universe {
        val updatedMoons = moons.map {
            val otherMoons = moons.minus(it)
            val nextVelocity = it.computeNextVelocity(otherMoons)
            Pair(it, nextVelocity);
        }.map { it.first.nextMoonPosition(it.second) }

        return Universe(updatedMoons)
    }

    fun getTotalEnergy() = moons.map { it.computeTotalEnergy() }.sum()

    fun makeXPairs(): List<Pair<Int, Int>> {
        return moons.map { Pair(it.position.x, it.velocity.x) }
    }

    fun makeYPairs(): List<Pair<Int, Int>> {
        return moons.map { Pair(it.position.y, it.velocity.y) }
    }

    fun makeZPairs(): List<Pair<Int, Int>> {
        return moons.map { Pair(it.position.z, it.velocity.z) }
    }
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