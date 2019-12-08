package com.pietri.tom.aoc.aoc2019.day8

fun findLayerWithLeastZeroes(layers: List<String>): String {
    return layers.map { Pair(it, numberOfZeroesInLayer(it)) }.sortedBy { it.second }
            .first().first
}

fun numberOfZeroesInLayer(layer: String) = layer.count { it == '0' }
fun numberOfOnesInLayer(layer: String) = layer.count { it == '1' }
fun numberOfTwosInLayer(layer: String) = layer.count { it == '2' }

fun sliceImageIntoLayers(input: String, width: Int, length: Int): List<String> {
    val layerSize = width * length
    var position = 0;
    val output = mutableListOf<String>()
    while (position < input.length) {
        output.add(input.substring(position, position + layerSize))
        position += layerSize;
    }

    return output;
}

fun computeFirstSolution(input: String): Int {
    val layerWithLeastZeroes = findLayerWithLeastZeroes(sliceImageIntoLayers(input, 25, 6))
    return numberOfOnesInLayer(layerWithLeastZeroes) * numberOfTwosInLayer(layerWithLeastZeroes)
}

fun computeSecondSolution(input: String, width: Int = 25, length: Int = 6): List<String> {
    var addedLayers = StringBuilder("")
    val layers = sliceImageIntoLayers(input, width, length)
    for (i in 0 until (width * length)) {
        val firstRelevantLayer = layers.find { it[i] == '0' || it[i] == '1' }
        addedLayers.append(firstRelevantLayer!![i])
    }
    return sliceImageIntoLayers(addedLayers.toString(), width, 1)
}
