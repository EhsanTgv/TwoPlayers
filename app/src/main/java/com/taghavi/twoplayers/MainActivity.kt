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
    lateinit var mediaPlayer1: MediaPlayer
    lateinit var mediaPlayer2: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMusicPlayer1()
        setupMusicPlayer2()
    }

    private fun setupMusicPlayer1() {
        mediaPlayer1 = MediaPlayer()
        with(mediaPlayer1) {
            try {
                setDataSource(this@MainActivity, Uri.parse("https://file-examples-com.github.io/uploads/2017/11/file_example_MP3_700KB.mp3"))
                prepareAsync()
                isLooping = true
                setOnPreparedListener {
                    start()

                    binding.mainSeekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                            setVolume(setMediaVolume(progress), setMediaVolume(progress))
                        }

                        override fun onStartTrackingTouch(seekBar: SeekBar?) {
                        }

                        override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        }

                    })
                }
            } catch (e: Exception) {
                Log.i("media", "$e")
                e.printStackTrace()
            }
        }
    }

    private fun setupMusicPlayer2() {
        mediaPlayer2 = MediaPlayer()
        with(mediaPlayer2) {
            try {
                setDataSource(this@MainActivity, Uri.parse("https://filesamples.com/samples/audio/mp3/sample1.mp3"))
                prepareAsync()
                isLooping = true
                setOnPreparedListener {
                    start()

                    binding.mainSeekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                            setVolume(setMediaVolume(progress), setMediaVolume(progress))
                        }

                        override fun onStartTrackingTouch(seekBar: SeekBar?) {
                        }

                        override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        }

                    })
                }
            } catch (e: Exception) {
                Log.i("media", "$e")
                e.printStackTrace()
            }
        }
    }

    private fun setMediaVolume(volume: Int): Float {
        return (1 - (ln(MAX_VOLUME.toDouble() - volume) / ln(MAX_VOLUME.toDouble()))).toFloat()
    }
}