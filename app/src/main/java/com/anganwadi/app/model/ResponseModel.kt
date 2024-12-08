package com.anganwadi.app.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ResponseModel {
    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("questions")
    @Expose
    private var questions: List<Question> = listOf()

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getQuestions(): List<Question> {
        return questions
    }

    fun setQuestions(questions: List<Question>) {
        this.questions = questions
    }
}