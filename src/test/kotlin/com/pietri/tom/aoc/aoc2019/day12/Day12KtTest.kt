package com.pietri.tom.aoc.aoc2019.day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class Day12KtTest {

    @Test
    fun `Part 1 exempleInput after 10 steps is 179`() {
        val input = listOf(Vector3D(-1, 0, 2),
                Vector3D(2, -10, -7),
                Vector3D(4, -8, 8),
                Vector3D(3, 5, -1))
        val moons = input.map { Moon(it) }
        assertThat(getTotalEnergyAfterSteps(moons, 10)).isEqualTo(179)
    }

    @Test
    fun `Part 1 solution should be 5937`() {
        val input = listOf(Vector3D(-6, -5, -8),
                Vector3D(0, -3, -13),
                Vector3D(-15, 10, -11),
                Vector3D(-3, -8, 3))
        assertThat(computeFirstSolution(input)).isEqualTo(5937)
    }
}