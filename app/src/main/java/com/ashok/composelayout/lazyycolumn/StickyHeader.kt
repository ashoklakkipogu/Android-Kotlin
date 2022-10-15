package com.ashok.composelayout.lazyycolumn

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun StickyHeader() {
    val sections = listOf("A", "B", "C", "D", "E", "F", "G")
    LazyColumn {
        sections.forEach { sections ->
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Gray)
                        .padding(12.dp),
                    text = "Text $sections"
                )

            }
            items(10) {
               Text(text = "Item $it from the section $sections"
               ) 
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
@Preview
private fun StickyHeaderPreview() {
    StickyHeader()
}