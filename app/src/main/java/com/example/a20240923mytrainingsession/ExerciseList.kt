package com.example.a20240923mytrainingsession

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ExerciseList : AppCompatActivity() {
    private lateinit var toolbarExerciseListTB: Toolbar
    private lateinit var exerciseListLV: ListView
    private lateinit var listAdapter: ExListAdapder
    val exerciseList = ExerciseBase.exercises

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exercise_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initVariables()
        setSupportActionBar(toolbarExerciseListTB)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, exerciseList)
        exerciseListLV.adapter = listAdapter
        exerciseListLV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                val exercise = adapter.getItem(position)
                val intent = Intent(this, RunTraining::class.java)
                intent.putExtra(Exercise::class.java.simpleName, exercise)
                finish()
                startActivity(intent)
            }
    }
    private fun initVariables() {
        toolbarExerciseListTB = findViewById(R.id.toolbarExerciseListTB)
        exerciseListLV = findViewById(R.id.exerciseListLV)
        listAdapter = ExListAdapder(this, exerciseList)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.exercise_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}