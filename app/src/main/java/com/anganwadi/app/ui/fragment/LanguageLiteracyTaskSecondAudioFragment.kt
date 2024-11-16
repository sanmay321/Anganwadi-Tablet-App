package com.anganwadi.app.ui.fragment

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

class LanguageLiteracyTaskSecondAudioFragment : Fragment() {
    private var _binding: FragmentLangguageLiteracyTaskSecondAudioBinding? = null
    private val binding get() = _binding!!
    private val listOptions = arrayListOf("L", "M", "M", "M")
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentLangguageLiteracyTaskSecondAudioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayList =
            arrayOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
        val arrayListOptionsId =
            arrayOf(binding.tvTitle1, binding.tvTitle2, binding.tvTitle3, binding.tvTitle4)

        arrayList.forEach { view ->
            view.setOnClickListener {
                clearAllBackground()
                view.background =
                    ContextCompat.getDrawable(requireActivity(), R.drawable.background_8_orange)
            }
        }
        val options = listOptions
        arrayListOptionsId.forEachIndexed { index, textView ->
            textView.text = options[index]
        }
        binding.ivIconSound.setOnClickListener {
            playFromRaw()
        }
    }

    private fun clearAllBackground() {
        val arrayList =
            arrayOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
        arrayList.forEach {
            it.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_8_white)
        }
    }

    fun playFromRaw() {
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