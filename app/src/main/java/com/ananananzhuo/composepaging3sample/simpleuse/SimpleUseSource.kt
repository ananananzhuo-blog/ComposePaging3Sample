package com.ananananzhuo.composepaging3sample.simpleuse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*

/**
 * author  :mayong
 * function:
 * date    :2021/9/21
 **/

class SimpleUseViewModel : ViewModel() {
    val projects = Pager(PagingConfig(pageSize = 20)){
        SimpleUseSource()
    }.flow.cachedIn(viewModelScope)
}

data class SimpleUseBean(val data: String="")

class SimpleUseSource : PagingSource<Int, SimpleUseBean>() {
    override fun getRefreshKey(state: PagingState<Int, SimpleUseBean>): Int? =null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimpleUseBean> {
        return try {
            val nextPage = params.key ?: 1
            val datas = mutableListOf(
                SimpleUseBean("哈哈${params.key}"),
                SimpleUseBean("哈哈${params.key}"),
                SimpleUseBean("哈哈${params.key}"),
                SimpleUseBean("哈哈${params.key}"),
                SimpleUseBean("哈哈${params.key}")
            )
            LoadResult.Page(
                data = datas,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nextPage < 100) nextPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}


