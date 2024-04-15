package com.example.acc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(color = MaterialTheme.colors.background) {
                AccelerometerApp()
            }
        }
    }

    @Composable
    fun AccelerometerApp(sensorViewModel: SensorViewModel = viewModel()) {
        val orientation by sensorViewModel.orientationLiveData. observeAsState(FloatArray(3))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Accelerometer Orientation", fontSize = 24.sp)

            SensorOrientation(orientation = orientation)

            DisposableEffect(key1 = null) {
                onDispose {
                    // Clean up if needed
                }
            }
        }
    }

    @Composable
    fun SensorOrientation(orientation: FloatArray) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("X-Axis: ${orientation[0].roundToInt()} degrees")
            Text("Y-Axis: ${orientation[1].roundToInt()} degrees")
            Text("Z-Axis: ${orientation[2].roundToInt()} degrees")

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Phone Orientation",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(160.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                            .padding(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(140.dp)
                                .clip(CircleShape)
                                .background(Color.DarkGray)
                                .padding(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(CircleShape)
                                    .background(Color.Black)
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "X",
                                    fontSize = 20.sp,
                                    color = Color.White,
                                    modifier = Modifier.rotate(orientation[0])
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Surface(color = MaterialTheme.colors.background) {
            AccelerometerApp()
        }
    }
}