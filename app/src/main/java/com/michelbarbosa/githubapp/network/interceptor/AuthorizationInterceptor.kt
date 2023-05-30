package com.michelbarbosa.githubapp.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val apiKey: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .get()
            .addHeader(HeaderConstants.NAME_REQUEST_FILE, HeaderConstants.TYPE_REQUEST_FILE)
            .addHeader(HeaderConstants.NAME_TOKEN_FILE, HeaderConstants.concatToken(apiKey))
            .url(request.url)
            .build()

        return chain.proceed(newRequest)
    }

}