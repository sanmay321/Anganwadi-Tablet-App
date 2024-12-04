package com.anganwadi.app.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.annotation.processing.Generated


class QuestionImage {
    @SerializedName("after")
    @Expose
    var after: String? = null

    @SerializedName("before")
    @Expose
    var before: String? = null
}