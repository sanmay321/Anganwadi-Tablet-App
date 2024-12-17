package com.anganwadi.app.ui.fragment

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentMultipleOptionsBinding
import com.anganwadi.app.model.Question
import com.anganwadi.app.ui.CognitiveActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition

open class MultipleOptionsBaseFragment : BaseFragment() {
    private var _binding: FragmentMultipleOptionsBinding? = null
    val binding get() = _binding!!
    lateinit var question: Question
    var correctAnswer: String = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMultipleOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayList =
            arrayOf(binding.btnOne, binding.btnTwo, binding.btnThree, binding.btnFour)
        val arrayListIv =
            arrayOf(binding.ivBtnOne, binding.ivBtnTwo, binding.ivBtnThree, binding.ivBtnFour)
        val arrayListTag =
            arrayOf("o1", "o2", "o3", "o4")
        arrayList.forEachIndexed { index, view ->
            view.tag = arrayListTag[index]
            view.setOnClickListener {
                clearAllBackground()
                if (view.tag == correctAnswer) {
                    Toast.makeText(requireContext(), "correct", Toast.LENGTH_SHORT).show()
                }
                (requireActivity() as CognitiveActivity).setUserAnswerTheQuestion()
                arrayListIv[index].background =
                    ContextCompat.getDrawable(requireActivity(), R.drawable.background_8_orange)
            }
        }
    }

    private fun clearAllBackground() {
        val arrayList =
            arrayOf(binding.btnOne, binding.btnTwo, binding.btnThree, binding.btnFour)
        val arrayListIv =
            arrayOf(binding.ivBtnOne, binding.ivBtnTwo, binding.ivBtnThree, binding.ivBtnFour)
        arrayList.forEachIndexed { index, relativeLayout ->
            relativeLayout.background = null
            arrayListIv[index].background =null
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setThreeOptionView() {
        binding.btnFour.hideView()
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            _binding?.btnOne?.post {
                val plusWidth = binding.btnOne.width

                val minusParams = _binding?.btnThree?.layoutParams as LinearLayout.LayoutParams
                minusParams.width = plusWidth

                // Center horizontally
                val containerWidth = binding.bottomButtonsRow?.width ?: 0
                val horizontalMargin = (containerWidth - plusWidth) / 2
                minusParams.marginStart = horizontalMargin
                minusParams.marginEnd = horizontalMargin
                binding.btnThree.layoutParams = minusParams
            }
        }
    }

    fun setupPortraitLayout() {
        binding.apply {
            setThreeOptionView()
        }
    }

    fun setMarginVertical(value: Int, includeBottom: Boolean = true) {
        binding.cl.post {
            val screenWidth = Resources.getSystem().displayMetrics.widthPixels
            val layoutParams = binding.cl.layoutParams as? ViewGroup.MarginLayoutParams

            // Set height to screen width
            layoutParams?.height = screenWidth

            // Calculate vertical margin as 1/4 of the screen width
            val verticalMargin = screenWidth / value

            // Set top and bottom margins
            layoutParams?.topMargin = verticalMargin
            if (includeBottom) {
                layoutParams?.bottomMargin = verticalMargin
            }

            // Apply updated layout parameters
            binding.cl.layoutParams = layoutParams
        }
    }
    fun setUpForStructure1() {
        binding.frImageClue.visibility = View.VISIBLE
        binding.frQuestionImage.visibility = View.VISIBLE
    }
    fun setUpForStructure2() {
        binding.frImageClue.visibility = View.GONE
    }
    fun setUpForStructure3() {
        binding.frImageClue.visibility = View.GONE
        binding.frQuestionImage.visibility = View.GONE
        binding.tvTitleQuestion.visibility = View.VISIBLE
    }
    fun setUpForStructure4(isShowQuestionSound: Boolean) {
        binding.frQuestionImage.visibility = View.GONE
        binding.tvTitleQuestion.visibility = View.GONE
        binding.llQuestion.visibility = View.GONE
        binding.frImageClue.visibility = View.GONE
        if(isShowQuestionSound){
            binding.llSound.visibility = View.VISIBLE
        }else{
            binding.ivIconSound.visibility = View.VISIBLE
        }
    }

    fun setImageQuestion(image: String?) {
        Glide.with(requireActivity())
            .load(image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(binding.iv)
    }
    fun setImageClue(image: String?) {
        Glide.with(requireActivity())
            .load(image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(binding.ivImageClue)
    }

    fun setImage(iv: ImageView, image: String?) {
        Glide.with(this)
            .asBitmap()
            .load(image)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val imageWidth = resource.width
                    val imageHeight = resource.height

                    // Get the aspect ratio
                    val aspectRatio = imageHeight.toFloat() / imageWidth

                    // Set the ImageView's height based on the image's width and aspect ratio
                    val layoutParams = iv.layoutParams
                    layoutParams.height = (iv.width * aspectRatio).toInt()
                    iv.layoutParams = layoutParams

                    // Load the image into the ImageView
                    iv.setImageBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Handle cleanup if needed
                }
            })
        Glide.with(requireActivity())
            .load(image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(iv)
    }

    fun setUpOptionsView(totalOptions: Int?) {
        if (totalOptions == 2) {
            binding.btnFour.hideView()
            binding.btnThree.hideView()
        } else if (totalOptions == 3) {
            setThreeOptionView()
        }
    }

    fun setUpOptionsImage() {
        setImage(
            binding.ivBtnOne,
            question.question?.option?.o1
        )
        setImage(
            binding.ivBtnTwo,
            question.question?.option?.o2
        )
        setImage(
            binding.ivBtnThree,
            question.question?.option?.o3
        )
        setImage(
            binding.ivBtnFour,
            question.question?.option?.o4
        )

    }
}