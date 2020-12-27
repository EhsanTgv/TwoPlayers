package com.taghavi.twoplayers

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ln

class MainActivity : AppCompatActivity() {
    private val MAX_VOLUME = 100
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMusicPlayer()
    }

    private fun setupMusicPlayer() {
        mediaPlayer = MediaPlayer()
        try {
            mediaPlayer.setDataSource(this, Uri.parse("https://file-examples-com.github.io/uploads/2017/11/file_example_MP3_700KB.mp3"))
            mediaPlayer.prepareAsync()
            mediaPlayer.setVolume(setVolume(20), setVolume(20))
            mediaPlayer.setOnPreparedListener {
                Log.i("media", "started")
                mediaPlayer.start()
            }
        } catch (e: Exception) {
            Log.i("media", "$e")
            e.printStackTrace()
        }
    }

    private fun setVolume(volume:Int): Float {
        return (1 - (ln(MAX_VOLUME.toDouble() - volume) / ln(MAX_VOLUME.toDouble()))).toFloat()
    }
}