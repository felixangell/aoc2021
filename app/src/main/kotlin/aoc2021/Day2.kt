import aoc2021.Resource

//val input = Resource.loadResource("/day2.txt")
val input = listOf(
    "forward 5",
    "down 5",
    "forward 8",
    "up 3",
    "down 8",
    "forward 2"
)

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

fun day2extra() {
    val parts = input.map {
        val (direction, dist) = it.split(" ")
        (direction to dist.toInt())
    }

    var depth = 0
    var horizontal = 0
    var aim = 0
    parts.forEach { (dir, dist) ->
        when (dir) {
            "up" -> {
                depth -= dist
                aim -= dist
            }
            "down" -> {
                depth += dist
                aim += dist
            }
            "forward" -> {
                horizontal += dist
                depth += aim * dist
            }
        }
    }

    val result = horizontal * depth
    println("result = $result")
}

fun main() {
    day2()
}