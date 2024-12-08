package com.anganwadi.app.network

import com.anganwadi.app.model.ResponseModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("api/v1/assessment/agewise")
    fun getQuestion(@Body body: RequestBody): Call<ResponseModel>
}