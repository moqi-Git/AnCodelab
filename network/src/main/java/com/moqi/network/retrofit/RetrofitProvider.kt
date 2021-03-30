package com.moqi.network.retrofit

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

object RetrofitProvider {

    private const val BASE_URL = "http://wisdom.ekwing.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
//        .addCallAdapterFactory()
//        .addConverterFactory()
        .build()
}

class XCallAdapterFactory: CallAdapter.Factory(){

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

    }

}

class XCallAdapter<T, R>: CallAdapter<T, R> {
    override fun responseType(): Type {

    }

    override fun adapt(call: Call<T>): R {

    }

}

class XConverterFactory: Converter.Factory(){

}

class XConverter<F, T>: Converter<F, T>{
    override fun convert(value: F): T? {

    }
}