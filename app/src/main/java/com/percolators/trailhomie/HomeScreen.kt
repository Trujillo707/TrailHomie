package com.percolators.trailhomie

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){

    var filterState by remember { mutableStateOf(0)}
    Scaffold(
        containerColor = Color(red = 219, green = 212, blue = 169),
        topBar = {
            TopAppBar(
                title = {
                        Text(text = "Filter", color = Color.White)
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
        ) {
            while (TrailList.getSize() != 3){
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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrailCard(aTrail: Trail, navController: NavController){
    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .height(120.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(red = 212, green = 169, blue = 219 )),
        onClick = {
            navController.navigate(NavScreen.ReportScreen.route + "/${aTrail.getName()}")
        }
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
        Row {
            Text(modifier = Modifier.padding(horizontal = 4.dp),
                text = "Condition: ${aTrail.getCondition()}")
        }
    }
}

