package com.michelbarbosa.githubapp.model

import com.michelbarbosa.githubapp.BuildConfig
import com.michelbarbosa.githubapp.model.HeaderParam.HEADER_PARAM_FIRST
import com.michelbarbosa.githubapp.model.HeaderParam.HEADER_PARAM_LAST
import com.michelbarbosa.githubapp.model.HeaderParam.HEADER_PARAM_NEXT
import com.michelbarbosa.githubapp.model.HeaderParam.HEADER_PARAM_PREV
import com.michelbarbosa.githubapp.model.HeaderParam.getValue

data class HeaderPagingConfig(
    val link: String,
    val endpoint: String,
    val prev: Int = 0,
    val next: Int = 1,
    val last: Int = 2,
    val first: Int = 0
    ){

    val prevPage: Int get() = getValue(HEADER_PARAM_PREV, link, endpoint)
    val nextPage: Int get() = getValue(HEADER_PARAM_NEXT, link, endpoint)
    val lastPage: Int get() = getValue(HEADER_PARAM_LAST, link, endpoint)
    val firstPage: Int get() = getValue(HEADER_PARAM_FIRST, link, endpoint)
}


object HeaderParam {
    const val HEADER_PARAM_PREV = "prev"
    const val HEADER_PARAM_NEXT = "next"
    const val HEADER_PARAM_LAST = "last"
    const val HEADER_PARAM_FIRST = "first"
    const val PARAM_PAGE = "?page="
    const val PARAM_LINK = "link"

    //todo: tratar seguinte resposta desse header aqui
//link: <https://api.github.com/repositories/1300192/issues?page=2>; rel="prev",
// <https://api.github.com/repositories/1300192/issues?page=4>; rel="next",
// <https://api.github.com/repositories/1300192/issues?page=515>; rel="last",
// <https://api.github.com/repositories/1300192/issues?page=1>; rel="first"


    fun getValue (param: String, headerLink: String, endpoint: String): Int {
        val value = headerLink.split(",")
            .find { it
                .contains(param) }
            ?.replace(BuildConfig.BASE_URL + endpoint + PARAM_PAGE, "")
            ?.toInt()

        return value ?: kotlin.run { 0 }
    }

}