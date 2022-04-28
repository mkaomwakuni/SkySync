package com.mkao.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var counter: Int = 0

    //called when the Activity is Created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Find the button in the layout
        val rollDice:Button = findViewById(R.id.Button)

        rollDice.setOnClickListener {
            rollDice()

        }
        //Roll dice on start of rhe app
        rollDice()

    }
    private fun rollDice(){
        //create a new Dice Object with 6 sides on it
        val dice = Dice(6)
        val diceRoll = dice.roll()
        //find the imageview in the layout
        val diceImage: ImageView = findViewById(R.id.imageV)
        //Determine which drawable resource ID to use based on
        //dice roll

        val drawableResource = when(diceRoll){
            1->R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }
        //update the ImageView with the correct resource
        diceImage.setImageResource(drawableResource)
        //update the content description
        diceImage.contentDescription= diceRoll.toString()

         }
}
//Dice with a fixed number of sides
class Dice(private val numSides: Int) {
    fun roll(): Int {

        return (1..numSides).random()
//Do a random roll and return the results
    }

}

