package uz.abbosbek.crudtaks_4.api

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.abbosbek.crudtaks_4.utils.Constants

object RetrofitInstance {

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(headerInterceptor())
    }.build()

    private fun headerInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-type", "application/json; charset=UTF-8")
            .build()
        chain.proceed(request)
    }
}