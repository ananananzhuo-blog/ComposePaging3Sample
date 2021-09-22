package com.ananananzhuo.composepaging3sample.refresh

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.*
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

/**
 * author  :mayong
 * function:添加加载框
 * date    :2021/9/21
 **/
@Composable
fun refreshLoadUse() {
    val refreshState = rememberSwipeRefreshState(isRefreshing = false)
    val model = viewModel<RefreshLoadUseViewModel>()
    val collectAsLazyPagingItems = model.datas.collectAsLazyPagingItems()
    SwipeRefresh(state = refreshState, onRefresh = {
        collectAsLazyPagingItems.refresh()
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            content = {
                itemsIndexed(collectAsLazyPagingItems) { _, refreshData ->//每个item的展示
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 14.dp, vertical = 4.dp)
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(Color.Green,shape= RoundedCornerShape(8.dp))
                            .border(
                                width = 1.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(start = 10.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(text = refreshData?.data ?: "")
                    }
                }
                when (collectAsLazyPagingItems.loadState.append) {
                    is LoadState.Loading -> {//加载中的尾部item展示
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "加载中。。。")
                            }
                        }
                    }
                    else -> {//加载完成或者加载错误展示的尾部item
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "--加载完成或加载错误--")
                            }
                        }
                    }
                }

            })
    }
}

data class RefreshData(val data: String)

class RefreshLoadUseViewModel : ViewModel() {
    val datas = Pager(PagingConfig(pageSize = 20)) {
        RefreshLoadUseSource()
    }.flow.cachedIn(viewModelScope)
}


class RefreshLoadUseSource : PagingSource<Int, RefreshData>() {
    override fun getRefreshKey(state: PagingState<Int, RefreshData>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RefreshData> {
        return try {
            val nextPage = params.key ?: 1
            if (nextPage < 13) {
                delay(1500)
                val datas = mutableListOf(
                    RefreshData("哈哈${params.key}"),
                    RefreshData("哈哈${params.key}"),
                    RefreshData("哈哈${params.key}"),
                    RefreshData("哈哈${params.key}"),
                    RefreshData("哈哈${params.key}")
                )
                LoadResult.Page(
                    data = datas,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (nextPage < 100) nextPage + 1 else null
                )
            } else {
                LoadResult.Error(NullPointerException("空指针"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

