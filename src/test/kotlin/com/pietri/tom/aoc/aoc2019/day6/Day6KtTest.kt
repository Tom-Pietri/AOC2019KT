package com.pietri.tom.aoc.aoc2019.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*

internal class Day6KtTest {
    @Test
    fun `Example input should return 42`() {
        val input = Arrays.asList("COM)B","B)C","C)D","D)E","E)F","B)G","G)H","D)I","E)J","J)K","K)L")
        assertThat(makeResultFirstResult(input)).isEqualTo(42)
    }

    @Test
    fun `First answer my Input should return return 119831`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day6/day6.input").readLines(Charsets.UTF_8)
                .filter { it.isNotBlank() }
        assertThat(makeResultFirstResult(input)).isEqualTo(119831)
    }

    @Test
    fun `Example 2 input should return 4`() {
        val input = Arrays.asList("COM)B","B)C","C)D","D)E","E)F","B)G","G)H","D)I","E)J","J)K","K)L","K)YOU","I)SAN")
        assertThat(makeSecondResult(input)).isEqualTo(4)
    }

    @Test
    fun `Second answer my Input should return return 7`() {
        val input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day6/day6.input").readLines(Charsets.UTF_8)
                .filter { it.isNotBlank() }
        assertThat(makeSecondResult(input)).isEqualTo(322)
    }
}

