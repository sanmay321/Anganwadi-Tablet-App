package com.anganwadi.app.ui.fragment

import android.content.res.Configuration
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentLangguageLiteracyTaskSecondAudioBinding

class LanguageLiteracyTaskSecondAudioFragment : MultipleOptionsBaseFragment() {
    private val listOptions = arrayListOf("L", "M", "M", "M")
    private var mediaPlayer: MediaPlayer? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frQuestionImage.visibility = View.GONE
        binding.tvTitleQuestion.visibility = View.GONE
        binding.llQuestion.visibility = View.GONE
        binding.frImageClue.visibility = View.GONE
        binding.ivIconSound.visibility = View.VISIBLE
        binding.tvTitle.text="Choose beginning letter sound"
        binding.ivIconSound.setOnClickListener {
            playFromRaw()
        }
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setMarginVertical(6)
        } else {
            setMarginVertical(10)
        }
    }

    private fun playFromRaw() {
        binding.ivIconSound.setImageResource(R.drawable.ic_sound_on)
        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(context, R.raw.sound)
            mediaPlayer?.start()
            mediaPlayer?.setOnCompletionListener {
                binding.ivIconSound.setImageResource(R.drawable.ic_sound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}