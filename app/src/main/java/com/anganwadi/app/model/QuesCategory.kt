package com.anganwadi.app.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import javax.annotation.processing.Generated


@Parcelize
class QuesCategory : Parcelable {
    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("categoryName")
    @Expose
    var categoryName: String? = null
}