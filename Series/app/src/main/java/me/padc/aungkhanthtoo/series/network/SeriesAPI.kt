package me.padc.aungkhanthtoo.series.network

import me.padc.aungkhanthtoo.series.network.responses.GetCategoriesProgramsResponse
import me.padc.aungkhanthtoo.series.network.responses.GetCurrentProgramResponse
import me.padc.aungkhanthtoo.series.network.responses.GetTopicsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SeriesAPI {

    @FormUrlEncoded
    @POST("getTopics.php")
    fun loadTopics(
            @Field("page") page: Int,
            @Field("access_token") accessToken: String
    ): Call<GetTopicsResponse>

    @FormUrlEncoded
    @POST("getCurrentProgram.php")
    fun loadCurrentProgram(
            @Field("page") page: Int,
            @Field("access_token") accessToken: String
    ): Call<GetCurrentProgramResponse>

    @FormUrlEncoded
    @POST("getCategoriesPrograms.php")
    fun loadCategories(
            @Field("page") page: Int,
            @Field("access_token") accessToken: String
    ): Call<GetCategoriesProgramsResponse>

}