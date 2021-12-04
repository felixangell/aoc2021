package aoc2021

import kotlin.math.pow

//val input = Resource.loadResource("/day3.txt")
private val input = listOf(
    "00100",
    "11110",
    "10110",
    "10111",
    "10101",
    "01111",
    "00111",
    "11100",
    "10000",
    "11001",
    "00010",
    "01010",
)

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

fun day3extra() {
    val data = input
        .map { it.toCharArray().map { ch -> ch.digitToInt() } }

    val col = fun(at: Int) = data.map { it[at] }

    val filterBy = fun(data: MutableList<List<Int>>): MutableList<List<Int>> {
        data[0].indices.forEach { i ->
            val cols = col(i)
            val commonBits = cols.groupingBy { it }.eachCount()
            val mostCommon = commonBits.maxByOrNull { it.value }?.key!!
            println("mc $mostCommon for $i")

            val badRows = data
                .mapNotNull { row -> if (row[i] != mostCommon) row else null }
            badRows.forEach { data.remove(it) }
        }
        return data
    }

    val mostCommon = filterBy(data.toMutableList())
    println(mostCommon)

    // oxygen = final filtered by most common with a 1
    // c02 = final filtered by least common with a 0
}

fun main() {
    day3extra()
}