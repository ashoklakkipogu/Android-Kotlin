package com.ashok.composelayout.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.*

@Composable
fun CoilImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        contentAlignment = Alignment.Center
    ) {
        /* AsyncImage(
             model = "https://images.pexels.com/photos/1072851/pexels-photo-1072851.jpeg",
             contentDescription = null
         )*/

        /*AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://images.pexels.com/photos/1072851/pexels-photo-1072851.jpeg")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,

            )*/

       /* SubcomposeAsyncImage(
            model = "https://images.pexels.com/photos/1072851/pexels-photo-1072851.jpeg",
            loading = {
                CircularProgressIndicator()
            },
            contentDescription = ""
        )*/

        SubcomposeAsyncImage(
            model = "https://images.pexels.com/photos/1072851/pexels-photo-1072851.jpeg",
            contentDescription = ""
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }

    }
}

@Composable
@Preview
private fun CoilImagePreview() {
    Box {
        CoilImage()
    }
}