package aoc2021

import kotlin.math.pow

val input = Resource.loadResource("/day3.txt")

fun Array<Int>.bitsToInt(): Int = this.mapIndexed { index, i ->
    when (i) {
        1 -> 2.0.pow(this.size - index - 1).toInt()
        else -> 0
    }
}.sum()

fun day3() {
    val data = input.map { it.toCharArray().map { ch -> ch.digitToInt() } }
    val col = fun(at: Int) = data.map { it[at] }

    val gammaBits = Array(data.first().size) { 0 }
    val epsilonBits = Array(data.first().size) { 0 }
    data[0].indices.forEach { i ->
        val commonBits = col(i).groupingBy { it }.eachCount()
        gammaBits[i] = commonBits.maxByOrNull { it.value }?.key!!
        epsilonBits[i] = commonBits.minByOrNull { it.value }?.key!!
    }

    val gamma = gammaBits.bitsToInt()
    val epsilon = epsilonBits.bitsToInt()
    println("$gamma vs $epsilon = ${gamma * epsilon}")
}

fun main() {
    day3()
}