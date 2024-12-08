package com.anganwadi.app.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.DraggableItemAdapter
import com.anganwadi.app.ImageItemAdapter
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentCognitiveTaskFifthBinding
import com.anganwadi.app.model.ImageModel
import com.anganwadi.app.model.Question
import com.anganwadi.app.ui.CognitiveActivity

class CognitiveTaskFifthFragment : BaseFragment() {
    private var _binding: FragmentCognitiveTaskFifthBinding? = null
    private val binding get() = _binding!!
    private var imageItems: ArrayList<ImageModel> = ArrayList()
    lateinit var question: Question

    companion object {
        const val TAG = "tag"
        fun newFragment(question: Question): CognitiveTaskFifthFragment {
            return CognitiveTaskFifthFragment().apply {
                val bundle = Bundle()
                bundle.putParcelable(TAG, question)
                arguments = bundle
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCognitiveTaskFifthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        question = arguments?.getParcelable(AestheticTaskFirstFragment.TAG) ?: Question()
        val isDemo = (question.quesCategory?.categoryName ?: "").contains("AAA")
        if(isDemo){
            binding.tvDemo.visibility=View.VISIBLE
        }else{
            binding.tvDemo.visibility=View.GONE
        }
        fun addToList(image: String?){
            if(!image.isNullOrEmpty()){
                imageItems.add(ImageModel(imageItems.size, imageUrl =image))
            }
        }
        binding.tvTitle.text=question.question?.questionText
        imageItems.clear()
        question.question?.option?.let {
            addToList(it.o1)
            addToList(it.o2)
            addToList(it.o3)
            addToList(it.o4)
            addToList(it.o5)
            addToList(it.o6)
            addToList(it.o7)
            addToList(it.o8)
            addToList(it.o9)
            addToList(it.o10)
        }


        val orientation = resources.configuration.orientation
        page1()

        binding.recyclerViewItems.adapter = ImageItemAdapter(
            requireContext(),
            imageItems,
            orientation == Configuration.ORIENTATION_PORTRAIT
        )

        binding.recyclerDropped1.adapter = DraggableItemAdapter(requireContext(),arrayListOf(), orientation == Configuration.ORIENTATION_PORTRAIT)
        binding.recyclerDropped2.adapter = DraggableItemAdapter(requireContext(),arrayListOf(), orientation == Configuration.ORIENTATION_PORTRAIT)


        setDropTarget(binding.rlDropped1, binding.recyclerDropped1)
        setDropTarget(binding.rlDropped2, binding.recyclerDropped2)
    }

    private fun setDropTarget(dropTarget: RelativeLayout, rvDropTarget: RecyclerView) {
        dropTarget.setOnDragListener { _, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> true

                DragEvent.ACTION_DROP -> {
                    dropTarget.setBackgroundResource(R.drawable.background_8_orange)
                    val id = event.clipData?.getItemAt(0)?.text.toString().toInt()
                    val adapter = rvDropTarget.adapter as DraggableItemAdapter
                    val index = imageItems.map { it.id }.indexOf(id)
                    adapter.addItem(imageItems[index])
                    val masterAdapter = binding.recyclerViewItems.adapter as ImageItemAdapter
                    masterAdapter.removeItem(imageItems[index])
                    (requireActivity() as CognitiveActivity).setUserAnswerTheQuestion()

                    true
                }


                else -> false
            }
        }
    }

    private fun page1() {
        var itemRow = (question.question?.totalOptions?:0)/2
        if(itemRow==0){
            itemRow=5
        }
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 3)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 3)

            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), 3)
        } else {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 5)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 5)
            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), itemRow)
        }
    }
    private fun page2() {
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 5)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 5)

            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), 3)
        } else {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 4)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 4)
            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), 5)
        }
    }
}