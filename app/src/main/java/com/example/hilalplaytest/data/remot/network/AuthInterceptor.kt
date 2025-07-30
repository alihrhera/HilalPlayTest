package com.example.hilalplaytest.data.remot.network

import com.example.hilalplaytest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

/**
 * Adds the API token to the query parameters of the request.
 *
 * @param chain the request chain
 * @return the response
 */
class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("X-Master-Key", BuildConfig.API_TOKEN)
            .build()

        return chain.proceed(request)
    }
}