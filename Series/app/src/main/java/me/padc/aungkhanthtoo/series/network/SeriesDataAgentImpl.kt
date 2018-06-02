package me.padc.aungkhanthtoo.series.network

import android.util.Log.d
import android.util.Log.e
import com.google.gson.Gson
import me.padc.aungkhanthtoo.series.data.vo.BaseVO
import me.padc.aungkhanthtoo.series.data.vo.CurrentProgramVO
import me.padc.aungkhanthtoo.series.data.vo.TitleVO
import me.padc.aungkhanthtoo.series.events.ApiEvents
import me.padc.aungkhanthtoo.series.utils.SERVER_CAN_T_CONNECT
import me.padc.aungkhanthtoo.series.utils.SERVER_ERROR_RESPONSE
import me.padc.aungkhanthtoo.series.utils.SERVER_NO_DATA
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.doAsync
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object SeriesDataAgentImpl : SeriesDataAgent {

    private val mAPI by lazy {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

        Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-5/simple-habits/")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()
                .create(SeriesAPI::class.java)
    }

    override fun loadSeriesData(accessToken: String, pageNo: Int) {

        val callTopics = mAPI.loadTopics(pageNo, accessToken)
        val callCategory = mAPI.loadCategories(pageNo, accessToken)
        val callCurrentProgram = mAPI.loadCurrentProgram(pageNo, accessToken)

        doAsync {
            try {
                val responseProgram = callCurrentProgram.execute()
                if (responseProgram.isSuccessful) {
                    val responseCategory = callCategory.execute()
                    if (responseCategory.isSuccessful) {
                        val responseTopics = callTopics.execute()
                        if (responseTopics.isSuccessful) {
                            val programResponse = responseProgram.body()
                            val categoryResponse = responseCategory.body()
                            val topicResponse = responseTopics.body()
                            if (programResponse?.currentProgram != null &&
                                    categoryResponse?.categoriesPrograms?.isNotEmpty() == true &&
                                    topicResponse?.topics?.isNotEmpty() == true) {
                                d("SeriesDataAgentImpl", "currentProgram ${programResponse.currentProgram}\ncategory ${categoryResponse.categoriesPrograms}\ntopics ${topicResponse.topics}")
                                EventBus.getDefault().post(
                                        ApiEvents.SuccessfulDataLoaded(
                                                categoryResponse.page?.toInt() ?: 1,
                                                getAllData(listOf(TitleVO("Start Here")),
                                                        listOf(programResponse.currentProgram),
                                                        categoryResponse.categoriesPrograms,
                                                        listOf(TitleVO("All Topics")),
                                                        topicResponse.topics)))
                            } else {
                                EventBus.getDefault().post(ApiEvents.ErrorInvokingAPI("No data could be loaded for Now. Please try again later.", SERVER_NO_DATA))
                            }
                        } else {
                            e("SeriesDataAgentImpl", "Topics API unsuccessful : ${responseTopics.errorBody().toString()}")
                            EventBus.getDefault().post(ApiEvents.ErrorInvokingAPI("Server fails.", SERVER_ERROR_RESPONSE))
                        }
                    } else {
                        e("SeriesDataAgentImpl", "Category API unsuccessful : ${responseCategory.errorBody().toString()}")
                        EventBus.getDefault().post(ApiEvents.ErrorInvokingAPI("Server fails.", SERVER_ERROR_RESPONSE))
                    }
                } else {
                    e("SeriesDataAgentImpl", "Program API unsuccessful : ${responseProgram.errorBody().toString()}")
                    EventBus.getDefault().post(ApiEvents.ErrorInvokingAPI("Server fails.", SERVER_ERROR_RESPONSE))
                }
            } catch (ioe: IOException) {
                e("SeriesDataAgentImpl", "Can't connect to Server! $ioe")
                EventBus.getDefault().postSticky(ApiEvents.ErrorInvokingAPI("Can't connect to Server", SERVER_CAN_T_CONNECT))
            }
        }
    }

    private fun getAllData(vararg arg: List<BaseVO>): List<BaseVO> {
        val mutable = ArrayList<BaseVO>()
        arg
                .asSequence()
                .forEach {
                    it.forEach { mutable.add(it) }
                }
        return mutable.toList()
    }
}