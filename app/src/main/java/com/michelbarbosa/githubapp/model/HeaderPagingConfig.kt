package com.michelbarbosa.githubapp.model

import android.net.ParseException
import androidx.paging.PagingConfig
import com.michelbarbosa.githubapp.BuildConfig
import com.michelbarbosa.githubapp.model.HeaderParam.HEADER_PARAM_FIRST
import com.michelbarbosa.githubapp.model.HeaderParam.HEADER_PARAM_LAST
import com.michelbarbosa.githubapp.model.HeaderParam.HEADER_PARAM_NEXT
import com.michelbarbosa.githubapp.model.HeaderParam.HEADER_PARAM_PREV
import com.michelbarbosa.githubapp.model.HeaderParam.getValue
import kotlin.random.Random

data class HeaderPagingConfig(
    val link: String?,
    val endpoint: String
    ){

    fun prevPage(targetNameValue: String) =
        getValue(HEADER_PARAM_PREV, link, endpoint, targetNameValue)
    fun nextPage(targetNameValue: String) =
        getValue(HEADER_PARAM_NEXT, link, endpoint, targetNameValue)
    fun lastPage(targetNameValue: String) =
        getValue(HEADER_PARAM_LAST, link, endpoint, targetNameValue)
    fun firstPage(targetNameValue: String) =
        getValue(HEADER_PARAM_FIRST, link, endpoint, targetNameValue)
}


object HeaderParam {
    const val HEADER_PARAM_PREV = "prev"
    const val HEADER_PARAM_NEXT = "next"
    const val HEADER_PARAM_LAST = "last"
    const val HEADER_PARAM_FIRST = "first"
    const val PARAM_SINCE = "since"
    const val PARAM_LINK = "link"

    //todo: tratar seguinte resposta desse header aqui
//link: <https://api.github.com/repositories/1300192/issues?page=2>; rel="prev",
// <https://api.github.com/repositories/1300192/issues?page=4>; rel="next",
// <https://api.github.com/repositories/1300192/issues?page=515>; rel="last",
// <https://api.github.com/repositories/1300192/issues?page=1>; rel="first"

    fun getValue (param: String, headerLink: String?,
                  endpoint: String, targetNameValue: String): Int {
        val textValue = getListValues(param, headerLink, endpoint)
            ?.find { it.contains(targetNameValue) }
            ?.replace(targetNameValue, "")
            ?.let {
                Regex("[^a-zA-Z0-9]").replace(it, "") }

        val value = try {
            textValue?.toInt()
        } catch (e: ParseException){
            e.printStackTrace()
            0
        }

        return (value ?: kotlin.run { 0 })
    }

    private fun getListValues(param: String, headerLink: String?,
                              endpoint: String): List<String>? {
        val link = headerLink?.split(",")
            ?.find { it
                .contains(param)
            }
            ?.replace(BuildConfig.BASE_URL, "")
            ?.replace(endpoint, "")
            ?.replace("rel=", "")
            ?.replace(param, "")

        val valuesParam = link?.let { Regex("[^a-zA-Z0-9&=_]").replace(it, "") }

        return valuesParam?.split("&")
    }
    fun getRandomInitialSince() = Random.nextInt(135074069)
}