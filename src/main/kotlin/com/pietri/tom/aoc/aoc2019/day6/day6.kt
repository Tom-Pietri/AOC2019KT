package com.pietri.tom.aoc.aoc2019.day6

import sun.security.util.Length

fun makeResultFirstResult(input: List<String>): Int {
    return makeNodesMap(input).values.map { numberOfParentsOfNode(it) }.sum()
}

fun makeSecondResult(input: List<String>): Int {
    return distanceBetweenNodes("YOU", "SAN", makeNodesMap(input))
}

private fun makeNodesMap(input: List<String>): Map<String, Node> {
    val nodes = HashMap<String, Node>()
    input.forEach { addOrbitsToNode(it, nodes) }

    return nodes
}

fun addOrbitsToNode(orbit: String, nodes: HashMap<String, Node>) {
    val (parentKey, childKey) = orbit.split(")")
    val child = nodes[childKey] ?: Node(null, mutableListOf(), childKey)
    val parent = nodes[parentKey] ?: Node(null, mutableListOf(), parentKey)
    parent.childrens.add(child)
    child.parent = parent
    nodes[parentKey] = parent
    nodes[childKey] = child
}

fun distanceBetweenNodes(start: String, end: String, nodes: Map<String, Node>): Int {
    val startNode: Node = nodes[start]!!
    val targetNode: Node = nodes[end]!!

    var firstCommonParent: Node? = null;
    var startNodeCurrentParent: Node = startNode.parent!!
    var endNodeCurrentParent: Node?
    var firstVal = 0;
    var secondVal = 0;
    while (firstCommonParent == null) {
        secondVal = 0;
        endNodeCurrentParent = targetNode.parent
        while (endNodeCurrentParent != null && firstCommonParent == null) {
            if (endNodeCurrentParent.value.equals(startNodeCurrentParent.value)) {
                firstCommonParent = startNodeCurrentParent
            } else {
                secondVal++
            }
            endNodeCurrentParent = endNodeCurrentParent.parent
        }
        if (firstCommonParent == null) {
            startNodeCurrentParent = startNodeCurrentParent.parent!!
            firstVal++
        }
    }


    val targetNodeParents = mutableListOf<Node>()
    startNode.childrens.any { it.value.equals(targetNode.value) }
    return firstVal + secondVal;
}

fun numberOfParentsOfNode(node: Node): Int {
    var parent = node.parent
    var result = 0;
    while (parent != null) {
        result++
        parent = parent.parent
    }

    return result
}

data class Node(var parent: Node?, val childrens: MutableList<Node>, val value: String)
