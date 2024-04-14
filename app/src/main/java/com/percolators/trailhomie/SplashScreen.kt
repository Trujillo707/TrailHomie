package com.percolators.trailhomie

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SplashScreen(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.sue_meg_state_park), contentDescription = "", contentScale = ContentScale.Inside)
        Column ( horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
            Spacer(modifier = Modifier.fillMaxHeight(0.10F))
            Image(modifier = Modifier.fillMaxHeight(0.25F) ,painter =painterResource(id = R.drawable.trailhomie), contentDescription = "")
            Spacer(modifier = Modifier.fillMaxHeight(0.60f))
            Button(onClick = { navController.navigate(NavScreen.HomeScreen.route) }, modifier = Modifier.fillMaxHeight(0.35F).fillMaxWidth(.65F)) {
                Text(text = "Where to homie?", fontSize = 24.sp )
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.20F))
        }
    }
}