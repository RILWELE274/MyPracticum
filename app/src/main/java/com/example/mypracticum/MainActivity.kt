// STUDENT NUMBER: 123456789
// FULL NAME: Rilwele Success Khangale

package com.example.mypracticum

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val songTitles = arrayListOf(
        "Remember",
        "Blessed the Lord",
        "Naho",
        "Don't Worry About a Thing"
    )

    private val artistNames = arrayListOf(
        "Lufuno Mudau",
        "Benjamin Dube",
        "Lucky Dube",
        "Taya Smith"
    )

    private val ratings = arrayListOf(1, 2, 4, 5) // fixed: all between 1–5
    private val comments = arrayListOf("Rap", "Gospel", "Reggae", "Gospel")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtSongList = findViewById<TextView>(R.id.txtSongList)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnDetails = findViewById<Button>(R.id.btnDetails)
        val btnExit = findViewById<Button>(R.id.btnExit)

        updateSongList(txtSongList)

        btnAdd.setOnClickListener {
            showInputDialog(txtSongList)
        }

        btnDetails.setOnClickListener {
            val intent = Intent(this, Detail::class.java)
            intent.putStringArrayListExtra("songs", songTitles)
            intent.putStringArrayListExtra("artists", artistNames)
            intent.putIntegerArrayListExtra("ratings", ratings)
            intent.putStringArrayListExtra("comments", comments)
            startActivity(intent)
        }

        btnExit.setOnClickListener {
            finishAffinity()
        }
    }

    private fun showInputDialog(displayText: TextView) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_input,null)
        val edtTitle = dialogView.findViewById<EditText>(R.id.edtTitle)
        val edtArtist = dialogView.findViewById<EditText>(R.id.edtArtist)
        val edtRating = dialogView.findViewById<EditText>(R.id.edtRating)
        val edtComment = dialogView.findViewById<EditText>(R.id.edtComment)

        AlertDialog.Builder(this)
            .setTitle("Add Song to Playlist")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val title = edtTitle.text.toString().trim()
                val artist = edtArtist.text.toString().trim()
                val ratingText = edtRating.text.toString().trim()
                val comment = edtComment.text.toString().trim()

                try {
                    val rating = ratingText.toInt()
                    if (title.isEmpty() || artist.isEmpty() || comment.isEmpty()) {
                        Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }
                    if (rating !in 1..5) {
                        Toast.makeText(this, "Rating must be between 1 and 5.", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }

                    songTitles.add(title)
                    artistNames.add(artist)
                    ratings.add(rating)
                    comments.add(comment)

                    Log.d("Playlist", "Added: $title by $artist ($rating) - $comment")
                    updateSongList(displayText)
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Please enter a valid number for rating.", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun updateSongList(textView: TextView) {
        val builder = StringBuilder("🎵 Playlist: ")
        for (i in songTitles.indices) {
            builder.append("${i + 1}. ${songTitles[i]} by ${artistNames[i]} (Rating: ${ratings[i]}) ")
        }
        textView.text = builder.toString()
    }
}

