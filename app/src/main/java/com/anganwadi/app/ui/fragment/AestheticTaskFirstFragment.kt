package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.ActivityAestheticTaskFirstBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AestheticTaskFirstFragment : Fragment() {
    private var _binding: ActivityAestheticTaskFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityAestheticTaskFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showHint(2)
    }
    private fun setView(){
        val arrayList =
            arrayOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
        arrayList.forEach { view ->
            view.setImageResource(R.drawable.ic_egg_place_holder)
        }
        arrayList.forEach { view ->
            view.setOnClickListener {
                clearAllBackground()
                view.setImageResource(R.drawable.ic_egg_selected)
            }
        }
    }

    private fun clearAllBackground() {
        val arrayList =
            arrayOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
        arrayList.forEach { view ->
            view.setImageResource(R.drawable.ic_egg_place_holder)
        }
    }
    private fun showHint(indexSelected: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val arrayList =
                arrayOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
            arrayList.forEachIndexed { index, imageView ->
                if(index==indexSelected){
                    imageView.setImageResource(R.drawable.ic_egg_position)
                }else{
                    imageView.setImageResource(0)
                }
            }
            delay(1500)
            setView()
        }
    }
}