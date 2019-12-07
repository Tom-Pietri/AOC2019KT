package com.pietri.tom.aoc.aoc2019.day2


fun findFirstOpCodeFor(s : String): Int {
    return runOpCode(s).first()
}

fun runOpCode(s: String): List<Int> {
    var intcodes = s.split(",").map { it.toInt() }.toMutableList()


    var position = 0;
    var opCode = intcodes[position];
    while(opCode != 99) {
        when (opCode) {
            1 -> {
                var param1 = intcodes.get(position + 1)
                var param2 = intcodes.get(position + 2)
                var param3 = intcodes.get(position + 3)
                intcodes[param3] = intcodes.get(param1) + intcodes.get(param2)
                position += 4
            }
            2 -> {
                var param1 = intcodes.get(position + 1)
                var param2 = intcodes.get(position + 2)
                var param3 = intcodes.get(position + 3)
                intcodes[param3] = intcodes.get(param1) * intcodes.get(param2)
                position += 4
            }
        }
        opCode = intcodes[position];
    }
    return intcodes
}
