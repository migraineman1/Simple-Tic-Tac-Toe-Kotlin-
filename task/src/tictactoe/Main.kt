package tictactoe

//import kotlin.math.abs

fun main() {
    // set up variables
    var getMove: String
    var parseMove: List<String>
    var moveY: Int
    var moveX: Int
    var validEntry: Boolean
    var turnCount = 0


    var gameOver = false
    var player = 'X'

    // print the current game grid
    println("---------")
    for (i in 0..2) {
        println("|       |")
    }
    println("---------")

    // generate board
    val board = mutableListOf(mutableListOf(' ', ' ', ' '), mutableListOf(' ', ' ', ' '), mutableListOf(' ', ' ', ' '))

    // begin gameplay loop
    do {
        validEntry = false

        // get move from user and check for validity
        getMove = readln()
        while (!validEntry) {
            if (!("\\d+\\s\\d+".toRegex().matches(getMove))) {
                println("You should enter numbers!")
                getMove = readln()
            } else {
                parseMove = getMove.split(" ")
                moveX = parseMove[0].toInt()
                moveY = parseMove[1].toInt()
                if (moveX < 1 || moveX > 3 || moveY < 1 || moveY > 3) {
                    println("Coordinates should be from 1 to 3!")
                    getMove = readln()
                } else {
                    if (board[moveX - 1][moveY - 1] == 'X' || board[moveX - 1][moveY - 1] == 'O') {
                        println("This cell is occupied! Choose another one!")
                        getMove = readln()
                    } else {
                        board[moveX - 1][moveY - 1] = player
                        validEntry = true
                        turnCount++

                        // change players for next entry
                        if (player == 'X') { player = 'O' }
                        else { player = 'X' }
                    }
                }
            }
        }

        // print the updated grid
        println("---------")
        for (i in 0..2) {
            println("| ${board[i][0]} ${board[i][1]} ${board[i][2]} |")
        }
        println("---------")

        // check if game is over

        // check if Xs or Os win
        fun winner(symbol: Char): Boolean {

            // check for win in rows
            for (i in 0..2) {
                if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true
            }

            // check for win in columns
            for (i in 0..2) {
                if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true
            }

            // check for diagonal wins
            if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true
            if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true

            return false
        }

        if (winner('X')) {
            println("X wins")
            gameOver = true
        } else if (winner('O')) {
            println("O wins")
            gameOver = true
        } else if (turnCount == 9) {
            println("Draw")
            gameOver = true
        }


    } while (!gameOver)



}