package com.anganwadi.app.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import javax.annotation.processing.Generated

@Parcelize
class Question__1 : Parcelable {
    @SerializedName("questionImage")
    @Expose
    var questionImage: QuestionImage? = null

    @SerializedName("structure")
    @Expose
    var structure: Int = 0

    @SerializedName("questionText")
    @Expose
    var questionText: String? = null

    @SerializedName("questionSoundText")
    @Expose
    var questionSoundText: String? = null

    @SerializedName("questionSound")
    @Expose
    var questionSound: String? = null

    @SerializedName("questionType")
    @Expose
    var questionType: String? = null

    @SerializedName("answerImage")
    @Expose
    var answerImage: String? = null

    @SerializedName("totalOptions")
    @Expose
    var totalOptions: Int? = null

    @SerializedName("option")
    @Expose
    var option: Option? = null

    @SerializedName("correctAnswer")
    @Expose
    var correctAnswer: List<String> = listOf()
}