package com.taghavi.twoplayers

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMusicPlayer()
    }

    private fun setupMusicPlayer() {
        mediaPlayer = MediaPlayer()
        try {
            mediaPlayer.setDataSource(this, Uri.parse("http://www.hochmuth.com/mp3/Haydn_Cello_Concerto_D-1.mp3"))
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener {
                Log.i("media", "started")
                mediaPlayer.start()
            }
        } catch (e: Exception) {
            Log.i("media", "$e")
            e.printStackTrace()
        }
    }
}