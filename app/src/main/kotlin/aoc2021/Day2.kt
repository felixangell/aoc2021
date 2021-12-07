import aoc2021.Resource

private val input = Resource.loadResource("/day2.txt")

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
            "up" -> aim -= dist
            "down" -> aim += dist
            "forward" -> {
                horizontal += dist
                val depthDelta = aim * dist
                println(depthDelta)
                depth += depthDelta
            }
        }
    }

    val result = horizontal * depth
    println("$horizontal x $depth = $result")
}

fun main() {
    day2extra()
}