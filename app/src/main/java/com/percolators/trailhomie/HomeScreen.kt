package com.percolators.trailhomie

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(trailList:List<Trail>){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                        Text(text = "Sort", color = Color.White)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(red =0 , green = 76, blue = 70)
                ),
                actions = {
                    IconButton(onClick = { /*TODO*/ }, ) {
                        Icon(painter = painterResource(id = R.drawable.baseline_filter_list_24),
                            contentDescription = "Filter",
                            tint = Color.White)
                    }
                }
            )
        }
    ) {paddingValues ->
        LazyColumn(modifier = Modifier
            .padding(paddingValues)
        ) {
            items(trailList){trail->
                TrailCard(aTrail = trail)
            }
        }
    }
}



@Composable
fun TrailCard(aTrail: Trail){
    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .height(120.dp)
            .fillMaxWidth()
    ) {
        Row {
            Text(
                modifier = Modifier
                    .padding(4.dp),
                text = aTrail.getName(),
                fontSize = 32.sp,
            )

        }
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Text(modifier = Modifier
                .padding(horizontal = 4.dp),
                text = "Distance")
        }
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Text(modifier = Modifier
                .padding(horizontal = 4.dp),
                text = "${aTrail.getDistance()}")
        }
    }
}

