package com.anganwadi.app.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import javax.annotation.processing.Generated

@Parcelize
class Question : Parcelable {
    @SerializedName("question")
    @Expose
    var question: Question__1? = null

    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("ageGroup")
    @Expose
    var ageGroup: String? = null

    @SerializedName("quesCategory")
    @Expose
    var quesCategory: QuesCategory? = null

    @SerializedName("__v")
    @Expose
    var v: Int? = null
}