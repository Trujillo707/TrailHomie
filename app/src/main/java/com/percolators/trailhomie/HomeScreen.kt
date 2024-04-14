package com.percolators.trailhomie

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){

    var filterState by remember { mutableStateOf(0)}
    Scaffold(
        containerColor = Color(red = 219, green = 212, blue = 169),
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(text = "TrailHomie", color = Color.White)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Filter", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(red =0 , green = 76, blue = 70)
                ),
                actions = {
                    IconButton(onClick = {
                        if (filterState == 0){
                            filterState = 1
                        }
                        else{
                            filterState = 0
                        }
                        }
                    ) {
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
            .fillMaxHeight()
        ) {
            // Magic Number TODO:Dont do that
            while (TrailList.getSize() != 5){
                continue
            }
            items(TrailList.sortedDistance()){trail->
                if (filterState == 1){
                    if (trail.getCondition() >= 0){
                        TrailCard(aTrail = trail, navController)
                    }
                }
                else{
                    TrailCard(aTrail = trail, navController)
                }

            }
        }
    }
}
val imageMap = mapOf(
                    ("Arcata Community Forest" to (R.drawable.arcata_community_forest)),
                    ("Avenue of the Giants" to (R.drawable.avenue_of_the_giants)),
                    ("Hammond Coastal Trail" to (R.drawable.hammond_coastal_trail)),
                    ("Sequoia Park" to (R.drawable.sequoia_park)),
                    ("Sue-meg State Park" to (R.drawable.sue_meg_state_park)))


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrailCard(aTrail: Trail, navController: NavController){
    val conditionMap = mapOf(0L to (R.drawable.mud_boot),1L to (R.drawable.flat_boot), -1L to (R.drawable.water_boot))

    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(red = 212, green = 169, blue = 219 )),
        onClick = {
            navController.navigate(NavScreen.ReportScreen.route + "/${aTrail.getName()}/${aTrail.getCondition()}")
        }
    ) {
        Box (modifier = Modifier.
            fillMaxHeight(0.20f)
        )

        {
            imageMap[aTrail.getName()]?.let { painterResource(id = it) }
                ?.let { Image(painter = it, contentDescription = "",
                    contentScale = ContentScale.FillBounds, modifier = Modifier.matchParentSize())}
            Column () {
                Row {
                    Text(
                        modifier = Modifier
                            .padding(4.dp),
                        text = aTrail.getName(),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        style = TextStyle(shadow = Shadow(color = Color.Black, blurRadius = 10f)),
                        color = Color.White
                    )

                }
                Row {
                    Text(
                        modifier = Modifier
                            .weight(1.5f)
                            .padding(4.dp),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        style = TextStyle(shadow = Shadow(color = Color.Black, blurRadius = 10f)),
                        text = "Distance: ${aTrail.getDistance()}"
                    )
                    Spacer(modifier = Modifier.weight(1.5f))
                    Image(
                        painter = painterResource(conditionMap[aTrail.getCondition()]!!),
                        contentDescription = "trail Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .weight(1f)
                            .clip(CircleShape)
                    )
                }
            }
        }
    }
}

