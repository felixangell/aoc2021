import aoc2021.Resource

val input = Resource.loadResource("/day2.txt")

fun day2() {
    val parts = input.map {
        val (direction, dist) = it.split(" ")
        (direction to dist.toInt())
    }

    var depth = 0
    var horizontal = 0
    parts.forEach { (dir, dist) ->
        when (dir) {
            "up" -> depth -= dist
            "down" -> depth += dist
            "forward" -> horizontal += dist
        }
    }

    val result = horizontal * depth
    println("result = $result")
}

fun main() {
    day2()
}