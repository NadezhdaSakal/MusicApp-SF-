package com.sakal.mymusicapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sakal.mymusicapp.data.LastFMApi
import com.sakal.mymusicapp.data.entity.*
import retrofit2.Call

class TracksDataSource(private val api: LastFMApi) : PagingSource<Int, Track>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response: Call<TracksWrapper> = api.getTracks(params)

            LoadResult.Page(
                data = response.tracks,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.totalPages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Track>): Int? {
        TODO("Not yet implemented")
    }
}
