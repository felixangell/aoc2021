package aoc2021

private val sample = "3,4,3,1,2"
private val input = Resource.loadResource("/day6.txt")

fun simulate(initialState: List<Long>, daysToSimulate: Int): Long {
    val state = Array<Long>(9) { 0 }
    initialState.forEach { x -> x.toInt().also { state[it]++ } }

    for (day in 0 until daysToSimulate) {
        val f0 = state[0]
        for (j in 0 until state.size - 1) {
            state[j] = state[j + 1]
        }
        state[6] += f0
        state[8] = f0
    }
    return state.sum()
}


fun day6() {
    val initialState = input.first().split(",")
        .map { it.toLong() }

    val daysToSimulate = 256
    val count = simulate(initialState, daysToSimulate)
    println("there are $count fish after $daysToSimulate days")
}

fun main() {
    day6()
}