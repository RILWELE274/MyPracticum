// STUDENT NUMBER: 123456789
// FULL NAME: Rilwele Success Khangale

package com.example.mypracticum

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mypracticum.R

class Detail : AppCompatActivity() {

    private lateinit var txtDetails: TextView
    private lateinit var songs: ArrayList<String>
    private lateinit var artists: ArrayList<String>
    private lateinit var ratings: ArrayList<Int>
    private lateinit var comments: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Get views
        txtDetails = findViewById(R.id.txtDetails)
        val btnShowAll = findViewById<Button>(R.id.btnShowAll)
        val btnAverage = findViewById<Button>(R.id.btnAverage)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Receive data from MainActivity
        songs = intent.getStringArrayListExtra("songs") ?: arrayListOf()
        artists = intent.getStringArrayListExtra("artists") ?: arrayListOf()
        ratings = intent.getIntegerArrayListExtra("ratings") ?: arrayListOf()
        comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()

        // Show all song details
        btnShowAll.setOnClickListener {
            showAllSongs()
        }

        // Calculate and show average rating
        btnAverage.setOnClickListener {
            showAverageRating()
        }

        // Go back to main screen
        btnBack.setOnClickListener {
            finish()
        }
    }

    // Function to display full playlist
    private fun showAllSongs() {
        if (songs.isEmpty()) {
            txtDetails.text = "No songs in playlist."
            return
        }

        val details = StringBuilder()
        for (i in songs.indices) {
            details.append("${songs[i]} by ${artists[i]} - Rating: ${ratings[i]}, Comment: ${comments[i]} ")
        }

        txtDetails.text = details.toString()
    }

    // Function to calculate and display average rating
    private fun showAverageRating() {
        if (ratings.isEmpty()) {
            txtDetails.text = "No ratings to calculate average."
            return
        }

        var total = 0
        for (rating in ratings) {
            total += rating
        }

        val average = total.toDouble() / ratings.size
        txtDetails.text = "Average Rating: %.2f".format(average)
    }
}
