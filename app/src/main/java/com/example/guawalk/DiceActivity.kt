package com.example.guawalk

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows user to roll a dice and
 * view the results
 */
class DiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }
        //to start activity with a random dice image
        rollDice()
    }

    private fun rollDice() {
        /**
         * Create new dice object with 6 sides and do
         *the dice roll
         */
        val dice = Dice(6)
        val diceRollL = dice.roll()
        val diceRollR = dice.roll()
        //find the imageView in the Layout
        val diceImageL: ImageView = findViewById(R.id.iVFirstDice)
        val diceImageR: ImageView = findViewById(R.id.iVSecondDice)
        //determine a drawable result upon roll
        val drawableResourceL = when (diceRollL) {
            1-> R.drawable.dice_1
            2-> R.drawable.dice_2
            3-> R.drawable.dice_3
            4-> R.drawable.dice_4
            5-> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val drawableResourceR = when (diceRollR) {
            1-> R.drawable.dice_1
            2-> R.drawable.dice_2
            3-> R.drawable.dice_3
            4-> R.drawable.dice_4
            5-> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        //Update the imageView with the correct resource ID
        diceImageL.setImageResource(drawableResourceL)
        diceImageR.setImageResource(drawableResourceR)
        // Update the content description
        diceImageL.contentDescription = diceRollL.toString()
        diceImageR.contentDescription = diceRollR.toString()

        val sumDice : TextView = findViewById(R.id.sumDice)
        val sumResult :Int = diceRollL + diceRollR
        sumDice.text = sumResult.toString()


        /** //Commented for example with labels (Text View)
        resultFirstDice.text = dice.roll().toString()
        // No need the "diceRoll" since is refactored in the textView
         */
        /** //commented for exercise with 1 dice
        val resultSecondDice: TextView = findViewById(R.id.second_dice)
        resultSecondDice.text = dice.roll().toString()
         */
        /**
         * Notice refactoring by using the roll function directly on
         * the TextView.Text declaration
         */


    }
}

class Dice(private val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}