package com.anganwadi.app.ui.fragment

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentLanguageLiteracyTaskFirstBinding

class LanguageLiteracyTaskFirstFragment : Fragment() {
    private lateinit var mediaRecorder: MediaRecorder
    private lateinit var mediaPlayer: MediaPlayer
    private var audioFilePath: String? = null
    private var isRecording = false
    private var timerHandler: Handler = Handler()
    private var timerRunnable: Runnable? = null
    private var secondsElapsed = 0
    private var _binding: FragmentLanguageLiteracyTaskFirstBinding? = null
    private val binding get() = _binding!!
    private val listOptions = arrayListOf("L", "M", "M", "M")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentLanguageLiteracyTaskFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed(kotlinx.coroutines.Runnable {
            showInitRecord()
        }, 2000)
        showInitView()
        binding.btnRecord.setOnClickListener {
            if (!checkPermissions()) {
                requestPermissions()
            } else {
                showRecordingView()
                startRecording()
            }
        }
        binding.ivPlay.setOnClickListener {
            playAudio()
        }
        binding.ivPause.setOnClickListener {
            stopRecording()
            showSubmitView()
        }
        binding.tvRetry.setOnClickListener {
            showInitRecord()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun showRecordingView() {
        binding.rlRecording.visibility = View.VISIBLE
        binding.llInitRecord.visibility = View.GONE
        binding.rlSubmit.visibility = View.GONE
        binding.tvHint.visibility = View.GONE
    }

    private fun showInitRecord() {
        binding.llInitRecord.visibility = View.VISIBLE
        binding.rlRecording.visibility = View.GONE
        binding.rlSubmit.visibility = View.GONE
        binding.tvHint.visibility = View.GONE
    }

    private fun showSubmitView() {
        binding.rlRecording.visibility = View.GONE
        binding.llInitRecord.visibility = View.GONE
        binding.rlSubmit.visibility = View.VISIBLE
        binding.tvHint.visibility = View.GONE
    }

    private fun showInitView() {
        binding.llInitRecord.visibility = View.GONE
        binding.rlRecording.visibility = View.GONE
        binding.rlSubmit.visibility = View.GONE
        binding.tvHint.visibility = View.VISIBLE
    }

    private fun startRecording() {
        audioFilePath = "${requireActivity().externalCacheDir?.absolutePath}/recorded_audio.3gp"
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(audioFilePath)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            prepare()
            start()
        }
        isRecording = true
        startTimer()
    }

    private fun stopRecording() {
        mediaRecorder.apply {
            stop()
            release()
        }

        isRecording = false
        stopTimer()
    }

    private fun playAudio() {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(audioFilePath)
            prepare()
            start()
        }

        mediaPlayer.setOnCompletionListener {
//            ivSoundAnimation.visibility = View.GONE
//            btnPlay.visibility = View.VISIBLE
        }
    }

    private fun startTimer() {
        secondsElapsed = 0
        timerRunnable = object : Runnable {
            override fun run() {
                secondsElapsed++
                val minutes = secondsElapsed / 60
                val seconds = secondsElapsed % 60
                binding.tvTimer.text = String.format("%02d:%02d", minutes, seconds)
                timerHandler.postDelayed(this, 1000)
            }
        }
        timerHandler.postDelayed(timerRunnable!!, 1000)
    }

    private fun stopTimer() {
        timerHandler.removeCallbacks(timerRunnable!!)
    }

    private val RECORD_AUDIO_REQUEST_CODE = 1

    private fun checkPermissions(): Boolean {
        val permission = android.Manifest.permission.RECORD_AUDIO
        return requireActivity().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        requestPermissions(
            arrayOf(
                android.Manifest.permission.RECORD_AUDIO,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            RECORD_AUDIO_REQUEST_CODE
        )
    }

}