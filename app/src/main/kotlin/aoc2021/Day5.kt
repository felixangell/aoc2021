package aoc2021

import java.lang.Integer.signum
import kotlin.math.abs
import kotlin.math.max

val sample = listOf(
    "0,9 -> 5,9",
    "8,0 -> 0,8",
    "9,4 -> 3,4",
    "2,2 -> 2,1",
    "7,0 -> 7,4",
    "6,4 -> 2,0",
    "0,9 -> 2,9",
    "3,4 -> 1,4",
    "0,0 -> 8,8",
    "5,5 -> 8,2",
)
val lines = Resource.loadResource("/day5.txt")

fun pp(pt: List<String>) = pt
    .map { it.split(",").map { v -> v.toInt() }.zipWithNext() }
    .flatten()
    .let {
        val (from, to) = it
        listOf(from.first, from.second, to.first, to.second)
    }

fun day5() {
    val day2Enabled = true

    val points = lines.map {
        pp(it.replace(" ", "").split("->"))
    }

    val lines = points.asSequence().map { (x1, y1, x2, y2) ->
        val xl = abs(x2 - x1)
        val yl = abs(y2 - y1)
        val isStraight = xl == 0 || yl == 0
        if (!isStraight && !day2Enabled) return@map null
        (0..max(xl, yl))
            .map {
                // startX + linePointX * sign(direction of x)
                val px = x1 + it * (signum(x2 - x1))
                val py = y1 + it * (signum(y2 - y1))
                px to py
            }
    }
        .filterNotNull()
        .flatten()
        .groupBy { it }
        .filter { it.value.size >= 2 }.toList()

    println(lines.size)
}

fun main() {
    day5()
}