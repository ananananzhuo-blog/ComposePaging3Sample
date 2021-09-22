package com.ananananzhuo.composepaging3sample

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.ananananzhuo.composepaging3sample.simpleuse.SimpleUseBean
import com.ananananzhuo.composepaging3sample.simpleuse.SimpleUseViewModel
import kotlinx.coroutines.flow.collect

/**
 * author  :mayong
 * function:
 * date    :2021/9/21
 **/

@Composable
fun simpleUse() {
    val model = viewModel<SimpleUseViewModel>()
    val datas = model.projects.collectAsLazyPagingItems()
    LazyColumn(content = {
        itemsIndexed(datas) { _, data ->
            Box(
                Modifier
                    .padding(horizontal = 14.dp,vertical = 4.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .border(1.dp, Color.Red, RoundedCornerShape(5.dp))
                    .padding(start = 10.dp)
                ,
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = data?.data ?: "")
            }
        }
    })
}
