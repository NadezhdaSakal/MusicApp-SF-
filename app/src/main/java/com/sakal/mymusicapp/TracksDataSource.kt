package com.sakal.mymusicapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sakal.mymusicapp.data.LastFMApi
import com.sakal.mymusicapp.data.entity.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse

class TracksDataSource(private val api: LastFMApi) : PagingSource<Int, Track>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
            return try {
                val nextPageNumber = params.key ?: 0
                val query: Call<TracksWrapper> = api.getTracks(50, 1)
                val response: Response<TracksWrapper> = query.awaitResponse()

                LoadResult.Page(
                    data = response.body()?.tracks!!.track,
                    prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                    nextKey = if (nextPageNumber < response.body()?.tracks?.attr?.totalPages!!.toInt()) nextPageNumber + 1 else null
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Track>): Int? {
            TODO("Not yet implemented")
        }
    }