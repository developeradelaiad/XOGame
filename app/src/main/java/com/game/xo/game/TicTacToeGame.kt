package com.game.xo.game

class TicTacToeGame {
    private val board = Array(3) { Array(3) { "" } }
    private var currentPlayer = "X"
    private var gameOver = false

    fun makeMove(row: Int, col: Int): Boolean {
        if (gameOver || board[row][col].isNotEmpty()) {
            return false
        }

        board[row][col] = currentPlayer
        currentPlayer = if (currentPlayer == "X") "O" else "X"
        return true
    }

    fun checkWinner(): String? {
        // Check rows
        for (i in 0..2) {
            if (board[i][0].isNotEmpty() &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2]) {
                gameOver = true
                return board[i][0]
            }
        }

        // Check columns
        for (i in 0..2) {
            if (board[0][i].isNotEmpty() &&
                board[0][i] == board[1][i] &&
                board[1][i] == board[2][i]) {
                gameOver = true
                return board[0][i]
            }
        }

        // Check diagonals
        if (board[0][0].isNotEmpty() &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) {
            gameOver = true
            return board[0][0]
        }

        if (board[0][2].isNotEmpty() &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) {
            gameOver = true
            return board[0][2]
        }

        // Check for draw
        if (isBoardFull()) {
            gameOver = true
            return "Draw"
        }

        return null
    }

    private fun isBoardFull(): Boolean {
        return board.all { row -> row.all { it.isNotEmpty() } }
    }

    fun getCurrentPlayer(): String = currentPlayer

    fun isGameOver(): Boolean = gameOver

    fun resetGame() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = ""
            }
        }
        currentPlayer = "X"
        gameOver = false
    }
} 