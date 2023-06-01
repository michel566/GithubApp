package com.michelbarbosa.githubapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.michelbarbosa.githubapp.model.HeaderParam
import com.michelbarbosa.githubapp.model.HeaderParam.PARAM_SINCE
import com.michelbarbosa.githubapp.model.UserDomain
import com.michelbarbosa.githubapp.model.UserResultDomain
import com.michelbarbosa.githubapp.paging.data.RemoteDataSource

class UserPagingSource(
    private val dataSource: RemoteDataSource<UserResultDomain?>,
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
            val response =
                dataSource.listUsers(since = HeaderParam.getRandomInitialSince(), perPage = perPage)

            response?.let { resultDomain ->
                val prevKey = resultDomain.pagingConfig.prevPage(PARAM_SINCE)
                val nextKey = resultDomain.pagingConfig.nextPage(PARAM_SINCE)
                LoadResult.Page(
                    data = resultDomain.listUser,
                    prevKey = if (prevKey == 0) null else prevKey,
                    nextKey = nextKey
                )
            } ?: kotlin.run {
                LoadResult.Error(Exception("No content"))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}