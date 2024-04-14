package com.percolators.trailhomie

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(trailToReport:String, navController: NavController) {
    val theTrail = TrailList.searchByName(trailToReport)


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = Color(red = 212, green = 169, blue = 219 ))

    ) {

        Row(modifier = Modifier.weight(1.75f)) {
            Text(modifier = Modifier,
                    text = trailToReport,
                    fontSize = 64.sp
            )
        }

        Row(modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .weight(0.5f))
            {
            Text(
                modifier = Modifier,
                text = "CONDITION!",
                fontSize = 32.sp,
            )
        }
        Spacer(modifier = Modifier.weight(0.15f))
        Row(modifier = Modifier.weight(1.5f))
            {
            Image(
                painter = painterResource(R.drawable.test_yo_mama),
                contentDescription = "trail Image",
                modifier = Modifier
                    .weight(3f)
                    .clip(CircleShape)
                )
            }

        Spacer(modifier = Modifier.weight(0.10f))
        Row(modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .weight(0.75f))
        {
            Text(
                modifier = Modifier,
                text = "Report condition?",
                fontSize = 26.sp,
            )
        }
        Row(modifier = Modifier
            .weight(1f)
            .align(alignment = Alignment.CenterHorizontally)
            ) {

            Spacer(modifier = Modifier.weight(0.29f))
            Column(modifier = Modifier
                .weight(1f)) {
                Image(
                    painter = painterResource(R.drawable.test_yo_mama),
                    contentDescription = "trail Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .clip(CircleShape)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(R.drawable.test_yo_mama),
                    contentDescription = "trail Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .clip(CircleShape)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(R.drawable.test_yo_mama),
                    contentDescription = "trail Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .clip(CircleShape)
                    )
                }

        }
        Spacer(modifier = Modifier.weight(0.10f))
    }


}
