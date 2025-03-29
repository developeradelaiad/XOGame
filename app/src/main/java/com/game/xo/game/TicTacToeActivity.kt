package com.game.xo.game

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.game.xo.R

class TicTacToeActivity : AppCompatActivity() {
    private lateinit var game: TicTacToeGame
    private lateinit var statusText: TextView
    private lateinit var buttons: Array<Array<Button>>
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)

        game = TicTacToeGame()
        statusText = findViewById(R.id.statusText)
        resetButton = findViewById(R.id.resetButton)

        // Initialize buttons array
        buttons = Array(3) { row ->
            Array(3) { col ->
                findViewById<Button>(resources.getIdentifier(
                    "btn$row$col",
                    "id",
                    packageName
                ))
            }
        }

        // Set click listeners for all buttons
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].setOnClickListener { onButtonClick(i, j) }
            }
        }

        // Set reset button click listener
        resetButton.setOnClickListener {
            game.resetGame()
            updateUI()
        }
    }

    private fun onButtonClick(row: Int, col: Int) {
        if (game.makeMove(row, col)) {
            buttons[row][col].text = if (game.getCurrentPlayer() == "X") "O" else "X"
            
            val winner = game.checkWinner()
            when (winner) {
                "X" -> statusText.text = "Player X Wins!"
                "O" -> statusText.text = "Player O Wins!"
                "Draw" -> statusText.text = "It's a Draw!"
                null -> statusText.text = "Player ${game.getCurrentPlayer()}'s Turn"
            }
        }
    }

    private fun updateUI() {
        // Clear all buttons
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].text = ""
            }
        }
        statusText.text = "Player X's Turn"
    }
} 