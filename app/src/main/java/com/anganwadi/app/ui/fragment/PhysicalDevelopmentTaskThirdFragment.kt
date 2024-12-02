package com.anganwadi.app.ui.fragment

import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentPhyisicalDevelopmentTaskFirst1Binding
import com.anganwadi.app.databinding.FragmentPhyisicalDevelopmentTaskThirdBinding

class PhysicalDevelopmentTaskThirdFragment: MultipleOptionsBaseFragment() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frQuestionImage.visibility = View.GONE
        binding.tvTitleQuestion.visibility = View.GONE
        binding.llQuestion.visibility = View.GONE
        binding.frImageClue.visibility = View.GONE
        binding.llSound.visibility = View.VISIBLE
        binding.tvTitle.text="Which animal makes this sound?"
        binding.ivIconSound.setOnClickListener {
            playFromRaw()
        }
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setMarginVertical(6)
        } else {
            setMarginVertical(12)
        }
    }
    private fun playFromRaw() {
        binding.ivSound.setImageResource(R.drawable.ic_sound_on)
        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(context, R.raw.sound)
            mediaPlayer?.start()
            mediaPlayer?.setOnCompletionListener {
                binding.ivSound.setImageResource(R.drawable.ic_sound)
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