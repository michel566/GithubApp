package com.michelbarbosa.githubapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.michelbarbosa.githubapp.model.HeaderPagingConfig
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.network.response.UsersResponse
import com.michelbarbosa.githubapp.network.response.toUserDomain
import com.michelbarbosa.githubapp.paging.data.RemoteDataSource
import kotlin.math.sin

class UserPagingSource(
    private val dataSource: RemoteDataSource<UsersResponse>,
//    private val pagingConfig: HeaderPagingConfig,
    private val perPage: Int
) : PagingSource<Int, UserDomain>() {

    override fun getRefreshKey(state: PagingState<Int, UserDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDomain> {
        return try {
            val since = 1
            val position = params.key ?: PAGE_INDEX
            val popularResponse = dataSource.listUsers(since = since, perPage = perPage)

            LoadResult.Page(
                data = popularResponse.users.map { it.toUserDomain() },
                prevKey = null,
                nextKey = if (since >= position) position + PAGE_INDEX else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }


//        return try {
//            val response = dataSource.listUsers(since = pagingConfig.next, perPage = perPage)
//            LoadResult.Page(
//                data = response.users.map { it.toUserDomain() },
//                prevKey = pagingConfig.prev,
//                nextKey = pagingConfig.next
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
    }

    companion object {
        private const val PAGE_INDEX = 1
    }

}