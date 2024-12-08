package com.anganwadi.app.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anganwadi.app.databinding.ActivityCognitiveBinding
import com.anganwadi.app.model.Question
import com.anganwadi.app.model.ResponseModel
import com.anganwadi.app.network.RetrofitClient
import com.anganwadi.app.ui.fragment.AestheticTaskFirstFragment
import com.anganwadi.app.ui.fragment.AestheticTaskSecondFragment
import com.anganwadi.app.ui.fragment.CognitiveTaskFifthFragment
import com.anganwadi.app.ui.fragment.CognitiveTaskForthFragment
import com.anganwadi.app.ui.fragment.CognitiveTaskSecondFragment
import com.anganwadi.app.ui.fragment.CognitiveTaskThirdFragment
import com.anganwadi.app.ui.fragment.CognitivieTaskFirstFragment
import com.anganwadi.app.ui.fragment.LanguageLiteracyTaskFirstFragment
import com.anganwadi.app.ui.fragment.LanguageLiteracyTaskSecondAudioFragment
import com.anganwadi.app.ui.fragment.LanguageLiteracyTaskSecondIdentifyObjectFragment
import com.anganwadi.app.ui.fragment.LanguageLiteracyTaskSecondOddFragment
import com.anganwadi.app.ui.fragment.LanguageLiteracyTaskSecondVisualFragment
import com.anganwadi.app.ui.fragment.LanguageLiteracyTaskThirdLetterFragment
import com.anganwadi.app.ui.fragment.LanguageLiteracyTaskThirdLetterImageFragment
import com.anganwadi.app.ui.fragment.PhysicalDevelopmentTaskFirst1Fragment
import com.anganwadi.app.ui.fragment.PhysicalDevelopmentTaskFirst2Fragment
import com.anganwadi.app.ui.fragment.PhysicalDevelopmentTaskFourthFragment
import com.anganwadi.app.ui.fragment.PhysicalDevelopmentTaskSecondFragment
import com.anganwadi.app.ui.fragment.PhysicalDevelopmentTaskThirdFragment
import com.anganwadi.app.ui.fragment.StructureFourFragment
import com.anganwadi.app.ui.fragment.StructureOneFragment
import com.anganwadi.app.ui.fragment.StructureThreeFragment
import com.anganwadi.app.ui.fragment.StructureTwoFragment
import com.anganwadi.app.util.Constant.Companion.COGNITIVE_DOMAIN_TASK_1
import com.anganwadi.app.util.Constant.Companion.COGNITIVE_DOMAIN_TASK_3
import com.anganwadi.app.util.SessionManager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CognitiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCognitiveBinding
    private var position = 20
    private lateinit var questionsResponse: ResponseModel
    private var isUserAnsweredTheQuestion = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCognitiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNext.setOnClickListener {
            if (questionsResponse.getQuestions().isNotEmpty()) {
                if (!isUserAnsweredTheQuestion) {
                    Toast.makeText(this, "You have answer the question", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                position++
                if (position < questionsResponse.getQuestions().size) {
                    questionsResponse.getQuestions()[position].let {
                        showFragmentByType(it.question?.structure, it)
                    }
                }
            }
        }
        fetchQuestion()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        position--
        Log.d("onBackPressed ", "--> ${supportFragmentManager.backStackEntryCount}")

        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack() // Go to the previous fragment
        } else {
            super.onBackPressed() // Exit the activity
            finish()
        }
    }

    private fun fetchQuestion() {
        binding.loadingIndicator.visibility= View.VISIBLE
        val jsonObject = JSONObject()
        jsonObject.put("age", SessionManager.getAge(this))
        val jsonObjectString = jsonObject.toString()

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val call = RetrofitClient.instance.getQuestion(requestBody)
        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {
                    questionsResponse = response.body() ?: ResponseModel()
                    val positionIndex =questionsResponse.getQuestions().map { it.question?.structure }.indexOf(7)
//                    val positionIndex =0
                    val question = questionsResponse.getQuestions()[positionIndex]
                    question.let {
                        showFragmentByType(it.question?.structure, it)
                    }
                    position=positionIndex
                    binding.loadingIndicator.visibility= View.GONE
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
            }
        })
    }

    private fun showFragmentByType(structure: Int?, question: Question) {
        Log.d("showFragmentByType ", "--> ${structure}")
        isUserAnsweredTheQuestion = false
        when (structure) {
            1->{
                replaceFragment(StructureOneFragment.newFragment(question))
            }
            2 -> {
                replaceFragment(StructureTwoFragment.newFragment(question))
            }
            3 -> {
                replaceFragment(StructureThreeFragment.newFragment(question))
            }
            4 -> {
                replaceFragment(StructureFourFragment.newFragment(question))
            }
            5 -> {
                replaceFragment(CognitiveTaskFifthFragment.newFragment(question))
            }
            6 -> {
                replaceFragment(AestheticTaskFirstFragment.newFragment(question))
            }
            7 -> {
                replaceFragment(PhysicalDevelopmentTaskSecondFragment.newFragment(question))
            }
            8 -> {
                replaceFragment(PhysicalDevelopmentTaskFourthFragment.newFragment(question))
            }

        }
    }

    fun setUserAnswerTheQuestion() {
        isUserAnsweredTheQuestion = true
    }
}
