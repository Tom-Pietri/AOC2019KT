package com.pietri.tom.aoc.aoc2019.day14

import kotlin.math.ceil

fun computeFirstSolution(input: List<String>): Int {
    val reactionByResult = mapPuzzleInputToReaction(input)

    return makeOneFuel(reactionByResult, emptyMap()).first
}

fun computeSecondSolution(input: List<String>): Int {
    val reactionByResult = mapPuzzleInputToReaction(input)
    var availableOre = 1000000000000
    var remainingElements = mapOf<String, Int>()
    var fuelMade = 0
    while (availableOre > 0) {
        val (oreUsed, newRemainingElements) = makeOneFuel(reactionByResult, remainingElements)
        remainingElements = newRemainingElements;
        availableOre -= oreUsed
        if(availableOre > 0) {
            fuelMade++
        }
    }

    return fuelMade
}

fun makeOneFuel(reactionByResult: Map<String, Reaction>, remainingElements: Map<String, Int>): Pair<Int, Map<String, Int>> {
    val fuelReaction = reactionByResult.get("FUEL") ?: error("should exist")
    val mutableRemainingElements = remainingElements.toMutableMap()
    val requiredElementsToAdd = fuelReaction.required.toMutableList()
    var totalOre = 0;
    while (requiredElementsToAdd.size > 0) {
        val elementToCreate = requiredElementsToAdd.removeAt(0)
        if (elementToCreate.chemical != "ORE") {
            val reactionToCreateElement = reactionByResult[elementToCreate.chemical]!!

            val remainingQuantityOfElementToMake = mutableRemainingElements[elementToCreate.chemical] ?: 0

            val quantityNeeded = elementToCreate.quantity - remainingQuantityOfElementToMake
            val quantityToMake = ceil((quantityNeeded.toDouble() / reactionToCreateElement.produced.quantity)).toInt()

            mutableRemainingElements[elementToCreate.chemical] = remainingQuantityOfElementToMake + (quantityToMake * reactionToCreateElement.produced.quantity) - elementToCreate.quantity
            if (quantityToMake > 0) {
                val required = reactionByResult[elementToCreate.chemical]!!.required;
                requiredElementsToAdd.addAll(required.map { ChemicalWithQuantity(it.quantity * quantityToMake, it.chemical) })
            }
        } else {
            totalOre += elementToCreate.quantity
        }
    }

    return Pair(totalOre, mutableRemainingElements)
}

private fun mapPuzzleInputToReaction(input: List<String>): Map<String, Reaction> {
    return inputStringToReactions(input)
            .map { it.produced.chemical to it }
            .toMap()
}

fun inputStringToReactions(reactions: List<String>): List<Reaction> {
    return reactions.map(::separateProducedAndRequired)
            .map { Pair(splitRequired(it.first), it.second) }
            .map(::trimWhiteSpace)
            .map(::makeReactionFromSplitedString)
}

fun separateProducedAndRequired(reaction: String): Pair<String, String> {
    val (required, produced) = reaction.split("=>")
    return Pair(required, produced)
}

fun splitRequired(input: String): List<String> {
    return input.split(",")
}

fun trimWhiteSpace(reaction: Pair<List<String>, String>): Pair<List<String>, String> {
    return Pair(reaction.first.map { it.trim() }, reaction.second.trim())
}

fun makeReactionFromSplitedString(stringReaction: Pair<List<String>, String>): Reaction {
    val required = stringReaction.first.map { transformToChemical(it) }
    val produced = transformToChemical(stringReaction.second)
    return Reaction(required, produced);
}

fun transformToChemical(input: String): ChemicalWithQuantity {
    val (count, element) = input.split(" ")
    return ChemicalWithQuantity(count.toInt(), element)
}

data class Reaction(var required: List<ChemicalWithQuantity>,
                    var produced: ChemicalWithQuantity)


data class ChemicalWithQuantity(val quantity: Int, val chemical: String)