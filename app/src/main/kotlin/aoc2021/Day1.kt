package aoc2021

class Resource {
    companion object {
        fun loadResource(path: String) = Resource::class.java.getResourceAsStream(path)
            .bufferedReader().readLines()
    }
}

val depths = Resource.loadResource("/day1.txt")

enum class Measurement(private val type: String) {
    Increase("INC"), Decrease("DEC"), Equal("EQL"), None("N/A");

    override fun toString(): String {
        return type
    }
}

fun day1() {
    val measurementForDepth = depths
        .map { it.toInt() }
        .mapIndexed { index, value ->
            if (index == 0) {
                return@mapIndexed value to Measurement.None
            }

            val last = depths[index - 1].toInt()
            when {
                value > last -> value to Measurement.Increase
                else -> value to Measurement.Decrease
            }
        }

    println(measurementForDepth)

    val increases = measurementForDepth
        .filter { (_, v) -> v == Measurement.Increase }
    println(increases.size)
}

fun day2() {
    val diff = fun(curr: Int, last: Int): Measurement =
        when {
            curr < last -> Measurement.Decrease
            curr > last -> Measurement.Increase
            else -> Measurement.Equal
        }

    val measurementForDepth = depths
        .map { it.toInt() }
        .mapIndexed { index, value ->
            if (index == 0) {
                return@mapIndexed value to Measurement.None
            }
            value to diff(value, depths[index - 1].toInt())
        }

    val triples = depths.indices.mapIndexed { i, _ ->
        measurementForDepth
            .slice(i until measurementForDepth.size)
            .take(3)
    }.filter { it.size == 3 }

    var lastSum = -1
    val sumHistory = triples.dropLast(1).map {
        val sum = it.sumOf { p -> p.first }
        val ms = diff(sum, lastSum)
        lastSum = sum
        ms
    }

    val numIncreases = sumHistory
        .count { it == Measurement.Increase }

    println("num increases $numIncreases")
}

fun main() {
    day2()
}

/*
As the submarine drops below the surface of the ocean, it automatically performs a sonar sweep of the nearby sea floor. On a small screen, the sonar sweep report (your puzzle input) appears: each line is a measurement of the sea floor depth as the sweep looks further and further away from the submarine.

For example, suppose you had the following report:

199
200
208
210
200
207
240
269
260
263
This report indicates that, scanning outward from the submarine, the sonar sweep
found depths of 199, 200, 208, 210, and so on.

The first order of business is to figure out how quickly the depth increases, just so
you know what you're dealing with - you never know if the keys will get carried into
deeper water by an ocean current or a fish or something.

To do this, count the number of times a depth measurement increases from the previous
measurement. (There is no measurement before the first measurement.) In the example above,
the changes are as follows:

199 (N/A - no previous measurement)
200 (increased)
208 (increased)
210 (increased)
200 (decreased)
207 (increased)
240 (increased)
269 (increased)
260 (decreased)
263 (increased)
In this example, there are 7 measurements that are larger than the previous measurement.

How many measurements are larger than the previous measurement?
 */