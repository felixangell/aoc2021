import aoc2021.Resource
import kotlin.math.abs

private val sample = "16,1,2,0,4,2,7,1,2,14"
private val input = Resource.loadResource("/day7.txt").first()

fun day7() {
    val horizontals = input.split(",").map { it.toInt() }
    val median = horizontals.sorted()[horizontals.size / 2]
    val fuel = horizontals.sumOf { hp -> abs(hp - median) }
    println(fuel)
}

fun day7extra() {
    val horizontals = input.split(",").map { it.toInt() }
    val avg = horizontals.sum() / horizontals.size
    val fuel = horizontals.sumOf { hp -> (1..abs(hp - avg)).sum() }
    println(fuel)
}

fun main() {
    day7extra()
}