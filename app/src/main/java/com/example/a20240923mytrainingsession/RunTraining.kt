package com.example.a20240923mytrainingsession

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RunTraining : AppCompatActivity() {
    private lateinit var toolbarTrainingTB: Toolbar
    private lateinit var runExerciseBTN: Button
    private lateinit var exerciseTitleTV: TextView
    private lateinit var exerciseDescriptionTV: TextView
    private lateinit var timerTV: TextView
    private lateinit var selectNextExerciseBTN: Button
    private lateinit var exerciseImageIV: ImageView
    private lateinit var exercise: Exercise
    private lateinit var timer: CountDownTimer
//    private var exerciseIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_run_training)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initVariables()
        setSupportActionBar(toolbarTrainingTB)
        exercise = intent.extras?.getSerializable(Exercise::class.java.simpleName) as Exercise
//        exerciseIndex = intent.extras!!.getInt("exerciseIndex")
        exerciseTitleTV.text = exercise.name
        exerciseDescriptionTV.text = exercise.description
        runExerciseBTN.setOnClickListener {
            beginTraining()
        }
        selectNextExerciseBTN.setOnClickListener {
            val intent = Intent(this, ExerciseList::class.java)
//            intent.putExtra("exerciseIndex", exerciseIndex)
            finish()
            startActivity(intent)
        }
    }

    private fun beginTraining() {
        exerciseImageIV.setImageResource(exercise.gifPict)
        runExerciseBTN.isEnabled = false
        runExerciseBTN.text = "Тренировка..."
        timerTV.text = formatTime(exercise.durationInSec)
        timer = object: CountDownTimer(exercise.durationInSec *1000L, 1000){
            override fun onTick(millisUntilFinished: Long) {
                timerTV.text = formatTime((millisUntilFinished / 1000).toInt())
            }
            override fun onFinish() {
                runExerciseBTN.isEnabled = true
                runExerciseBTN.text = "Повторить упражнение"
                selectNextExerciseBTN.isEnabled = true
                timerTV.text = "Упражнение завершено"
                exerciseImageIV.setImageResource(0)
            }
        }.start()
    }

    private fun formatTime(duration: Int): String {
        val remainMin = duration / 60
        val remainSec = duration % 60
        return String.format("%02d:%02d", remainMin, remainSec)
    }

    private fun initVariables() {
        toolbarTrainingTB = findViewById(R.id.toolbarTrainingTB)
        runExerciseBTN = findViewById(R.id.runExerciseBTN)
        exerciseTitleTV = findViewById(R.id.exerciseTitleTV)
        exerciseDescriptionTV = findViewById(R.id.exerciseDescriptionTV)
        timerTV = findViewById(R.id.timerTV)
        selectNextExerciseBTN = findViewById(R.id.selectNextExerciseBTN)
        exerciseImageIV = findViewById(R.id.exerciseImageIV)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.training_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}