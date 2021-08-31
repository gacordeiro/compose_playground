package com.tutuland.compose02

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tutuland.compose02.ui.theme.Compose02Theme
import kotlinx.coroutines.launch
import okhttp3.internal.http2.Header

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose02Theme {
                LayoutScaffold()
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun LayoutScaffold() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Layouts Codelab")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                    }
                },
            )
        },
    ) { innerPadding -> BodyContent(Modifier.padding(innerPadding)) }
}

@ExperimentalFoundationApi
@Preview(
    name = "Light Mode",
    showBackground = true,
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun LayoutScaffoldPreview() {
    Compose02Theme {
        LayoutScaffold()
    }
}

@ExperimentalFoundationApi
@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    val listSize = 10
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier.fillMaxWidth()) {
        LazyColumn(Modifier.weight(1f), state = scrollState) {
            stickyHeader {
                Header("ITEMS!")
            }
            items(count = listSize) { index ->
                ImageListItem(index = index)
            }
            stickyHeader {
                Header("MORE ITEMS!")
            }
            items(count = listSize) { index ->
                ImageListItem(index = index+listSize)
            }
            stickyHeader {
                Header("SOOOO MANY ITEMS!")
            }
            items(count = listSize) { index ->
                ImageListItem(index = index+listSize+listSize)
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = {
                coroutineScope.launch { scrollState.animateScrollToItem(0) }
            }) {
                Text(text = "to the top")
            }
            Spacer(Modifier.width(16.dp))
            Button(onClick = {
                coroutineScope.launch { scrollState.animateScrollToItem(3*listSize - 1) }
            }) {
                Text(text = "to the botton")
            }
        }
    }
}

@Composable
fun Header(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        textAlign = TextAlign.Center
    )
}