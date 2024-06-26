package com.percolators.trailhomie
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import android.os.CountDownTimer
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow


//var end = false

// The timer is very good but due to scope it cannot call the function easily
// from how we have it structured
//object Timer: CountDownTimer(10000, 1000) {
//
//    override fun onTick(millisUntilFinished: Long) {
//    }
//
//    override fun onFinish() {
//         end = true
//    }
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(trailToReport:String, trailCondition:Long, navController: NavController) {
    val theTrail = TrailList.searchByName(trailToReport)
    val conditionMap = mapOf(0L to (R.drawable.mud_boot),1L to (R.drawable.flat_boot), -1L to (R.drawable.water_boot))
    var color by remember { mutableLongStateOf( -6) }
    var hasSubmitted by remember {
        mutableIntStateOf(0)
    }
    var end by remember{ mutableIntStateOf(0)}

//    if(end)
//    {DatabaseManagement.sendReport(trailToReport,color)}

    Box {
        imageMap[trailToReport]?.let { painterResource(id = it) }
            ?.let { Image(painter = it, contentDescription = "", contentScale = ContentScale.FillHeight) }
        Column(
            modifier = Modifier
                .fillMaxHeight()

        ) {

            Row(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .weight(1.5f)
            ) {
                Text(
                    modifier = Modifier,
                    text = trailToReport,
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Clip,
                    lineHeight = 45.sp,
                    fontWeight = FontWeight.ExtraBold,
                    style = TextStyle(shadow = Shadow(color = Color.Black, blurRadius = 10f)),
                    color = Color.White

                )
            }

            Row(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .weight(0.5f)
            )
            {
                Text(
                    modifier = Modifier,
                    text = "CONDITION!",
                    fontSize = 28.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    style = TextStyle(shadow = Shadow(color = Color.Black, blurRadius = 10f)),

                )
            }
            Spacer(modifier = Modifier.weight(0.15f))
            Row(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .weight(1.5f)
            )
            {
                Image(
                    painter = painterResource(conditionMap[trailCondition]!!),
                    contentDescription = "trail Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.weight(0.10f))
            Row(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .weight(0.75f)
            )
            {
                Text(
                    modifier = Modifier,
                    text = "Report condition?",
                    fontSize = 28.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    style = TextStyle(shadow = Shadow(color = Color.Black, blurRadius = 10f)),

                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                ElevatedCard(modifier = Modifier
                    .weight(1f),
                    colors = if (color == -1L) {
                        CardDefaults.cardColors(containerColor = Color.White)
                    } else {
                        CardDefaults.cardColors(containerColor = Color.Gray)
                    },
                    shape = CircleShape,
                    onClick = {
                        if (end != 1) {
//                            if (color == -6L) {
//                                Timer.start()
//                            }
                            if (color != -1L ) {
                                color = -1L
                                DatabaseManagement.sendReport(trailToReport,color)
                                end = 1
                            }
                        }
//                        else if(hasSubmitted == 0){
//                            DatabaseManagement.sendReport(trailToReport,color)
//                            hasSubmitted = 1
//                        }
                    }
                )
                {
                    Image(
                        painter = painterResource(R.drawable.water_boot), // change to water image
                        contentDescription = "trail Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .clip(CircleShape)
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                }
                ElevatedCard(modifier = Modifier
                    .weight(1f),
                    colors = if (color == 0L) {
                        CardDefaults.cardColors(containerColor = Color.White)
                    } else {
                        CardDefaults.cardColors(containerColor = Color.Gray)
                    },
                    shape = CircleShape,
                    onClick = {
                        if (end != 1) {
//                            if (color == -6L) {
//                                Timer.start()
//                            }
                            if (color != 0L) {
                                color = 0L
                                DatabaseManagement.sendReport(trailToReport,color)
                                end = 1
                            }
                        }
//                        else if(hasSubmitted == 0){
//                            DatabaseManagement.sendReport(trailToReport,color)
//                            hasSubmitted = 1
//                        }
                    }
                )
                {
                    Image(
                        painter = painterResource(R.drawable.mud_boot), //change to mud image
                        contentDescription = "trail Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .clip(CircleShape)
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                }
                ElevatedCard(modifier = Modifier
                    .weight(1f),
                    colors = if (color == 1L) {
                        CardDefaults.cardColors(containerColor = Color.White)
                    } else {
                        CardDefaults.cardColors(containerColor = Color.Gray)
                    },
                    shape = CircleShape,
                    onClick = {
                        if (end != 1) {
//                            if (color == -6L) {
//                                Timer.start()
//                            }
                            if (color != 1L) {
                                color = 1L
                                DatabaseManagement.sendReport(trailToReport,color)
                                end = 1
                            }
                        }
//                        else if(hasSubmitted == 0){
//                            DatabaseManagement.sendReport(trailToReport,color)
//                            hasSubmitted = 1
//                        }
                    }
                )
                {
                    Image(
                        painter = painterResource(R.drawable.flat_boot),
                        contentDescription = "trail Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .clip(CircleShape)
                            .align(alignment = Alignment.CenterHorizontally)

                    )
                }

            }
            Spacer(modifier = Modifier.weight(0.10f))
        }
    }

//
}
