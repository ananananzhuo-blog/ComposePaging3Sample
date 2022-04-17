package com.ananananzhuo.composepaging3sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ananananzhuo.composelib.ListView
import com.ananananzhuo.composelib.bean.ItemData
import com.ananananzhuo.composelib.constants.HOME
import com.ananananzhuo.composelib.constants.PAGE1
import com.ananananzhuo.composelib.constants.PAGE2
import com.ananananzhuo.composelib.constants.PAGE3
import com.ananananzhuo.composepaging3sample.refresh.refreshLoadUse
import com.ananananzhuo.composepaging3sample.roomload.RoomLoadPage
import com.ananananzhuo.composepaging3sample.ui.theme.ComposePaging3SampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePaging3SampleTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HOME) {
        composable(HOME) {
            home(navController)
        }
        composable(PAGE1) {
            simpleUse()
        }
        composable(PAGE2) {
            refreshLoadUse()
        }
        composable(PAGE3) {
            RoomLoadPage()
        }
    }
}

@Composable
fun home(navController: NavHostController) {
    ListView(datas = mutableListOf(
        ItemData(title = "简单使用Paging加载数据", tag = PAGE1),
        ItemData(title = "使用Paging3实现刷新和加载功能", tag = PAGE2),
        ItemData(title = "Room数据库+远程请求+Paging3实现Paging3中数据的插入和删除", tag = PAGE3),
    ), click = { itemData: ItemData, _, _ ->
        navController.navigate(itemData.tag)
    })
}
