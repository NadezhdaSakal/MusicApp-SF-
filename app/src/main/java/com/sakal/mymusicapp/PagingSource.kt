package com.sakal.mymusicapp

import androidx.paging.PagingState
import com.sakal.mymusicapp.data.LastFMApi
import com.sakal.mymusicapp.data.entity.TracksWrapper
import retrofit2.HttpException
import java.io.IOException

private const val LastFM_STARTING_PAGE_INDEX = 1


class PagingSource(
    private val service: LastFMApi
) : PagingSource<Int, TracksWrapper>() {


    override suspend fun load(params: androidx.paging.PagingSource.LoadParams<Int>): androidx.paging.PagingSource.LoadResult<Int, TracksWrapper> {
        val pageIndex = params.key ?: LastFM_STARTING_PAGE_INDEX
        return try {
            val response = service.getTracks(
                page = pageIndex
            )
            val tracks = response.results
            val nextKey =
                if (tracks.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            androidx.paging.PagingSource.LoadResult.Page(
                data = tracks,
                prevKey = if (pageIndex == LastFM_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return androidx.paging.PagingSource.LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return androidx.paging.PagingSource.LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, TracksWrapper>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
