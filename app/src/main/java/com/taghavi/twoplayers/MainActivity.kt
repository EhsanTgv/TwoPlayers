package com.taghavi.twoplayers

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.taghavi.twoplayers.databinding.ActivityMainBinding
import kotlin.math.ln

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val MAX_VOLUME = 100
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMusicPlayer()

        binding.mainSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mediaPlayer.setVolume(setVolume(progress), setVolume(progress))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
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

    private fun setVolume(volume: Int): Float {
        return (1 - (ln(MAX_VOLUME.toDouble() - volume) / ln(MAX_VOLUME.toDouble()))).toFloat()
    }
}