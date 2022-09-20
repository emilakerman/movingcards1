package com.example.movingcards
import android.animation.ObjectAnimator
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var button1 : Button
    lateinit var textView2 : TextView
    var points = 0

    lateinit var imageView2 : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        textView2 = findViewById(R.id.textView2)

        imageView2 = findViewById(R.id.imageView2)
        imageView2.setOnClickListener {
            vibrateKing()
        }

        ////INITIAL ANIMATION BUTTON
        val path = Path().apply {
            arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true)
        }
        ObjectAnimator.ofFloat(button1, View.X, View.Y, path).apply {
        duration = 2000
        start()
        }

        ////BUTTON1 CLICK
        button1.setOnClickListener {
            vibrate()
            points++
            val points2 = findViewById<TextView>(R.id.points)
            points2.text = points.toString()
            if (points >= 10) {
                textView2.text = "DAMN BRO YOU PASSED 10 POINTS"
                //val intent = Intent(this, YouWinScreen::class.java)
                //startActivity(intent)
            }
        }
        /*
                ObjectAnimator.ofFloat(button1, "translationY", 1500f).apply { //f = pixlar
                    duration = 2000 // betyder 2 sekunder
                    start()
            } */
    }
    /*
    fun buttonPress() {
        val path = Path().apply {
            arcTo(0f, 0f, 1000f, 1000f, 0f, -180f, true)
        }
            ObjectAnimator.ofFloat(button1, View.X, View.Y, path).apply {
            duration = 2000
            start()
        }
    }*/
    fun randomPosition() = Random.nextInt(-500, 500).toFloat()
    fun vibrate() {
        button1.animate()
            .translationX(randomPosition())
            .translationY(randomPosition())
            .setDuration(500)
            .withEndAction(::vibrate) //IF YOU REMOVE THIS IT WILL ONLY MOVE ONCE PER CLICK
            .start()
    }
    fun vibrateKing() {
        imageView2.animate()
            .translationX(randomPosition())
            .translationY(randomPosition())
            .setDuration(500)
            .withEndAction(::vibrateKing) //IF YOU REMOVE THIS IT WILL ONLY MOVE ONCE PER CLICK
            .start()
    }
}