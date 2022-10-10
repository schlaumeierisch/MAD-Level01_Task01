package nl.hva.task01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import nl.hva.task01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var lastThrow: Int = 1
    private var currentThrow: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    /**
     * Set the initial (UI) state of the game.
     */
    private fun initViews() {
        updateUI()

        binding.btnHigher.setOnClickListener {
            onHigherClick()
        }

        binding.btnLower.setOnClickListener {
            onLowerClick()
        }

        binding.btnEquals.setOnClickListener {
            onEqualsClick()
        }
    }

    /**
     * Update the last throw text and the dice image resource drawable with the current throw.
     */
    private fun updateUI() {
        binding.tvLastThrow.text = getString(R.string.last_throw, lastThrow)

        when (currentThrow) {
            1 -> binding.ivDice.setImageResource(R.drawable.dice1)
            2 -> binding.ivDice.setImageResource(R.drawable.dice2)
            3 -> binding.ivDice.setImageResource(R.drawable.dice3)
            4 -> binding.ivDice.setImageResource(R.drawable.dice4)
            5 -> binding.ivDice.setImageResource(R.drawable.dice5)
            6 -> binding.ivDice.setImageResource(R.drawable.dice6)
        }
    }

    /**
     * Replaces the previous dice value with the current one and replaces the current dice with a new dice
     * with a random number between 1 and 6 (inclusive).
     */
    private fun rollDice() {
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUI()
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onHigherClick() {
        rollDice()
        val answerIsCorrect = currentThrow > lastThrow

        showAnswerFeedback(answerIsCorrect)
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onLowerClick() {
        rollDice()
        val answerIsCorrect = currentThrow < lastThrow

        showAnswerFeedback(answerIsCorrect)
    }

    /**
     * Calls [rollDice] and checks if the answer is correct.
     */
    private fun onEqualsClick() {
        rollDice()
        val answerIsCorrect = currentThrow == lastThrow

        showAnswerFeedback(answerIsCorrect)
    }

    /**
     * Displays a message based on a whether the answer is correct
     */
    private fun showAnswerFeedback(answerIsCorrect: Boolean) {
        val message = if (answerIsCorrect) {
            "Correct"
        } else {
            "Incorrect"
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}