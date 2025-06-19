package com.example.mypracticum

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mypracticum.R

class Review : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val txtReview = findViewById<TextView>(R.id.txtReview)
        val btnClose = findViewById<Button>(R.id.btnClose)

        // Get playlist data from intent
        val songs = intent.getStringArrayListExtra("songs") ?: arrayListOf()
        val artists = intent.getStringArrayListExtra("artists") ?: arrayListOf()
        val ratings = intent.getIntegerArrayListExtra("ratings") ?: arrayListOf()
        val comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()

        // Build review summary
        val summary = StringBuilder("🎶 Full Playlist Review:\n\n")
        for (i in songs.indices) {
            summary.append("${i + 1}. ${songs[i]} by ${artists[i]}\n")
            summary.append(" ⭐ Rating: ${ratings[i]}/5\n")
            summary.append(" 💬 Comment: ${comments[i]}\n\n")
        }

        txtReview.text = summary.toString()

        // Close and return
        btnClose.setOnClickListener {
            finish()
        }
    }
}