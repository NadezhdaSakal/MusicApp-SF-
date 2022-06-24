package com.sakal.mymusicapp.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sakal.mymusicapp.data.*
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.data.entity.Track
import com.sakal.mymusicapp.data.entity.TracksWrapper
import com.sakal.mymusicapp.utils.Converter
import com.sakal.mymusicapp.viewmodel.HomeFragmentViewModel
import com.sakal.mymusicapp.viewmodel.TracksViewModel
import retrofit2.*
import java.io.IOException

class Interactor(private val repo: MainRepository, private val retrofitService: LastFMApi) {

    fun getTracksFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getTracks(limit = 50, page).enqueue(object : Callback<TracksWrapper> {

            override fun onResponse(call: Call<TracksWrapper>, response: Response<TracksWrapper>) {
                val list = Converter.convertApiTrackListToDTOList(response.body()?.tracks?.track)
                    repo.putToDb(list)
                    callback.onSuccess(list)

            }

                override fun onFailure(call: Call<TracksWrapper>, t: Throwable) {
                    callback.onFailure()
                }
            })
        }

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
    fun getTracksFromDB(): List<Audio> = repo.getAllFromDB()
    }








