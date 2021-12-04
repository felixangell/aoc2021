package aoc2021

private val manualInput = listOf(
    "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",

    "22 13 17 11  0",
    "8  2 23  4 24",
    "21  9 14 16  7",
    "6 10  3 18  5",
    "1 12 20 15 19",

    "3 15  0  2 22",
    "9 18 13 17  5",
    "19  8  7 25 23",
    "20 11 10 24  4",
    "14 21 16 12  6",

    "14 21 17 24  4",
    "10 16 15  9 19",
    "18  8 23 26 20",
    "22 11 13  6  5",
    "2  0 12  3  7",
)
private val input = Resource.loadResource("/day4.txt")

const val BOARD_SIZE = 5

data class BoardPoint(
    val number: Int,
    val position: Pair<Int, Int>
)

data class Board(
    val data: List<Int>,
    val width: Int = BOARD_SIZE, val height: Int = BOARD_SIZE,
    val marked: MutableSet<BoardPoint> = mutableSetOf(),
) {
    fun mark(number: Int, position: Int) {
        val point = ((position % width) to (position / width))
        marked.add(BoardPoint(number, point))
    }

    fun hasWinner(): Boolean {
        val xs = marked.groupingBy { it.position.first }
            .eachCount()
            .maxByOrNull { it.value }?.value

        val ys = marked.groupingBy { it.position.second }
            .eachCount()
            .maxByOrNull { it.value }?.value

        return xs == BOARD_SIZE || ys == BOARD_SIZE
    }

    override fun toString(): String {
        val res = StringBuffer()
        data.chunked(BOARD_SIZE).forEach { record ->
            record.forEach { i -> res.append(if (marked.map { it.number }.contains(i)) "[$i]," else "($i),") }
            res.append("\n")
        }
        return res.toString()
    }
}

class BoardSolver {
    companion object {
        fun solve(board: Board, number: Int): Boolean {
            val itemIndex = board.data.indexOf(number)
            if (itemIndex != -1) {
                board.mark(number, itemIndex)
            }
            return board.hasWinner()
        }

        fun score(board: Board, winningNumber: Int): Any {
            val markedNumbers = board.marked.map { it.number }
            val excess = board.data.filter { !markedNumbers.contains(it) }
            return excess.sum() * winningNumber
        }
    }
}

fun day4() {
    val randomNumbers = input[0].split(",").map { it.toInt() }

    val row = input.slice(1 until input.size).map {
        if (it.isEmpty()) return@map emptyList<Int>()
        it.split(" ").filter { i -> i.isNotEmpty() }.map { i -> i.toInt() }
    }.filter { it.isNotEmpty() }

    val records = row.chunked(BOARD_SIZE)
    val boards = records.map { boardRecords -> Board(boardRecords.flatten()) }

    val findWinner = fun(): Pair<Board, Int>? {
        randomNumbers.forEach { number ->
            val winner = boards.map { it to BoardSolver.solve(it, number) }
                .find { it.second }
            winner?.let { (board) ->
                return board to number
            }
        }
        return null
    }

    findWinner()?.let { (board, winningNumber) ->
        val score = BoardSolver.score(board, winningNumber)
        println("Winning board score: $score")
    }
}

fun main() {
    day4()
}