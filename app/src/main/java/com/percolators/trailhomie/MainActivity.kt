package com.percolators.trailhomie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.percolators.trailhomie.ui.theme.TrailHomieTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var listOfTrail = mutableListOf<Trail>(Trail("Foobar Trail", 25F, 2))

        setContent {
            TrailHomieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(red = 245, green = 243, blue = 231)
                ) {
                    HomeScreen(listOfTrail)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    var listOfTrail = mutableListOf<Trail>(Trail("Foobar Trail", 25F, 2),
        Trail("Banana test", 48F, 1))
    TrailHomieTheme {
        HomeScreen(listOfTrail)
    }
}