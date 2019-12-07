package com.pietri.tom.aoc.aoc2019.day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

class Day5KtTest {
    @Test
    fun `My input should return 742621`() {
        var input = File("./src/main/kotlin/com/pietri/tom/aoc/aoc2019/day5/day5.input").readLines(Charsets.UTF_8).get(0)
        assertThat(runOpCode(input, 5)).isEqualTo(742621);
    }
}
