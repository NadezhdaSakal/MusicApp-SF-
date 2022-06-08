package com.sakal.mymusicapp

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sakal.mymusicapp.data.entity.*
import com.sakal.mymusicapp.data.LastFMApi

class TracksPagingDataSource(private val service: LastFMApi) :
    PagingSource<Int, TracksWrapper>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TracksWrapper> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getTracks(pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.results

            var nextPageNumber: Int? = null
            if (pagedResponse?.pageInfo?.next != null) {
                val uri = Uri.parse(pagedResponse.pageInfo.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TracksWrapper>): Int = 1
}
