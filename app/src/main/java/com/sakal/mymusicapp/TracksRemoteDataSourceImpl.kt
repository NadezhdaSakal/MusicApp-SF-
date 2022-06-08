package com.sakal.mymusicapp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sakal.mymusicapp.data.LastFMApi
import com.sakal.mymusicapp.data.entity.TracksWrapper
import java.util.concurrent.Flow

const val NETWORK_PAGE_SIZE = 25

internal class TracksRemoteDataSourceImpl(
    private val trackService: LastFMApi
) : TracksRemoteDataSource {

    override fun getTracks(): Flow<PagingData<TracksWrapper>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                TracksPagingSource(service = LastFMApi)
            }
        ).flow
    }
}
