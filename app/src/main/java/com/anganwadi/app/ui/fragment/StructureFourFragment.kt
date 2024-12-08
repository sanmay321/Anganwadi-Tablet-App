package com.anganwadi.app.ui.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.anganwadi.app.R
import com.anganwadi.app.model.Question

class StructureFourFragment: MultipleOptionsBaseFragment() {
    private var tag = ""
    private var mediaPlayer: MediaPlayer? = null

    companion object {
        const val TAG = "tag"
        fun newFragment(question: Question): StructureFourFragment {
            return StructureFourFragment().apply {
                val bundle = Bundle()
                bundle.putParcelable(TAG, question)
                arguments = bundle
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        question = arguments?.getParcelable(AestheticTaskFirstFragment.TAG) ?: Question()
        question.question?.let {
            if (it.correctAnswer.isNotEmpty()) {
                correctAnswer = it.correctAnswer[0]
            }
        }
        binding.tvTitle.text = question.question?.questionText
        binding.tvSound.text = question.question?.questionSoundText
        setUpForStructure4(!question.question?.questionSoundText.isNullOrEmpty())
        val totalOptions=question.question?.totalOptions
        setUpOptionsView(totalOptions)
        setUpOptionsImage()
        binding.ivSound.setOnClickListener {
            playFromRaw(binding.ivSound, question.question?.questionSound?:"")
        }
        binding.ivIconSound.setOnClickListener {
            playFromRaw(binding.ivIconSound,question.question?.questionSound?:"")
        }
        val isDemo = (question.quesCategory?.categoryName ?: "").contains("AAA")
        if(isDemo){
            binding.tvDemo.visibility=View.VISIBLE
        }else{
            binding.tvDemo.visibility=View.GONE
        }
    }
    private fun playFromRaw(imageView: ImageView, music: String) {
        imageView.setImageResource(R.drawable.ic_sound_on)
        try {
            mediaPlayer = MediaPlayer().apply {
                try {
                    setDataSource(music)
                    setOnPreparedListener {
                        start()  // Start playing once the media is prepared
                    }
                    setOnErrorListener { mp, what, extra ->
                        true
                    }

                    setOnCompletionListener {
                        imageView.setImageResource(R.drawable.ic_sound)
                    }
                    prepareAsync()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
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