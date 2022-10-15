package com.ashok.composelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ashok.composelayout.components.CardView
import com.ashok.composelayout.lazyycolumn.CustomItem
import com.ashok.composelayout.lazyycolumn.PersonRepository
import com.ashok.composelayout.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface {
                    //UserLis()
                    /*GoogleButton(
                        text = "Sign Up with Google",
                        loadingText = "Creating Account...",
                        onClicked = {}
                    )*/
                    /*ExpandableCard(
                        title = "My Title",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
                                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                                "ullamco laboris nisi ut aliquip ex ea commodo consequat."
                    )*/
                    //CoilImage()
                    /*GradientButton(
                        text = "Click",
                        textColor = Color.White,
                        gradient = Brush.horizontalGradient(
                            colors = listOf(
                                Purple200,
                                Purple500
                            )
                        )
                    ) {

                    }*/
                    //StickyHeader()
                    val getAllData = PersonRepository().getAllData()
                    LazyColumn(contentPadding = PaddingValues(12.dp)) {
                        items(items = getAllData) { person ->
                            CustomItem(person = person)
                        }
                    }
                }
            }
        }

    }

}

@Composable
fun UserLis() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        for (i in 1..10) {
            CardView()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(Modifier.fillMaxSize()) {
        CardView()
    }
}
