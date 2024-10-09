package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonComponents(modifier: Modifier = Modifier) {
    var step by remember {mutableStateOf(1)}
    var squeezeCount by remember { mutableStateOf(1) }
    val imageResource = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val textResource = when (step) {
        1 -> R.string.tap_tree
        2 -> R.string.tap_lemon
        3 -> R.string.tap_lemonade
        else -> R.string.tap_glass
    }

    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        ){
        Text(stringResource(R.string.app_name), Modifier.padding(20.dp).background(color = Color.Yellow))
        Column(modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally){
            Button(onClick = { if (step == 1){
                step++
                squeezeCount = (2..4).random()
            } else if (step == 2) {
                squeezeCount--
                if (squeezeCount == 0) {
                    step++
                }
            }else if (step == 3) {
                println("you are in step 3")
                step++}
            else if (step == 4) {step = 1}},
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)) {
                Image(
                    painter = painterResource(imageResource), contentDescription = stringResource(R.string.tree_content_description)
                )
            }

            Text(stringResource(textResource),
                Modifier.padding(20.dp))
        }
        Spacer(modifier = modifier)

    }

}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonComponents(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}