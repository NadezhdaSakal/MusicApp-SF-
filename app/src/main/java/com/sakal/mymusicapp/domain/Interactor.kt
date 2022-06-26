package com.sakal.mymusicapp.domain

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sakal.mymusicapp.data.entity.*
import com.sakal.mymusicapp.data.LastFMApi
import com.sakal.mymusicapp.data.MainRepository

class Interactor(private val repo: MainRepository, private val api: LastFMApi
) :
    PagingSource<Int, Track>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
        val pageNumber = params.key ?: 1
        return try {
            val response = api.getTracks(100,1)
            val pagedResponse = response.body()?.tracks?.track
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
    override fun getRefreshKey(state: PagingState<Int, Track>): Int? = 1

    fun getTracksFromDB(): List<Audio> {
        return repo.getAllFromDB()
    }

}



